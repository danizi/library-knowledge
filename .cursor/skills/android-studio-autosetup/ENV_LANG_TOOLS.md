# 常用工具环境配置全流程（面向小白，按平台编号目录）
面向 Java、ADB、Gradle、Git、Node、npm 六类工具。从 0 开始逐步检查→缺失则安装→写环境变量→验证→卸载/清理。所有命令都可以直接复制执行。已改为优先使用 Chocolatey（choco），无需 winget。

---
## 目录
- 0. Windows 一键最快路线（choco）
- 1. Windows 详细步骤（含检查/安装/路径/验证/卸载，choco 版）
- 2. macOS 详细步骤
- 3. Linux 详细步骤（Debian/Ubuntu 示例）
- 4. 通用验证清单
- 5. 常用路径与环境变量示例
- 6. Path 备份与还原（Windows）
- 7. 镜像与离线包下载位置

---
## 0. Windows 一键最快路线（使用 choco）
1. 以管理员身份运行 PowerShell。
2. 如未安装 choco，先执行（只需一次）：
   ```powershell
   Set-ExecutionPolicy Bypass -Scope Process -Force
   [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.SecurityProtocolType]::Tls12
   iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
   ```
   示例图：![choco 安装成功](./references/assets/image/choco_install.png)
3. 逐条安装常用工具：
   ```powershell
   choco install -y temurin17
   choco install -y git
   choco install -y nodejs-lts
   choco install -y androidstudio
   ```
4. 设置环境变量（用户级，安全且不覆盖现有 Path）：
   ```powershell
   [Environment]::SetEnvironmentVariable('ANDROID_SDK_ROOT','C:\Android\sdk','User')
   [Environment]::SetEnvironmentVariable('GRADLE_HOME','C:\Gradle\gradle-8.6','User')
   [Environment]::SetEnvironmentVariable('JAVA_HOME','C:\Program Files\Eclipse Adoptium\jdk-17','User')
   $raw=[Environment]::GetEnvironmentVariable('Path','User') -split ';' | ? { $_ }
   $pref='%JAVA_HOME%\bin','%ANDROID_SDK_ROOT%\platform-tools','%ANDROID_SDK_ROOT%\emulator','%GRADLE_HOME%\bin'
   $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase);$out=@()
   foreach($p in $pref+$raw){$p=$p.Trim('"').Replace('\\','\');if($seen.Add($p)){$out+=$p}}
   [Environment]::SetEnvironmentVariable('Path',($out -join ';'),'User')
   ```
5. 新开 PowerShell 验证：`java -version`、`git --version`、`node -v`、`npm -v`、`adb version`（未装 platform-tools 可先跳过）。
6. 需要 adb / sdkmanager 时看 1.5~1.7；需要 Gradle 时看 1.8（项目有 gradlew 可跳过）。

---
## 1. Windows 详细步骤（choco 版）
> 无管理员时，把所有 `setx ... /M` 改成不带 /M（写用户变量），安装命令可能失败需改为手动下载。

### 1.1 准备
- 不同身份打开cmd，身份分为普通身份和管理员身份(PowerShell)。
  - 普通身份
  ![alt text](image.png)
  - 管理员身份
  ![alt text](image-1.png)
- 安装 choco（见 0.2 步）。
- 建议先备份 Path：`Get-ItemProperty -Path 'HKCU:\Environment' -Name Path | Out-File "$env:USERPROFILE\path_backup_user.txt"`

### 1.2 Java (Temurin 17)
- **检查**：普通身份打开打开cmd，输入`java --version`，**若已配置跳过此章节**。
  ![alt text](./references/assets/image/java_version.png)
- **安装jdk**：`choco install -y temurin17` 默认下载路径是 `C:\Program Files\Eclipse Adoptium\jdk-17`
  示例图：![Temurin 安装输出](./references/assets/image/choco_temurin17.png)
- **环境变量配置**：`setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-17" /M`；Path 追加 `%JAVA_HOME%\bin`。
  - 创建变量JAVA_HOME 
    环境变量中有用户级和系统级变量之分，建议写入系统级变量中，这需要管理员身份权限，所有命令行带有/M
    `setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-17" /M`
  - 用户级追加（无需管理员，cmd/PowerShell 均可）：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','User') -split ';' | ? { $_ }; $add='%JAVA_HOME%\bin'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'User'); Write-Host 'User Path updated. Count:' $out.Count"
    ```
  - 系统级追加（需管理员）：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','Machine') -split ';' | ? { $_ }; $add='%JAVA_HOME%\bin'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'Machine'); Write-Host 'Machine Path updated. Count:' $out.Count"
    ```
  示例图：![系统级 Path 追加示例](./references/assets/image/env_path_machine.png)
- **验证**：`java -version` 看到 17 即 OK。
- **卸载/清理**：环境失效可执行 删除jdk文件`choco uninstall -y temurin17`或去掉环境变量（用户级 Path & JAVA_HOME） `%JAVA_HOME%\bin`；。
  - 一键清理（用户级 Path & JAVA_HOME）：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','User') -split ';' | ? { $_ -and $_ -notmatch '^%JAVA_HOME%\\\\bin$' }; [Environment]::SetEnvironmentVariable('Path',($raw -join ';'),'User'); [Environment]::SetEnvironmentVariable('JAVA_HOME',$null,'User'); Write-Host 'Removed JAVA_HOME and %JAVA_HOME%\bin from User Path.'"
    ```
  - 一键清理（系统级，需管理员）：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','Machine') -split ';' | ? { $_ -and $_ -notmatch '^%JAVA_HOME%\\\\bin$' }; [Environment]::SetEnvironmentVariable('Path',($raw -join ';'),'Machine'); [Environment]::SetEnvironmentVariable('JAVA_HOME',$null,'Machine'); Write-Host 'Removed JAVA_HOME and %JAVA_HOME%\bin from Machine Path.'"
    ```
### 1.3 Git
- **检查**：`git --version`
- **安装**：`choco install -y git`
- **默认路径**：`C:\Program Files\Git`
- **Path 追加（一般 choco 自动处理，未生效再用）**  
  - 用户级：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','User') -split ';' | ? { $_ }; $add='%ProgramFiles%\Git\cmd'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'User'); Write-Host 'User Path updated. Count:' $out.Count"
    ```
  - 系统级（需管理员）：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','Machine') -split ';' | ? { $_ }; $add='%ProgramFiles%\Git\cmd'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'Machine'); Write-Host 'Machine Path updated. Count:' $out.Count"
    ```
- **验证**：`git --version`
- **卸载/清理**：`choco uninstall -y git`；若需移除 Path，复用上面命令思路，将 `$add` 换成要删除的路径并在 `$raw` 过滤掉它。

### 1.4 Android Studio (IDE)
- **检查**：`C:\Program Files\Android\Android Studio\bin\studio64.exe` 是否存在，或能否启动。
- **安装**：`choco install -y androidstudio`
- **验证**：能启动 IDE 即可。
- **卸载**：`choco uninstall -y androidstudio`

### 1.5 ADB / platform-tools
- **检查**：`adb version`
- **下载**：`https://dl.google.com/android/repository/platform-tools-latest-windows.zip`
- **安装**：解压到 `C:\Android\sdk\platform-tools`
- **环境变量**：`setx ANDROID_SDK_ROOT "C:\Android\sdk" /M`；Path 追加 `%ANDROID_SDK_ROOT%\platform-tools`。
- **Path 追加命令**  
  - 用户级：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','User') -split ';' | ? { $_ }; $add='%ANDROID_SDK_ROOT%\platform-tools'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'User'); Write-Host 'User Path updated. Count:' $out.Count"
    ```
  - 系统级（需管理员）：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','Machine') -split ';' | ? { $_ }; $add='%ANDROID_SDK_ROOT%\platform-tools'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'Machine'); Write-Host 'Machine Path updated. Count:' $out.Count"
    ```
- 示例图：![platform-tools 解压路径](./references/assets/image/platform_tools_unzip.png)
- **验证**：`adb version`
- 验证示例：![adb 版本](./references/assets/image/adb_version_ok.png)
- **卸载/清理**：删除该目录并按上面命令思路移除 `%ANDROID_SDK_ROOT%\platform-tools`；必要时清空或重设 `ANDROID_SDK_ROOT`。

### 1.6 cmdline-tools + sdkmanager（可选，装 SDK 组件时必需）
- **检查**：`C:\Android\sdk\cmdline-tools\latest\bin\sdkmanager.bat --version`
- **下载**：`https://dl.google.com/android/repository/commandlinetools-win-9477386_latest.zip`
- **安装**：解压到 `C:\Android\sdk\cmdline-tools\latest`（要有 `bin\sdkmanager.bat`）。
- **验证**：同上命令。
- **卸载**：删除 `cmdline-tools\latest` 目录。

### 1.7 SDK 组件（可选，需要 1.6）
```powershell
C:\Android\sdk\cmdline-tools\latest\bin\sdkmanager.bat --sdk_root=%ANDROID_SDK_ROOT% ^
  "platform-tools" "platforms;android-34" "build-tools;34.0.0"
```
- **验证**：`sdkmanager --list` 能看到上述包。
- **卸载**：`sdkmanager --uninstall "<package>"` 或删对应目录。

### 1.8 Gradle（可选，项目有 gradlew 可跳过）
- **检查**：`gradle -v`
- **安装**：`choco install -y gradle` 或下载 ZIP 解压到 `C:\Gradle\gradle-x.y`
- **环境变量**：`setx GRADLE_HOME "C:\Gradle\gradle-x.y" /M`；Path 追加 `%GRADLE_HOME%\bin`
- **Path 追加命令**  
  - 用户级：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','User') -split ';' | ? { $_ }; $add='%GRADLE_HOME%\bin'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'User'); Write-Host 'User Path updated. Count:' $out.Count"
    ```
  - 系统级（需管理员）：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','Machine') -split ';' | ? { $_ }; $add='%GRADLE_HOME%\bin'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'Machine'); Write-Host 'Machine Path updated. Count:' $out.Count"
    ```
- **验证**：`gradle -v`
- 验证示例：![gradle 版本](./references/assets/image/gradle_v.png)
- **卸载/清理**：`choco uninstall -y gradle` 或删除目录；用上面命令思路过滤掉 `%GRADLE_HOME%\bin`，并可将 `GRADLE_HOME` 设为空。

### 1.9 Node / npm
- **检查**：`node -v`、`npm -v`
- **安装**：`choco install -y nodejs-lts`
- **默认路径**：`C:\Program Files\nodejs`
- **Path 追加兜底（安装后未生效时用）**  
  - 用户级：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','User') -split ';' | ? { $_ }; $add='%ProgramFiles%\nodejs'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'User'); Write-Host 'User Path updated. Count:' $out.Count"
    ```
  - 系统级（需管理员）：  
    ```cmd
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$raw=[Environment]::GetEnvironmentVariable('Path','Machine') -split ';' | ? { $_ }; $add='%ProgramFiles%\nodejs'; $seen=[System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase); $out=@(); foreach($p in $raw + $add){ $p=$p.Trim('\"').Replace('\\','\'); if($seen.Add($p)){ $out+=$p } } $new=($out -join ';'); [Environment]::SetEnvironmentVariable('Path',$new,'Machine'); Write-Host 'Machine Path updated. Count:' $out.Count"
    ```
- **验证**：`node -v`、`npm -v`
- **卸载/清理**：`choco uninstall -y nodejs-lts`；如需移除 Path，按上面命令思路过滤掉 `%ProgramFiles%\nodejs`。

### 1.10 Path 备份/还原与去重
- 备份用户 Path：`Get-ItemProperty -Path 'HKCU:\Environment' -Name Path | Out-File "$env:USERPROFILE\path_backup_user_final.txt"`
- 备份系统 Path：`Get-ItemProperty -Path 'HKLM:\SYSTEM\CurrentControlSet\Control\Session Manager\Environment' -Name Path | Out-File "$env:USERPROFILE\path_backup_machine_final.txt"`
- 去重/追加示例：见 `ENV_COMMANDS.md`。

### 1.11 常见清理思路
- 卸载首选 choco；残留目录手动删。
- 清 Path：在“系统属性→高级→环境变量”或用 `[Environment]::SetEnvironmentVariable` 重新写。
- 若 Path 被覆盖，用前面备份文件恢复。

---
## 2. macOS 详细步骤
（同前，略）

## 3. Linux 详细步骤（Debian/Ubuntu 示例）
（同前，略）

## 4. 通用验证清单
```
java -version
adb version
gradle -v
git --version
node -v
npm -v
# 若安装 sdkmanager：
sdkmanager --list
```

## 5. 常用路径与环境变量示例
- Windows 用户 Path：`%JAVA_HOME%\bin;%ANDROID_SDK_ROOT%\platform-tools;%ANDROID_SDK_ROOT%\emulator;%GRADLE_HOME%\bin`
- macOS/Linux：`$JAVA_HOME/bin:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/emulator:$GRADLE_HOME/bin`

## 6. 备份/还原 Path（Windows）
- 备份用户 Path：`Get-ItemProperty -Path 'HKCU:\Environment' -Name Path | Out-File "$env:USERPROFILE\path_backup_user.txt"`
- 备份系统 Path：`Get-ItemProperty -Path 'HKLM:\SYSTEM\CurrentControlSet\Control\Session Manager\Environment' -Name Path | Out-File "$env:USERPROFILE\path_backup_machine.txt"`
- 还原：从备份取 Path 值，用 `[Environment]::SetEnvironmentVariable('Path','值','User')` 或 scope=Machine 写回。

## 7. 镜像与离线
- Gradle 镜像：`https://mirrors.aliyun.com/gradle/gradle-x.y-bin.zip`
- Android SDK 镜像：`https://mirrors.aliyun.com/android/repository/` 或 `https://mirrors.cloud.tencent.com/android/repository/`
- 离线：提前下载 JDK、platform-tools、cmdline-tools、Gradle ZIP、Node 安装包，按上面路径解压或安装即可。
