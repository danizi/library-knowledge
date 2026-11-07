#!/usr/bin/env python3
"""知识看板服务。卡片存在同目录的 SQLite 里，局域网里的其他设备用打印出来的地址打开。"""

import json
import re
import socket
import sqlite3
import subprocess
import threading
from datetime import datetime, timezone
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer
from pathlib import Path
from urllib.parse import unquote, urlparse

ROOT = Path(__file__).resolve().parent
DB_PATH = ROOT / "kanban.sqlite"
HTML_PATH = ROOT / "kanban.html"
PORT = 8741
LOCK = threading.Lock()


def now():
    return datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")


def blocks_to_markdown(blocks):
    parts = []
    for block in blocks or []:
        kind = block.get("type")
        if kind == "text" and (block.get("text") or "").strip():
            parts.append(block["text"].strip())
        elif kind == "image":
            alt = (block.get("alt") or "").replace("[", "").replace("]", "")
            parts.append("![%s](%s)" % (alt, block.get("src") or ""))
        elif (block.get("source") or "").strip():
            source = str(block["source"]).rstrip("\n")
            parts.append("```mermaid\n%s\n```" % source)
    return "\n\n".join(parts)


def connect():
    conn = sqlite3.connect(DB_PATH)
    conn.row_factory = sqlite3.Row
    conn.execute("PRAGMA journal_mode=WAL")
    conn.execute(
        """
        CREATE TABLE IF NOT EXISTS cards (
          id TEXT PRIMARY KEY,
          title TEXT NOT NULL DEFAULT '',
          code TEXT NOT NULL DEFAULT '',
          column_name TEXT NOT NULL DEFAULT 'todo',
          domain TEXT NOT NULL DEFAULT '',
          kind TEXT NOT NULL DEFAULT '',
          summary TEXT NOT NULL DEFAULT '',
          tags TEXT NOT NULL DEFAULT '[]',
          path TEXT NOT NULL DEFAULT '',
          body TEXT NOT NULL DEFAULT '',
          blocks TEXT NOT NULL DEFAULT '[]',
          position INTEGER NOT NULL DEFAULT 0,
          updated_at TEXT NOT NULL DEFAULT ''
        )
        """
    )
    return conn


def card_from_row(row):
    return {
        "id": row["id"],
        "title": row["title"],
        "code": row["code"],
        "column": row["column_name"],
        "domain": row["domain"],
        "kind": row["kind"],
        "summary": row["summary"],
        "tags": json.loads(row["tags"] or "[]"),
        "path": row["path"],
        "body": row["body"],
        "blocks": json.loads(row["blocks"] or "[]"),
        "position": row["position"],
        "updatedAt": row["updated_at"],
    }


def clean_card(raw, index):
    column = raw.get("column") if raw.get("column") in ("todo", "doing", "review", "done") else "doing"
    blocks = raw.get("blocks") if isinstance(raw.get("blocks"), list) else []
    tags = raw.get("tags") if isinstance(raw.get("tags"), list) else []
    body = raw.get("body") if isinstance(raw.get("body"), str) else ""
    if not body:
        body = blocks_to_markdown(blocks)
    return {
        "id": str(raw.get("id") or ("card-%s" % index)),
        "title": str(raw.get("title") or "未命名"),
        "code": str(raw.get("code") or ""),
        "column": column,
        "domain": str(raw.get("domain") or ""),
        "kind": str(raw.get("kind") or ""),
        "summary": str(raw.get("summary") or ""),
        "tags": [str(tag) for tag in tags],
        "path": str(raw.get("path") or ""),
        "body": body,
        "blocks": blocks,
        "position": int(raw["position"]) if isinstance(raw.get("position"), int) else index,
    }


def upsert(conn, card):
    conn.execute(
        """
        INSERT INTO cards (
          id, title, code, column_name, domain, kind, summary, tags, path, body, blocks, position, updated_at
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ON CONFLICT(id) DO UPDATE SET
          title = excluded.title,
          code = excluded.code,
          column_name = excluded.column_name,
          domain = excluded.domain,
          kind = excluded.kind,
          summary = excluded.summary,
          tags = excluded.tags,
          path = excluded.path,
          body = excluded.body,
          blocks = excluded.blocks,
          position = excluded.position,
          updated_at = excluded.updated_at
        """,
        (
            card["id"],
            card["title"],
            card["code"],
            card["column"],
            card["domain"],
            card["kind"],
            card["summary"],
            json.dumps(card["tags"], ensure_ascii=False),
            card["path"],
            card["body"],
            json.dumps(card["blocks"], ensure_ascii=False),
            card["position"],
            now(),
        ),
    )


def list_cards(conn):
    rows = conn.execute("SELECT * FROM cards ORDER BY position, title").fetchall()
    return [card_from_row(row) for row in rows]


def seed_if_empty(conn):
    count = conn.execute("SELECT COUNT(*) FROM cards").fetchone()[0]
    if count or not HTML_PATH.exists():
        return
    html = HTML_PATH.read_text(encoding="utf-8")
    match = re.search(
        r'<script id="seed" type="application/json">(.*?)</script>',
        html,
        re.S,
    )
    if not match:
        return
    cards = json.loads(match.group(1))
    for index, raw in enumerate(cards):
        upsert(conn, clean_card(raw, index))
    conn.commit()


def lan_ips():
    ips = []
    try:
        out = subprocess.check_output(["ifconfig"], text=True, stderr=subprocess.DEVNULL)
        for line in out.splitlines():
            parts = line.strip().split()
            if len(parts) >= 2 and parts[0] == "inet" and parts[1] != "127.0.0.1":
                if parts[1] not in ips:
                    ips.append(parts[1])
    except (OSError, subprocess.CalledProcessError):
        pass
    if ips:
        return ips
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.connect(("8.8.8.8", 80))
        ips.append(sock.getsockname()[0])
        sock.close()
    except OSError:
        pass
    return ips


class Handler(BaseHTTPRequestHandler):
    protocol_version = "HTTP/1.1"

    def log_message(self, fmt, *args):
        if args and str(args[0]).startswith("GET /api/cards "):
            return
        super().log_message(fmt, *args)

    def read_json(self):
        length = int(self.headers.get("Content-Length", "0") or 0)
        if length > 8 * 1024 * 1024:
            raise ValueError("太大")
        raw = self.rfile.read(length) if length else b""
        if not raw:
            return {}
        return json.loads(raw.decode("utf-8"))

    def send_json(self, status, payload):
        body = json.dumps(payload, ensure_ascii=False).encode("utf-8")
        self.send_response(status)
        self.send_header("Content-Type", "application/json; charset=utf-8")
        self.send_header("Content-Length", str(len(body)))
        self.send_header("Cache-Control", "no-store")
        self.end_headers()
        self.wfile.write(body)

    def send_html(self):
        body = HTML_PATH.read_bytes()
        self.send_response(200)
        self.send_header("Content-Type", "text/html; charset=utf-8")
        self.send_header("Content-Length", str(len(body)))
        self.send_header("Cache-Control", "no-store")
        self.end_headers()
        self.wfile.write(body)

    def do_GET(self):
        path = urlparse(self.path).path
        if path in ("/", "/kanban.html"):
            self.send_html()
            return
        if path == "/api/info":
            urls = ["http://127.0.0.1:%s/" % PORT]
            urls.extend("http://%s:%s/" % (ip, PORT) for ip in lan_ips())
            self.send_json(200, {"db": DB_PATH.name, "urls": urls})
            return
        if path == "/api/cards":
            with LOCK:
                conn = connect()
                try:
                    cards = list_cards(conn)
                finally:
                    conn.close()
            self.send_json(200, {"cards": cards})
            return
        self.send_json(404, {"error": "没有这个地址"})

    def do_POST(self):
        path = urlparse(self.path).path
        if path != "/api/cards":
            self.send_json(404, {"error": "没有这个地址"})
            return
        try:
            raw = self.read_json()
        except ValueError:
            self.send_json(413, {"error": "内容太大"})
            return
        except json.JSONDecodeError:
            self.send_json(400, {"error": "不是 JSON"})
            return
        with LOCK:
            conn = connect()
            try:
                count = conn.execute("SELECT COUNT(*) FROM cards").fetchone()[0]
                card = clean_card(raw, count)
                upsert(conn, card)
                conn.commit()
                row = conn.execute("SELECT * FROM cards WHERE id = ?", (card["id"],)).fetchone()
            finally:
                conn.close()
        self.send_json(200, {"card": card_from_row(row)})

    def do_PUT(self):
        path = unquote(urlparse(self.path).path)
        try:
            raw = self.read_json()
        except ValueError:
            self.send_json(413, {"error": "内容太大"})
            return
        except json.JSONDecodeError:
            self.send_json(400, {"error": "不是 JSON"})
            return
        if path == "/api/cards":
            cards = raw.get("cards") if isinstance(raw, dict) else None
            if not isinstance(cards, list):
                self.send_json(400, {"error": "需要 cards 数组"})
                return
            with LOCK:
                conn = connect()
                try:
                    conn.execute("DELETE FROM cards")
                    seen = set()
                    for index, item in enumerate(cards):
                        card = clean_card(item, index)
                        while card["id"] in seen:
                            card["id"] += "-2"
                        seen.add(card["id"])
                        card["position"] = index
                        upsert(conn, card)
                    conn.commit()
                    stored = list_cards(conn)
                finally:
                    conn.close()
            self.send_json(200, {"cards": stored})
            return
        if path == "/api/order":
            items = raw.get("items") if isinstance(raw, dict) else None
            if not isinstance(items, list):
                self.send_json(400, {"error": "需要 items 数组"})
                return
            with LOCK:
                conn = connect()
                try:
                    for item in items:
                        column = item.get("column") if item.get("column") in ("todo", "doing", "review", "done") else None
                        if column is None or "id" not in item:
                            continue
                        conn.execute(
                            "UPDATE cards SET column_name = ?, position = ?, updated_at = ? WHERE id = ?",
                            (column, int(item.get("position") or 0), now(), str(item["id"])),
                        )
                    conn.commit()
                finally:
                    conn.close()
            self.send_json(200, {"ok": True})
            return
        prefix = "/api/cards/"
        if not path.startswith(prefix) or path == prefix:
            self.send_json(404, {"error": "没有这个地址"})
            return
        card_id = path[len(prefix):]
        raw["id"] = card_id
        with LOCK:
            conn = connect()
            try:
                row = conn.execute("SELECT position FROM cards WHERE id = ?", (card_id,)).fetchone()
                card = clean_card(raw, row["position"] if row else 0)
                card["id"] = card_id
                if row:
                    card["position"] = row["position"]
                upsert(conn, card)
                conn.commit()
                stored = conn.execute("SELECT * FROM cards WHERE id = ?", (card_id,)).fetchone()
            finally:
                conn.close()
        self.send_json(200, {"card": card_from_row(stored)})

    def do_DELETE(self):
        path = unquote(urlparse(self.path).path)
        prefix = "/api/cards/"
        if not path.startswith(prefix):
            self.send_json(404, {"error": "没有这个地址"})
            return
        card_id = path[len(prefix):]
        with LOCK:
            conn = connect()
            try:
                conn.execute("DELETE FROM cards WHERE id = ?", (card_id,))
                conn.commit()
            finally:
                conn.close()
        self.send_json(200, {"ok": True})


def main():
    with LOCK:
        conn = connect()
        try:
            seed_if_empty(conn)
            count = conn.execute("SELECT COUNT(*) FROM cards").fetchone()[0]
        finally:
            conn.close()
    server = ThreadingHTTPServer(("0.0.0.0", PORT), Handler)
    print("知识看板", flush=True)
    print("数据库  %s" % DB_PATH, flush=True)
    print("卡片    %s 张" % count, flush=True)
    print("本机    http://127.0.0.1:%s/" % PORT, flush=True)
    for ip in lan_ips():
        print("局域网  http://%s:%s/" % (ip, PORT), flush=True)
    print("其他设备要和这台电脑在同一个网络，并且这个窗口保持开着。", flush=True)
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\n已停止")
    finally:
        server.server_close()


if __name__ == "__main__":
    main()
