# Windows 环境变量最小改动命令版（choco 场景）
按顺序执行，每步后可做检查。默认只写“用户级”环境，不改系统级，也不做任何安装，方便在没有 winget 时配合 choco 或手动安装。

## 步骤 0（可选）：若未安装 choco，管理员 PowerShell 运行
```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force
[System.Net.ServicePointManager]::SecurityProtocol = [System.Net.SecurityProtocolType]::Tls12
iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

## 步骤 1：备份用户 Path
```powershell
Get-ItemProperty -Path 'HKCU:\Environment' -Name Path -ErrorAction SilentlyContinue | Out-File "$env:USERPROFILE\path_backup_user_step.txt"
```

## 步骤 2：写入常用环境变量（用户级）
```powershell
[Environment]::SetEnvironmentVariable('ANDROID_SDK_ROOT','C:\Android\sdk','User')
[Environment]::SetEnvironmentVariable('GRADLE_HOME','C:\Gradle\gradle-8.6','User')
# JAVA_HOME 保持已有值；如需手动设：
# [Environment]::SetEnvironmentVariable('JAVA_HOME','C:\Program Files\Eclipse Adoptium\jdk-17','User')
```

## 步骤 3：去重并追加用户 Path（保留占位符 %VAR%）
```powershell
$raw = [Environment]::GetEnvironmentVariable('Path','User') -split ';' | Where-Object { $_ }
$preferred = '%JAVA_HOME%\bin','%ANDROID_SDK_ROOT%\platform-tools','%ANDROID_SDK_ROOT%\emulator','%GRADLE_HOME%\bin'
$seen = [System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase)
$ordered = @()
foreach($p in $preferred + $raw){
  $p=$p.Trim('"').Replace('\\','\')
  if($seen.Add($p)){ $ordered+=$p }
}
$new = ($ordered -join ';')
[Environment]::SetEnvironmentVariable('Path',$new,'User')
```

## 步骤 4：状态检查（只读）
新开终端：
```powershell
cd C:\Users\ws\.codex\skills\android-studio-autosetup\scripts
python bootstrap.py --status --skip-sdkmanager --skip-gradle
```
预期：`ANDROID_SDK_ROOT` / `GRADLE_HOME` 显示 OK；sdkmanager 可能是 [X]（因为跳过）。

## 还原说明
- 用户 Path 还原：
```powershell
$bak = Get-Content "$env:USERPROFILE\path_backup_user_step.txt" | Select-String '^Path' | ForEach-Object {($_ -split ':',2)[1].Trim()}
[Environment]::SetEnvironmentVariable('Path',$bak,'User')
```
- 如果需要系统级写入，在管理员 PowerShell 中执行同样命令但将 `'User'` 改 `'Machine'`（请务必先备份）。
