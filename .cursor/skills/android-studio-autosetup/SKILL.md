---
name: android-studio-autosetup
description: 一键在 Windows/macOS/Linux 上完成 Android Studio、JDK、Android SDK/NDK、Gradle 的安装与镜像配置，写入环境变量，支持 version.json 自更新和绑定硬件的激活校验。用于新环境搭建或修复 Android 开发环境，也可在开发安装工具时调用。
---

# Android Studio 自动部署

## 快速上手
- 先看 `references/os-playbooks.md`，按操作系统查安装命令和镜像选择。
- 在 `scripts/bootstrap.py` 配置默认接口/镜像（`VERSION_URL`、`DEFAULT_MIRROR`、`ACTIVATION_URL`）。
- 目标机器先做 Dry-Run：`python scripts/bootstrap.py --version-url http://your-domain/version.json --activation-url https://your-domain/activate`。
- 确认输出无误后加 `--apply` 真正执行，完成 AS/JDK/SDK/NDK/Gradle 安装、环境变量写入与常见问题修复。

## 核心流程（按顺序执行）
1) **平台检测**：用 `platform.system()` / `platform.machine()` 选择对应 playbook。
2) **自更新**：拉取 `version.json`（格式见 `references/licensing-update.md`），远端版本高于本地则下载包并重新运行。
3) **依赖检查**：确保包管理器可用（`winget|choco`、`brew`、`apt/yum`），必要时申请 sudo/管理员。
4) **JDK 与 Gradle**：安装或固定 Temurin/Corretto 17 或 21；写入 `JAVA_HOME`、`GRADLE_HOME` 并追加 PATH。
5) **Android Studio**：安装稳定版或指定渠道（channel 参数）；优先官方包管理器，失败再直链下载并校验。
6) **Android SDK/NDK**：通过 `sdkmanager` 安装 cmdline-tools、platform-tools、build-tools 及所需 API/NDK；在国内可加镜像/代理。
7) **环境变量**：写入 shell rc 与系统级位置；用 `java -version`、`gradle -v`、`sdkmanager --list` 验证。
8) **常见修复**：清理缓存（Gradle/SDK）、重置代理与镜像、检查模拟器加速（HAXM/Hyper-V/AVD）、修复权限。
9) **激活**：生成机器码（硬盘 + MAC 哈希）提交激活 API；将授权 token 本地缓存并校验是否被篡改。

## 文件索引
- `scripts/bootstrap.py`：跨平台安装/修复主脚本（默认 dry-run，`--apply` 执行）。
- `references/os-playbooks.md`：按操作系统列出的安装命令、环境变量、镜像与修复步骤。
- `references/licensing-update.md`：机器码生成、激活流程、version.json 结构、防破解要点。

## 使用场景
- **新机快速搭建**：先 dry-run 确认步骤，再 `--apply` 一键装全套并写镜像/环境变量。
- **构建机修复**：`--apply --fix` 重新配置环境变量与镜像，补齐缺失 SDK，不动 Android Studio 主程序。
- **无头 CI**：`bootstrap.py --ci --apply --sdk "platforms;android-34,build-tools;34.0.0" --ndk 26.3.11579264` 预热代理机。
- **批量升级**：更新远端 `version.json` 并托管新版包，脚本下次运行自更新。
- **授权校验**：分发激活码，脚本绑定硬件哈希缓存 token，服务器响应可撤销。

## 测试清单
- Dry-run 与 apply 在：Windows 11、macOS 14+、Ubuntu 22.04（amd64/arm64）均通过。
- 安装后命令可用：`java -version`、`gradle -v`、`sdkmanager --list`、`adb version`。
- 环境变量在不同 shell 与重启后仍生效。
- 镜像配置生效（Gradle 与 SDK 下载）且关闭后可恢复官方源。
- 激活能拒绝伪造 MAC/硬盘与过期 token，离线宽限期按预期。

保持 SKILL 精简，细节放在 references 中按需查阅。

## 落地计划（面向傻瓜式一键安装）
1) **防呆改造**：脚本零参数自动运行；3 秒倒计时；自动选镜像（大陆 → aliyun）；失败输出可复制修复指令和日志路径；`--expert` 才显示全部参数。  
2) **配置与自更新**：支持同目录 `config.json`（命令行 > config > 默认）；version.json 自更新含 SHA256 校验，自动替换自身；激活接口自动调用，缺少激活码不阻塞。  
3) **安装/修复打磨**：缺少包管理器时给一键安装提示；安装后自动跑验证命令；`--fix` 清缓存重写环境变量，不重装 AS。  
4) **打包分发**：PyInstaller 生成 Windows EXE 与 macOS/Linux 单文件；附带样例 `config.json`、`version.json` 和校验值，生成极简 README（小白版）。  
5) **验证矩阵**：Win11、macOS 14、Ubuntu 22.04（amd64/arm64）各跑 Dry-Run+实际执行，记录耗时/镜像命中/常见报错与提示文案。  
6) **发布与回滚**：上传安装包+校验值到 CDN；version.json 控制升级/回滚（force 开关）。  
7) **后续可选**：GUI 壳、代理自动测速选镜像、预置 SDK 组合快捷档（Stable/NDK/CI）。

## 常见问题（FAQ）
- 缺少包管理器：Windows 找不到 winget 会自动切到 choco；两者都没有时尝试自动安装 choco（Dry-Run 只提示）。仍失败可手动装 App Installer 或 Chocolatey。
- sdkmanager 未找到：需先安装 cmdline-tools 并放到 `<sdk_root>/cmdline-tools/latest`；随后运行 `sdkmanager --sdk_root=<sdk_root> --licenses`。
- 激活未配置：未填 activation_url / license_key 时会跳过，不阻塞安装；填入后自动请求并缓存 token。
- 镜像选择不对：`mirror=auto` 按时区/Locale 判断；如需强制，config.json 或命令行 `--mirror official|aliyun|tencent`。
- PATH 重复或脏：脚本追加环境变量，不清理旧值；可用 `--fix` 清缓存并重写环境变量（不重装 Android Studio）。
- 只想看当前环境状态：使用 `python scripts/bootstrap.py --status`，会列出包管理器/JDK/Gradle/sdkmanager/ADB 及环境变量的 ✓/✗ 情况，不做任何安装。

