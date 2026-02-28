# 授权、激活与自更新

## 机器码（硬件绑定）
- 将主硬盘序列号 + 主 MAC 列表组合，统一大写，用 `|` 连接后做 SHA256。
- Python 片段（Win/Mac/Linux 通用）：
  ```python
  import hashlib, uuid, subprocess, platform, json

  def disk_serial():
      system = platform.system()
      try:
          if system == "Windows":
              out = subprocess.check_output(["wmic", "diskdrive", "get", "SerialNumber"], text=True)
              return next((s.strip() for s in out.splitlines() if s.strip() and s.strip() != "SerialNumber"), "")
          if system == "Darwin":
              out = subprocess.check_output(["system_profiler", "SPStorageDataType", "-json"], text=True)
              data = json.loads(out)
              for item in data.get("SPStorageDataType", []):
                  sn = item.get("device_serial") or item.get("bus_serial_number")
                  if sn:
                      return sn
          # Linux
          out = subprocess.check_output(["lsblk", "-ndo", "SERIAL"], text=True)
          return next((s.strip() for s in out.splitlines() if s.strip()), "")
      except Exception:
          return ""  # 允许为空，仍会和 MAC 一起哈希

  def machine_code():
      macs = sorted({uuid.getnode(), *[uuid.getnode()]})  # 如需多网卡可替换此处
      parts = [disk_serial()] + [hex(m)[2:] for m in macs if m]
      raw = "|".join(p.upper() for p in parts if p)
      return hashlib.sha256(raw.encode()).hexdigest()
  ```
- 本地缓存机器码；每次运行重算并比对，检测伪造。

## 激活流程
1. 收集 `machine_code`、`license_key`、`tool_version`、`os`、`hostname`。
2. 发送 `POST /activate`，Body 为 JSON。
3. 服务器响应示例：
   ```json
   {"status":"ok","token":"<jwt-or-random>","expires_at":"2026-12-31T23:59:59Z","features":{"mirror":"aliyun","ci":true}}
   ```
4. 成功后保存 token 到 `%ProgramData%/as-bootstrap/license.json`（Windows）或 `/usr/local/share/as-bootstrap/license.json`（类 Unix），包含 `machine_code`、`expires_at`、`signature`。
5. 每次运行校验：
   - token 存在且未过期
   - `machine_code` 与当前一致
   - 服务器签名/HMAC（推荐 JWT）验证通过
6. 失败时提示重新激活；CI 可允许 `--offline-grace DAYS`（到期 + 宽限 > 当前）。

## 通过 version.json 自更新
- 将 `version.json` 部署在稳定的 HTTPS 地址，保持精简、可缓存。
- 示例结构：
  ```json
  {
    "version": "1.3.0",
    "min_os": {"windows": "10", "macos": "13", "linux": "20.04"},
    "url": "https://cdn.example.com/as-bootstrap-1.3.0.zip",
    "sha256": "<zip checksum>",
    "changelog": ["JDK 21 optional", "Auto NDK install", "Mirror fallback"],
    "force": false
  }
  ```
- 客户端逻辑：
  - 与本地 `LOCAL_VERSION` 做语义化对比。
  - 远端更新或 `force=true` 时下载 zip 到临时目录，校验 SHA256，替换本地脚本后重启。
  - 本地配置（镜像、激活 token）单独目录保存。

## 防破解与加固
- 所有下载走 HTTPS 并校验 SHA256。
- 激活 token 需签名（JWT/HMAC），包含 `machine_code`、`expires_at`、`license_id`，离线可验。
- 若分发二进制，可做混淆/壳（PyInstaller + 字节码加密），授权校验尽量放服务器。
- 激活接口按 IP + machine_code 限流；可通过黑名单撤销 token ID。
- 记录激活成功/失败日志用于审计，避免收集 PII。

## 最小激活 API 契约
- `POST /activate` → 返回 token、过期时间、特性开关或错误码（`invalid_key`、`expired_key`、`too_many_devices`）。
- 可选 `POST /deactivate` 释放授权。
- 可选 `GET /status?token=` 便于排查。

