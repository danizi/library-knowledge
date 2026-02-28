#!/usr/bin/env python3
"""
跨平台 Android Studio + JDK/SDK/Gradle 引导脚本（小白友好版）。
- 零参数自动模式：可读取 config.json（可选），否则使用内置默认；自动选镜像；默认先 Dry-Run，再倒计时 3 秒自动执行。
- 支持 version.json 自更新 + 激活；失败不阻塞安装。
"""

import argparse
import json
import os
import platform
import shutil
import subprocess
import sys
import tempfile
import urllib.request
import datetime
import time
from hashlib import sha256
from pathlib import Path
from typing import Dict, List, Tuple

LOCAL_VERSION = "0.1.0"
DEFAULT_MIRROR = "official"  # options: official, aliyun, tencent
CONFIG_DIR = Path(os.environ.get("AS_BOOTSTRAP_HOME", Path.home() / ".as-bootstrap"))
TOKEN_PATH = CONFIG_DIR / "license.json"
LOG_DIR = CONFIG_DIR / "logs"
LOG_FILE = LOG_DIR / "latest.txt"

MIRRORS = {
    "official": {
        "gradle": "https://services.gradle.org/distributions/gradle-{version}-bin.zip",
        "sdk": None,
    },
    "aliyun": {
        "gradle": "https://mirrors.aliyun.com/gradle/gradle-{version}-bin.zip",
        "sdk": "https://mirrors.aliyun.com/android/repository/",
    },
    "tencent": {
        "gradle": "https://mirrors.cloud.tencent.com/gradle/gradle-{version}-bin.zip",
        "sdk": "https://mirrors.cloud.tencent.com/android/repository/",
    },
}

DEFAULTS = {
    "Windows": {
        "sdk_root": r"C:\\Android\\sdk",
        "java_home": r"C:\\Program Files\\Eclipse Adoptium\\jdk-17",
        "gradle_home": r"C:\\Gradle\\gradle-8.6",
    },
    "Darwin": {
        "sdk_root": str(Path.home() / "Library" / "Android" / "sdk"),
        "java_home": "/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home",
        "gradle_home": "/usr/local/opt/gradle",
    },
    "Linux": {
        "sdk_root": "/opt/android-sdk",
        "java_home": "/usr/lib/jvm/java-17-openjdk-amd64",
        "gradle_home": "/opt/gradle",
    },
}

SDK_PACKAGES_DEFAULT = [
    "platform-tools",
    "platforms;android-34",
    "build-tools;34.0.0",
    "ndk;26.3.11579264",
]


def log(msg: str) -> None:
    """Print to stdout and append to latest.txt (best-effort, 忽略写入异常)."""
    print(msg, flush=True)
    try:
        LOG_DIR.mkdir(parents=True, exist_ok=True)
        with LOG_FILE.open("a", encoding="utf-8") as f:
            f.write(msg + "\n")
    except Exception:
        pass


def run_cmd(cmd: List[str], apply: bool, check: bool = True) -> subprocess.CompletedProcess:
    """统一的命令执行/模拟入口；apply=False 时只打印不执行。"""
    pretty = " ".join(cmd)
    if not apply:
        log(f"[DRY] {pretty}")
        return subprocess.CompletedProcess(cmd, 0)
    log(f"[RUN] {pretty}")
    return subprocess.run(cmd, check=check)


def is_tool(name: str) -> bool:
    """判断可执行是否在 PATH 中。"""
    return shutil.which(name) is not None


def is_admin() -> bool:
    """检测是否管理员/root，用于决定系统级写入和包管理器。"""
    if detect_os() != "Windows":
        return os.geteuid() == 0
    try:
        import ctypes

        return ctypes.windll.shell32.IsUserAnAdmin()
    except Exception:
        return False


def detect_os() -> str:
    """返回平台字符串 Windows/Darwin/Linux。"""
    return platform.system()


def wpath(p: str) -> str:
    """Windows 路径规范化（去重复斜杠、统一为反斜杠）。非 Windows 原样返回。"""
    if detect_os() != "Windows":
        return p
    return str(Path(p)).replace("/", "\\")


def disp_path(val: str) -> str:
    """仅用于输出展示；Windows 下用 / 显示，避免 \\ 视觉干扰。"""
    if detect_os() != "Windows":
        return val
    return val.replace("\\", "/")


def http_get_json(url: str) -> Dict:
    """GET 并解析 JSON（假定可信 host）。"""
    with urllib.request.urlopen(url) as resp:  # nosec - trusted host provided by operator
        return json.loads(resp.read().decode())


def compare_versions(remote: str, local: str) -> int:
    def split(v):
        return [int(x) for x in v.split(".") if x.isdigit()]

    r, l = split(remote), split(local)
    for a, b in zip(r, l):
        if a != b:
            return 1 if a > b else -1
    return 1 if len(r) > len(l) else (-1 if len(r) < len(l) else 0)


def self_update(version_url: str, apply: bool) -> None:
    """自更新：拉取 version.json，校验版本与 sha，并下载包（不自动替换）。"""
    if not version_url:
        return
    try:
        data = http_get_json(version_url)
        remote_ver = data.get("version")
        if not remote_ver:
            return
        cmp = compare_versions(remote_ver, LOCAL_VERSION)
        if cmp <= 0:
            log(f"自更新：本地 {LOCAL_VERSION} 已是最新")
            return
        url = data.get("url")
        sha = data.get("sha256")
        log(f"发现新版本 -> {remote_ver}，下载地址: {url}")
        if not apply:
            log("(dry-run) 跳过下载")
            return
        if not url:
            log("缺少下载地址，终止更新")
            return
        with urllib.request.urlopen(url) as resp:
            payload = resp.read()
        if sha:
            digest = sha256(payload).hexdigest()
            if digest.lower() != sha.lower():
                raise RuntimeError("更新包校验失败")
        temp_zip = Path(tempfile.mkdtemp()) / "update.zip"
        temp_zip.write_bytes(payload)
        log(f"已下载更新到 {temp_zip}，请手动解压替换（自动替换未实现）。")
    except Exception as exc:  # pragma: no cover - best effort
        log(f"自更新跳过：{exc}")


def machine_code() -> str:
    """生成硬件指纹（磁盘序列+MAC），用于激活绑定。"""
    parts = []
    try:
        system = platform.system()
        if system == "Windows":
            out = subprocess.check_output(["wmic", "diskdrive", "get", "SerialNumber"], text=True)
            parts += [x.strip() for x in out.splitlines() if x.strip() and x.strip() != "SerialNumber"]
        elif system == "Darwin":
            out = subprocess.check_output(["system_profiler", "SPStorageDataType", "-json"], text=True)
            data = json.loads(out)
            for item in data.get("SPStorageDataType", []):
                sn = item.get("device_serial") or item.get("bus_serial_number")
                if sn:
                    parts.append(sn)
        else:
            out = subprocess.check_output(["lsblk", "-ndo", "SERIAL"], text=True)
            parts += [x.strip() for x in out.splitlines() if x.strip()]
    except Exception:
        pass
    parts.append(hex(platform.node().__hash__() & 0xFFFFFFFF))
    raw = "|".join(p.upper() for p in parts if p)
    return sha256(raw.encode()).hexdigest()


def activate(activation_url: str, license_key: str, apply: bool) -> None:
    """激活：POST 机器码+激活码；缓存 token。缺参数时跳过。"""
    if not activation_url or not license_key:
        log("未提供 activation_url 或 license_key，跳过激活")
        return
    CONFIG_DIR.mkdir(parents=True, exist_ok=True)
    payload = {
        "machine_code": machine_code(),
        "license_key": license_key,
        "version": LOCAL_VERSION,
        "os": detect_os(),
    }
    data = json.dumps(payload).encode()
    req = urllib.request.Request(activation_url, data=data, headers={"Content-Type": "application/json"})
    try:
        if not apply:
            log(f"[DRY] POST {activation_url} payload={payload}")
            return
        with urllib.request.urlopen(req) as resp:  # nosec - trusted host provided by operator
            resp_data = json.loads(resp.read().decode())
        token = resp_data.get("token")
        if not token:
            raise RuntimeError(f"激活失败: {resp_data}")
        TOKEN_PATH.write_text(json.dumps(resp_data, indent=2))
        log(f"激活成功，token 已保存至 {TOKEN_PATH}")
    except Exception as exc:  # pragma: no cover
        log(f"激活出错：{exc}")


def add_env_exports(os_name: str, sdk_root: str, java_home: str, gradle_home: str, apply: bool, skip_gradle: bool) -> None:
    """写入环境变量并追加 PATH；Windows 用 winreg 避免 setx 截断。可选跳过 Gradle 相关条目。"""
    if os_name == "Windows":
        admin = is_admin()
        scope_flag = "/M" if admin else ""  # 无 /M 则写当前用户
        sdk_root_w = wpath(sdk_root)
        java_home_w = wpath(java_home)
        gradle_home_w = wpath(gradle_home)
        # 使用 winreg 直接写环境变量，避免 setx 长度截断
        try:
            import winreg  # type: ignore
        except Exception:
            winreg = None

        def set_env_win(name: str, value: str, admin_scope: bool) -> None:
            if not apply:
                log(f"[DRY] set {name}={'(hidden)' if name=='Path' else value} scope={'Machine' if admin_scope else 'User'}")
                return
            if not winreg:
                run_cmd(["setx", name, value, "/M" if admin_scope else ""], apply, check=False)
                return
            root = winreg.HKEY_LOCAL_MACHINE if admin_scope else winreg.HKEY_CURRENT_USER
            subkey = r"SYSTEM\CurrentControlSet\Control\Session Manager\Environment" if admin_scope else r"Environment"
            try:
                key = winreg.CreateKeyEx(root, subkey, 0, winreg.KEY_SET_VALUE)
                winreg.SetValueEx(key, name, 0, winreg.REG_EXPAND_SZ, value)
                winreg.CloseKey(key)
            except Exception as exc:
                log(f"[WARN] 写入 {name} 失败，回退 setx：{exc}")
                run_cmd(["setx", name, value, "/M" if admin_scope else ""], apply, check=False)

        def append_path(entries: List[str], admin_scope: bool) -> None:
            """读取当前 Path，追加缺失项后写回，避免覆盖已有条目。"""
            current = ""
            if winreg:
                try:
                    root = winreg.HKEY_LOCAL_MACHINE if admin_scope else winreg.HKEY_CURRENT_USER
                    subkey = r"SYSTEM\CurrentControlSet\Control\Session Manager\Environment" if admin_scope else r"Environment"
                    with winreg.OpenKey(root, subkey) as key:
                        current, _ = winreg.QueryValueEx(key, "Path")
                except OSError:
                    current = os.environ.get("Path", "")
            else:
                current = os.environ.get("Path", "")
            parts = [p.strip().strip('"').replace("\\\\", "\\") for p in current.split(";") if p.strip()]
            existing_lower = {p.lower() for p in parts}
            for e in entries:
                norm = e.replace("\\\\", "\\")
                if norm.lower() not in existing_lower:
                    parts.append(norm)
                    existing_lower.add(norm.lower())
            new_val = ";".join(parts)
            set_env_win("Path", new_val, admin_scope)

        # 写变量
        set_env_win("ANDROID_SDK_ROOT", sdk_root_w, admin)
        set_env_win("JAVA_HOME", java_home_w, admin)
        if not skip_gradle:
            set_env_win("GRADLE_HOME", gradle_home_w, admin)
        append_entries = [
            "%ANDROID_SDK_ROOT%\\platform-tools",
            "%ANDROID_SDK_ROOT%\\emulator",
            "%JAVA_HOME%\\bin",
        ]
        if not skip_gradle:
            append_entries.append("%GRADLE_HOME%\\bin")
        append_path(append_entries, admin)
        if not admin:
            log("[!!] 未以管理员运行，环境变量已写入当前用户。如需系统级，请用管理员重跑。")
    else:
        export_lines = [
            f"export ANDROID_SDK_ROOT=\"{sdk_root}\"",
            f"export JAVA_HOME=\"{java_home}\"",
            f"export GRADLE_HOME=\"{gradle_home}\"",
            "export PATH=\"$PATH:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/emulator:$GRADLE_HOME/bin:$JAVA_HOME/bin\"",
        ]
        shell_rc = Path.home() / (".zshrc" if os.environ.get("SHELL", "").endswith("zsh") else ".bashrc")
        if apply:
            shell_rc.write_text(shell_rc.read_text() + "\n" + "\n".join(export_lines) if shell_rc.exists() else "\n".join(export_lines))
            log(f"已写入环境变量到 {shell_rc}")
        else:
            log("[DRY] 请在 shell rc 中添加：")
            for line in export_lines:
                log(f"  {line}")


def write_gradle_mirror(mirror: str, apply: bool) -> None:
    """设置 Gradle 下载镜像到用户 gradle.properties。"""
    url_template = MIRRORS[mirror]["gradle"]
    if not url_template:
        log("使用官方源，跳过镜像配置")
        return
    distro_url = url_template.format(version="8.6")
    gradle_props = Path.home() / ".gradle" / "gradle.properties"
    content = f"distributionUrl={distro_url}\n"
    if apply:
        gradle_props.parent.mkdir(parents=True, exist_ok=True)
        gradle_props.write_text(content)
        log(f"已在 {gradle_props} 设置 Gradle 镜像")
    else:
        log(f"[DRY] 将在 {gradle_props} 写入：{content.strip()}")


def sdkmanager_path(os_name: str, sdk_root: str) -> Path:
    """返回 sdkmanager 路径，根据平台选择 .bat/无扩展。"""
    base = Path(wpath(sdk_root))
    if os_name == "Windows":
        return base / "cmdline-tools" / "latest" / "bin" / "sdkmanager.bat"
    return base / "cmdline-tools" / "latest" / "bin" / "sdkmanager"


def sdk_install(os_name: str, sdk_root: str, packages: List[str], mirror: str, apply: bool, skip: bool) -> None:
    """使用 sdkmanager 安装指定包并自动接受 licenses。"""
    sdk_bin = sdkmanager_path(os_name, sdk_root)
    if not sdk_bin.exists():
        if skip:
            log(f"[SKIP] 未找到 sdkmanager：{sdk_bin}；已启用 skip-sdkmanager，不再提示/安装。")
        else:
            log(f"未找到 sdkmanager：{sdk_bin}，请先安装 cmdline-tools（或加 --skip-sdkmanager 忽略）。")
        return
    cmd = [str(sdk_bin), f"--sdk_root={sdk_root}"] + packages
    if MIRRORS[mirror]["sdk"]:
        url = MIRRORS[mirror]["sdk"]
        cmd.insert(1, f"--download-url={url}")
        cmd.insert(1, "--no_https")
    run_cmd(cmd, apply)
    # Accept licenses non-interactively
    run_cmd([str(sdk_bin), f"--sdk_root={sdk_root}", "--licenses"], apply, check=False)


def build_playbook(os_name: str, sdk_root: str, channel: str, pm: str, skip_gradle: bool) -> List[Tuple[str, List[List[str]]]]:
    """生成按平台和包管理器的安装命令列表。pm='none' 时返回空。"""
    tasks: List[Tuple[str, List[List[str]]]] = []
    if pm == "none":
        return tasks
    if os_name == "Windows":
        if pm == "winget":
            tasks.append(("检测包管理器", [["winget", "--version"]]))
            tasks.append(("安装 JDK 17", [["winget", "install", "-e", "--id", "EclipseAdoptium.Temurin.17.JDK"]]))
            if not skip_gradle:
                tasks.append(("安装 Gradle", [["winget", "install", "-e", "--id", "Gradle.Gradle"]]))
            studio_id = "Google.AndroidStudio" if channel == "stable" else "Google.AndroidStudio.Preview"
            tasks.append(("安装 Android Studio", [["winget", "install", "-e", "--id", studio_id]]))
        else:  # choco
            tasks.append(("检测包管理器", [["choco", "-v"]]))
            tasks.append(("安装 JDK 17", [["choco", "install", "-y", "temurin17"]] ))
            if not skip_gradle:
                tasks.append(("安装 Gradle", [["choco", "install", "-y", "gradle"]]))
            tasks.append(("安装 Android Studio", [["choco", "install", "-y", "androidstudio"]]))
        tasks.append(("确保 cmdline-tools 目录存在", [["powershell", "-Command", f"if(!(Test-Path '{sdk_root}')){{New-Item -ItemType Directory -Path '{sdk_root}'}}"]]))
    elif os_name == "Darwin":
        tasks.append(("检测包管理器", [["brew", "--version"]]))
        tasks.append(("安装 JDK 17", [["brew", "install", "--cask", "temurin17"]]))
        if not skip_gradle:
            tasks.append(("安装 Gradle", [["brew", "install", "gradle"]]))
        studio_cask = "android-studio" if channel == "stable" else "android-studio-preview"
        tasks.append(("安装 Android Studio", [["brew", "install", "--cask", studio_cask]]))
        tasks.append(("安装 cmdline-tools", [["brew", "install", "--cask", "android-commandlinetools"]]))
    else:  # Linux
        tasks.append(("更新 apt", [["sudo", "apt-get", "update"]]))
        tasks.append(("安装 JDK 17", [["sudo", "apt-get", "install", "-y", "openjdk-17-jdk"]]))
        if not skip_gradle:
            tasks.append(("安装 Gradle", [["sudo", "apt-get", "install", "-y", "gradle"]]))
        tasks.append(("创建 sdk 根目录", [["sudo", "mkdir", "-p", sdk_root], ["sudo", "chown", f"{os.getuid()}:{os.getgid()}", sdk_root]]))
    return tasks


def cleanup(os_name: str, sdk_root: str, apply: bool) -> None:
    """清理 Gradle/SDK 缓存目录。"""
    gradle_cache = Path.home() / ".gradle" / "caches"
    sdk_cache = Path(sdk_root) / ".android" / "cache"
    for path in [gradle_cache, sdk_cache]:
        if path.exists():
            if apply:
                shutil.rmtree(path, ignore_errors=True)
                log(f"已删除缓存 {path}")
            else:
                log(f"[DRY] 将删除缓存 {path}")


def parse_args() -> argparse.Namespace:
    """解析命令行参数，支持 status/force-non-admin/no-elevate 等模式。"""
    parser = argparse.ArgumentParser(description="Android Studio 自动部署", add_help=False)
    parser.add_argument("--expert", action="store_true", help="显示全部参数并跳过防呆提示")
    parser.add_argument("--help", "-h", action="store_true", help="显示帮助")
    parser.add_argument("--status", action="store_true", help="仅检测当前环境状态，不做安装")
    parser.add_argument("--force-non-admin", action="store_true", help="在 Windows 非管理员模式下继续（跳过 choco/系统级变量）")
    parser.add_argument("--no-elevate", action="store_true", help="禁用自动提权（调试用）")
    parser.add_argument("--skip-sdkmanager", action="store_true", help="缺少 cmdline-tools 时跳过 sdkmanager 相关步骤与提示")
    parser.add_argument("--skip-gradle", action="store_true", help="跳过 Gradle 安装与 GRADLE_HOME/Path 写入")
    parser.add_argument("--version-url", help="自更新用 version.json 地址")
    parser.add_argument("--activation-url", help="激活接口地址")
    parser.add_argument("--license-key", help="激活码")
    parser.add_argument("--mirror", choices=list(MIRRORS.keys()) + ["auto"], default="auto", help="镜像：auto/official/aliyun/tencent")
    parser.add_argument("--sdk-root", help="自定义 ANDROID_SDK_ROOT")
    parser.add_argument("--java-home", help="自定义 JAVA_HOME")
    parser.add_argument("--gradle-home", help="自定义 GRADLE_HOME")
    parser.add_argument("--sdk", help="sdkmanager 包列表（逗号分隔）")
    parser.add_argument("--ndk", help="覆盖 NDK 版本（追加 ndk;X）")
    parser.add_argument("--channel", choices=["stable", "preview"], help="Android Studio 渠道")
    parser.add_argument("--apply", action="store_true", help="执行命令（默认先 dry-run 后倒计时自动执行）")
    parser.add_argument("--fix", action="store_true", help="清缓存并重写环境变量")
    parser.add_argument("--ci", action="store_true", help="CI 模式：尽量无交互")
    return parser.parse_args()


def load_config() -> Dict:
    """读取同目录 config.json，失败返回空 dict。"""
    cfg_path = Path(__file__).resolve().parent / "config.json"
    if not cfg_path.exists():
        return {}
    try:
        return json.loads(cfg_path.read_text(encoding="utf-8"))
    except Exception as exc:
        log(f"读取 config.json 失败：{exc}，使用内置默认")
        return {}


def choose_mirror_auto() -> str:
    """按时区/Locale 粗略判断是否在国内，自动选择镜像。"""
    try:
        offset = datetime.datetime.now().astimezone().utcoffset()
        if offset and abs(offset.total_seconds() - 8 * 3600) < 1800:
            return "aliyun"
    except Exception:
        pass
    try:
        import locale

        loc = ".".join([x for x in locale.getdefaultlocale() if x])
        if "CN" in loc.upper():
            return "aliyun"
    except Exception:
        pass
    return "official"


def merge_config(args: argparse.Namespace) -> Dict:
    """合并优先级：命令行 > config.json > 内置默认；同时决定镜像/路径等。"""
    cfg = load_config()
    os_name = detect_os()
    defaults = DEFAULTS.get(os_name, DEFAULTS["Linux"])
    merged = {
        "version_url": cfg.get("version_url"),
        "activation_url": cfg.get("activation_url"),
        "license_key": cfg.get("license_key"),
        "mirror": cfg.get("mirror", "auto"),
        "sdk_root": cfg.get("sdk_root", defaults["sdk_root"]),
        "java_home": cfg.get("java_home", defaults["java_home"]),
        "gradle_home": cfg.get("gradle_home", defaults["gradle_home"]),
        "sdk": cfg.get("sdk", ",".join(SDK_PACKAGES_DEFAULT)),
        "ndk": cfg.get("ndk"),
        "channel": cfg.get("channel", "stable"),
        "apply": cfg.get("apply", False),
        "fix": cfg.get("fix", False),
        "ci": cfg.get("ci", False),
        "force_non_admin": cfg.get("force_non_admin", False),
        "no_elevate": cfg.get("no_elevate", False),
        "skip_sdkmanager": cfg.get("skip_sdkmanager", False),
        "skip_gradle": cfg.get("skip_gradle", False),
    }
    # CLI 覆盖配置
    for key in ["version_url", "activation_url", "license_key", "mirror", "sdk_root", "java_home", "gradle_home", "sdk", "ndk", "channel"]:
        val = getattr(args, key.replace("-", "_"), None)
        if val is not None:
            merged[key] = val
    for flag in ["apply", "fix", "ci", "skip_sdkmanager", "skip_gradle"]:
        if getattr(args, flag, False):
            merged[flag] = True
    if getattr(args, "force_non_admin", False):
        merged["force_non_admin"] = True
    # status 模式强制不执行
    if getattr(args, "status", False):
        merged["apply"] = False
        merged["status"] = True
    else:
        merged["status"] = False
    if merged["mirror"] == "auto":
        merged["mirror"] = choose_mirror_auto()
    return merged


def countdown(seconds: int = 3) -> bool:
    """简单倒计时，给用户取消的机会。"""
    for i in range(seconds, 0, -1):
        log(f"{i} 秒后开始实际执行，按 Ctrl+C 取消...")
        time.sleep(1)
    return True


def relaunch_as_admin():
    """在 Windows 上请求以管理员重新运行当前脚本，保留参数。"""
    if detect_os() != "Windows":
        return False
    if os.environ.get("AS_BOOTSTRAP_ELEVATED") == "1":
        return False
    args = " ".join(f'"{a}"' for a in sys.argv[1:])
    cmd = [
        "powershell",
        "-Command",
        f"Start-Process python -ArgumentList '\"{Path(__file__).resolve()}\" {args}' -Verb RunAs -Wait -WindowStyle Normal",
    ]
    try:
        subprocess.run(cmd, check=True)
        return True
    except Exception as exc:
        log(f"自动提权失败：{exc}")
        return False


def choose_package_manager(os_name: str, cfg: Dict) -> str:
    """Windows 优先 winget，缺失则自动切换/安装 choco；其他平台返回 winget 默认值。"""
    pm = "winget"
    if os_name == "Windows" and not is_tool("winget"):
        if is_tool("choco"):
            return "choco"
        log("未找到 winget，尝试自动安装 Chocolatey...")
        if cfg["apply"]:
            try:
                run_cmd(
                    [
                        "powershell",
                        "-NoProfile",
                        "-ExecutionPolicy",
                        "Bypass",
                        "-Command",
                        "Set-ExecutionPolicy Bypass -Scope Process -Force; "
                        "[System.Net.ServicePointManager]::SecurityProtocol = "
                        "[System.Net.ServicePointManager]::SecurityProtocol -bor 3072; "
                        "iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))",
                    ],
                    True,
                    check=False,
                )
                if is_tool("choco"):
                    return "choco"
            except Exception as exc:
                log(f"自动安装 choco 失败：{exc}，将继续尝试 winget 路径")
        else:
            log("[DRY] 会自动安装 Chocolatey 作为 winget 兜底")
            return "choco"
    # 非管理员且未强制，尝试自动提权；失败则跳过安装
    if os_name == "Windows" and cfg["apply"] and not is_admin() and not cfg.get("force_non_admin"):
        if cfg.get("no_elevate"):
            log("[!!] 非管理员，且 no-elevate 已开启，跳过安装步骤。")
            return "none"
        log("[!!] 尝试以管理员重新运行脚本以完成安装...")
        os.environ["AS_BOOTSTRAP_ELEVATED"] = "1"
        if relaunch_as_admin():
            log("已在管理员窗口执行完毕。")
        else:
            log("[!!] 自动提权失败，跳过安装。可手动用管理员运行或使用 --force-non-admin。")
        return "none"
    if os_name == "Windows" and cfg.get("force_non_admin"):
        log("[!!] 非管理员模式：跳过 winget/choco 安装，仅保留状态检查和用户级环境变量。")
        return "none"
    return pm


def status_report(cfg: Dict) -> None:
    """仅检测当前环境状态，不做安装，输出图标化列表。"""
    os_name = detect_os()
    ok_icon = "[OK]"
    bad_icon = "[X ]"
    warn_icon = "[!!]"
    rows = []

    # 计算“有效” SDK 路径：优先环境变量 ANDROID_SDK_ROOT，其次 ANDROID_HOME，最后配置默认值
    env_sdk_root = os.environ.get("ANDROID_SDK_ROOT") or os.environ.get("ANDROID_HOME")
    sdk_root_effective = Path(env_sdk_root) if env_sdk_root else Path(cfg["sdk_root"])

    def has_android_studio() -> Tuple[bool, str]:
        """尝试通过注册表/常见路径检测 Android Studio。"""
        # Windows: 查询注册表卸载项 DisplayName，提取 InstallLocation 或 DisplayIcon
        if os_name == "Windows":
            try:
                import winreg  # type: ignore

                reg_paths = [
                    r"SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall",
                    r"SOFTWARE\WOW6432Node\Microsoft\Windows\CurrentVersion\Uninstall",
                    r"SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall",  # HKCU
                ]
                hives = [
                    (winreg.HKEY_LOCAL_MACHINE, reg_paths[0]),
                    (winreg.HKEY_LOCAL_MACHINE, reg_paths[1]),
                    (winreg.HKEY_CURRENT_USER, reg_paths[2]),
                ]
                for hive, reg_path in hives:
                    with winreg.OpenKey(hive, reg_path) as root:
                        for i in range(winreg.QueryInfoKey(root)[0]):
                            try:
                                sub = winreg.EnumKey(root, i)
                                with winreg.OpenKey(root, sub) as k:
                                    name, _ = winreg.QueryValueEx(k, "DisplayName")
                                    if "Android Studio" in name:
                                        try:
                                            install_loc, _ = winreg.QueryValueEx(k, "InstallLocation")
                                        except OSError:
                                            install_loc = ""
                                        try:
                                            display_icon, _ = winreg.QueryValueEx(k, "DisplayIcon")
                                        except OSError:
                                            display_icon = ""
                                        if install_loc:
                                            p = Path(install_loc)
                                            if p.exists():
                                                candidate = p / "bin" / "studio64.exe"
                                                if candidate.exists():
                                                    return True, str(candidate)
                                        if display_icon:
                                            p = Path(display_icon.replace('"', "")).resolve()
                                            if p.exists():
                                                return True, str(p)
                            except OSError:
                                continue
            except Exception:
                pass

            # 兜底：遍历常见盘符的 Program Files\Android\Android Studio
            for drive in "CDEFGHIJKLMNOPQRSTUVWXYZ":
                base_pf = Path(f"{drive}:\\Program Files")
                if base_pf.exists():
                    for exe in ["studio64.exe", "studio.exe"]:
                        p = base_pf / "Android" / "Android Studio" / "bin" / exe
                        if p.exists():
                            return True, str(p)

        if os_name == "Darwin":
            for p in [
                Path("/Applications/Android Studio.app/Contents/MacOS/studio"),
                Path("/Applications/Android Studio Preview.app/Contents/MacOS/studio"),
            ]:
                if p.exists():
                    return True, str(p)

        if os_name == "Linux":
            for p in [
                Path("/opt/android-studio/bin/studio.sh"),
                Path.home() / "android-studio/bin/studio.sh",
                Path("/usr/local/android-studio/bin/studio.sh"),
            ]:
                if p.exists():
                    return True, str(p)

        return False, "未在注册表/常见路径找到（可能未放默认目录）"

    # 包管理器
    if os_name == "Windows":
        rows.append(("包管理器 winget", is_tool("winget"), "winget"))
        rows.append(("包管理器 choco", is_tool("choco"), "choco"))
    elif os_name == "Darwin":
        rows.append(("包管理器 brew", is_tool("brew"), "brew"))
    else:
        rows.append(("包管理器 apt/yum", is_tool("apt-get") or is_tool("yum"), "apt 或 yum"))

    # 工具链
    rows.append(("Java (java)", is_tool("java"), "java"))
    rows.append(("Gradle (gradle)", is_tool("gradle"), "gradle"))
    sdk_bin_path = sdkmanager_path(os_name, sdk_root_effective)
    rows.append(("sdkmanager", sdk_bin_path.exists(), disp_path(str(sdk_bin_path))))
    rows.append(("ADB (adb)", is_tool("adb"), "adb"))
    ide_ok, ide_detail = has_android_studio()
    rows.append(("Android Studio (IDE)", ide_ok, disp_path(ide_detail)))

    # 环境变量
    env_checks = [
        ("ANDROID_SDK_ROOT", os.environ.get("ANDROID_SDK_ROOT")),
        ("ANDROID_HOME", os.environ.get("ANDROID_HOME")),
        ("JAVA_HOME", os.environ.get("JAVA_HOME")),
        ("GRADLE_HOME", os.environ.get("GRADLE_HOME")),
    ]
    for name, val in env_checks:
        rows.append((f"环境变量 {name}", bool(val), disp_path(val) if val else "未设置"))
    log("\n=== 环境状态检查 (status) ===")
    for name, ok, detail in rows:
        icon = ok_icon if ok else bad_icon
        log(f"{icon} {name} -> {detail}")
    log(f"{warn_icon} 提示：如需自动安装，请去掉 --status 直接运行。")


def main():
    """主入口：处理帮助/状态、合并配置、自更新、激活、安装与倒计时二次执行。"""
    args = parse_args()
    if args.help and not args.expert:
        print("零参数即可运行：先 Dry-Run，3 秒后自动执行；config.json 可填写接口/镜像/激活码。")
        print("高级参数请用 --expert -h")
        return
    if args.help and args.expert:
        argparse.ArgumentParser().print_help()
        return

    cfg = merge_config(args)
    if cfg["status"]:
        status_report(cfg)
        return
    os_name = detect_os()
    sdk_root = cfg["sdk_root"]
    java_home = cfg["java_home"]
    gradle_home = cfg["gradle_home"]

    log(f"OS={os_name} | sdk_root={sdk_root} | mirror={cfg['mirror']} | channel={cfg['channel']} | apply={cfg['apply']} | ci={cfg['ci']}")
    log(f"日志输出：{LOG_FILE}")

    self_update(cfg["version_url"], cfg["apply"])
    activate(cfg["activation_url"], cfg["license_key"], cfg["apply"])

    # Windows 自动选择包管理器（winget 不在则切 choco/自动安装）
    pm = choose_package_manager(os_name, cfg)

    playbook = build_playbook(os_name, sdk_root, cfg["channel"], pm, cfg["skip_gradle"])
    for title, cmds in playbook:
        log(f"\n== {title} ==")
        for cmd in cmds:
            run_cmd(cmd, cfg["apply"], check=False)

    packages = [p.strip() for p in cfg["sdk"].split(",") if p.strip()]
    if cfg["ndk"]:
        packages = [p for p in packages if not p.startswith("ndk;")] + [f"ndk;{cfg['ndk']}"]

    write_gradle_mirror(cfg["mirror"], cfg["apply"])
    add_env_exports(os_name, sdk_root, java_home, gradle_home, cfg["apply"], cfg["skip_gradle"])
    sdk_install(os_name, sdk_root, packages, cfg["mirror"], cfg["apply"], cfg["skip_sdkmanager"])

    if cfg["fix"]:
        cleanup(os_name, sdk_root, cfg["apply"])

    log("\n完成。请验证：java -version, gradle -v, sdkmanager --list, adb version。")


if __name__ == "__main__":
    try:
        # 第一次执行（默认 dry-run）
        main()
        args = parse_args()
        cfg = merge_config(args)
        # status 模式仅做检查，不进入后续倒计时/执行
        if cfg.get("status"):
            sys.exit(0)

        if not cfg["apply"] and not cfg["ci"]:
            log("\n上述为 Dry-Run 计划。")
            if countdown(3):
                cfg["apply"] = True
                log("\n开始实际执行...\n")
                # 二次执行：沿用同一配置，但 apply=True
                os_name = detect_os()
                sdk_root = cfg["sdk_root"]
                java_home = cfg["java_home"]
                gradle_home = cfg["gradle_home"]
                log(f"OS={os_name} | sdk_root={sdk_root} | mirror={cfg['mirror']} | channel={cfg['channel']} | apply={cfg['apply']} | ci={cfg['ci']}")
                self_update(cfg["version_url"], cfg["apply"])
                activate(cfg["activation_url"], cfg["license_key"], cfg["apply"])
                pm_apply = choose_package_manager(os_name, cfg)
                playbook = build_playbook(os_name, sdk_root, cfg["channel"], pm_apply, cfg["skip_gradle"])
                for title, cmds in playbook:
                    log(f"\n== {title} ==")
                    for cmd in cmds:
                        run_cmd(cmd, cfg["apply"], check=False)
                packages = [p.strip() for p in cfg["sdk"].split(",") if p.strip()]
                if cfg["ndk"]:
                    packages = [p for p in packages if not p.startswith("ndk;")] + [f"ndk;{cfg['ndk']}"]
                write_gradle_mirror(cfg["mirror"], cfg["apply"])
                add_env_exports(os_name, sdk_root, java_home, gradle_home, cfg["apply"], cfg["skip_gradle"])
                sdk_install(os_name, sdk_root, packages, cfg["mirror"], cfg["apply"], cfg["skip_sdkmanager"])
                if cfg["fix"]:
                    cleanup(os_name, sdk_root, cfg["apply"])
                log("\n完成实际执行。请验证：java -version, gradle -v, sdkmanager --list, adb version。")
    except KeyboardInterrupt:
        log("用户取消。")
    except Exception as exc:
        log(f"运行出错：{exc}\n请查看日志：{LOG_FILE}")
