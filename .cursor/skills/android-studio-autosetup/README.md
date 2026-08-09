# Android Studio 自动部署 - 使用指引（最小原则）

脚本：`scripts/bootstrap.py`。目标：一键装/修 Android 开发环境，尽量不覆盖已有配置。

## 环境要求
- Python 3.8+
- Windows：winget 或 choco 任一可用（缺 winget 会尝试 choco；非管理员会降级到用户级写变量）

## 最小可试用
管理员 PowerShell：
```powershell
cd C:\Users\ws\.codex\skills\android-studio-autosetup\scripts
python bootstrap.py --apply --skip-sdkmanager --skip-gradle
```
含义：只确保 JDK/环境变量/IDE 检测，不安装 cmdline-tools 与 Gradle，避免破坏现有环境。日志：`%USERPROFILE%\.as-bootstrap\logs\latest.txt`

## 常用命令
- 仅查看状态（不安装）：`python bootstrap.py --status [--skip-sdkmanager] [--skip-gradle]`
- 完整安装（含 SDK/Gradle）：`python bootstrap.py --apply`
- 跳过 Gradle：`python bootstrap.py --apply --skip-gradle`
- 跳过 sdkmanager 提示：`python bootstrap.py --apply --skip-sdkmanager`
- 专家参数：`python bootstrap.py --expert -h`

## 参数开关（节选）
- `--skip-sdkmanager`：缺少 cmdline-tools 时不提示/不装。
- `--skip-gradle`：不安装 Gradle，不写 GRADLE_HOME。
- `--force-non-admin`：非管理员也强行继续（仅写用户变量，跳过包管理器安装）。
- `--no-elevate`：禁用自动提权（调试用）。

## 原子化步骤（Windows 示例）
- 前置：检测管理员权限与包管理器（winget/choco），只记录可用性。  
- Java (JDK17/21)：`java -version`；缺失则静默装 Temurin，设 `JAVA_HOME_PATH`（用户级），Path 追加 `%JAVA_HOME_PATH%\bin`。  
- Git：`git --version`；缺失则静默装 Git，设 `GIT_HOME_PATH`（系统或用户），Path 追加 `%GIT_HOME_PATH%\bin;%GIT_HOME_PATH%\cmd`。  
- Android Studio：查注册表/常见路径；缺失则下载安装包，验证 `studio64.exe` 存在。  
- ADB / platform-tools：`adb version`；缺则下载 platform-tools，设 `ANDROID_ADB_PATH`，Path 追加。  
- cmdline-tools + sdkmanager：若 `sdkmanager(.bat)` 不在 `C:\Android\sdk\cmdline-tools\latest\bin`，下载解压；验证 `sdkmanager --version`。  
- SDK 根与工具集：设 `ANDROID_SDK_ROOT`（默认 `C:\Android\sdk`），用 sdkmanager 安装 `platform-tools / platforms;android-34 / build-tools;34.0.0`（NDK 可选）。  
- Gradle（可选）：`gradle -v`；缺则下载官方 ZIP，设 `GRADLE_HOME_PATH`，Path 追加 `%GRADLE_HOME_PATH%\bin`。  
- Node/npm（可选）：`node -v` / `npm -v`；缺则装 Node LTS，可选 `NODE_HOME_PATH` + Path 追加。  
- 环境变量去重与备份：备份用户/系统 Path，去重，保持 `%VAR%` 占位符，不覆盖原有条目。  
- 最终健康检查：汇总已装版本、关键可执行路径、变量是否已设，输出日志与恢复脚本（可选）。

## 配置文件（可选）
复制 `config.sample.json` 为 `config.json`，可设置：
- `version_url`、`activation_url`、`license_key`
- `mirror`: auto/official/aliyun/tencent
- `sdk`、`ndk`、`channel`、`skip_sdkmanager`、`skip_gradle`
命令行 > config > 内置默认。

## 验证清单
```
python bootstrap.py --status
java -version
gradle -v             # 若未 skip-gradle
sdkmanager --list     # 若已安装 cmdline-tools
adb version
```

## 常见问题
- 已安装提示：choco 会提示 “already installed”，不影响后续；如需重装用 `choco upgrade <pkg> -y`.
- sdkmanager 缺失：安装 “Android SDK Command-line Tools (latest)” 到 `%ANDROID_SDK_ROOT%\cmdline-tools\latest`，或使用 `--skip-sdkmanager`.
- Path 截断/覆盖：脚本追加前会去重并使用 `%VAR%` 占位符；仍有异常可用备份文件恢复（脚本执行时会在用户目录生成 `path_backup_*`）。

## 卸载/清理（Windows）
- `choco uninstall androidstudio gradle temurin17`（按需）
- 删除 SDK/Gradle 目录：`C:\Android\sdk`，`C:\Gradle\`
- 日志：`%USERPROFILE%\.as-bootstrap\logs`
