# Android Studio 自动部署：各 OS Playbook

用于 bootstrap 脚本可直接调用的确定性命令。优先包管理器，不可用时再直链下载；镜像域名可替换为自家 CDN。

## Windows 10/11
- 包管理器：优先 `winget`，备选 `choco`。
- JDK（Temurin 17）：`winget install -e --id EclipseAdoptium.Temurin.17.JDK`。
- Gradle：`winget install -e --id Gradle.Gradle`（或下载 zip -> `C:\Gradle`）。
- Android Studio：`winget install -e --id Google.AndroidStudio --source winget`。
- 命令行工具（若未捆绑）：下载 `commandlinetools-win-{version}_latest.zip`，解压到 `%ANDROID_SDK_ROOT%\cmdline-tools\latest`。
- SDK/NDK 示例：`%ANDROID_SDK_ROOT%\cmdline-tools\latest\bin\sdkmanager.bat --sdk_root=%ANDROID_SDK_ROOT% "platform-tools" "platforms;android-34" "build-tools;34.0.0" "ndk;26.3.11579264"`。
- 环境变量（系统 + 用户）：
  - `ANDROID_SDK_ROOT=C:\Android\sdk`
  - `JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17`（按实际安装路径）
  - `GRADLE_HOME=C:\Gradle\gradle-8.6`
  - PATH 追加：`%ANDROID_SDK_ROOT%\platform-tools;%ANDROID_SDK_ROOT%\emulator;%GRADLE_HOME%\bin;%JAVA_HOME%\bin`。
- 镜像（可选，适配中国网络）：`%USERPROFILE%\.gradle\gradle.properties` 设置 `distributionUrl=https\://mirrors.cloud.tencent.com/gradle/gradle-8.6-bin.zip`；SDK 镜像通过 `sdkmanager.bat --sdk_root=... --no_https --download-cache` 并设 `ANDROID_SDK_MANAGER_OPTS="--download-url=https://mirrors.aliyun.com/android/repository/"`。
- 常见修复：`gradle --stop` 并删 `%USERPROFILE%\.gradle\caches`；删 `%ANDROID_SDK_ROOT%\.android\cache`；HAXM 失败时 `bcdedit /set testsigning off`；模拟器需开启 Hyper-V/WSL。

## macOS 13/14/15（Intel & Apple Silicon）
- 包管理器：`brew`。
- JDK 17：`brew install --cask temurin17`（Apple Silicon 路径 `/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home`）。
- Gradle：`brew install gradle`（或 `brew install gradle@8` 后手动链接）。
- Android Studio：`brew install --cask android-studio`；Canary 用 `brew install --cask android-studio-preview`。
- 命令行工具：`brew install --cask android-commandlinetools`（安装到 `/usr/local/share/android-commandlinetools` 或 `/opt/homebrew/share/android-commandlinetools`）。
- SDK/NDK：`/usr/local/share/android-commandlinetools/bin/sdkmanager --sdk_root=$ANDROID_SDK_ROOT "platform-tools" "platforms;android-34" "build-tools;34.0.0" "ndk;26.3.11579264"`。
- 环境变量（shell rc）：
  ```sh
  export ANDROID_SDK_ROOT="$HOME/Library/Android/sdk"
  export JAVA_HOME="$(/usr/libexec/java_home -v 17)"
  export GRADLE_HOME="/usr/local/opt/gradle"
  export PATH="$PATH:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/emulator:$GRADLE_HOME/bin:$JAVA_HOME/bin"
  ```
- 镜像：`~/.gradle/gradle.properties` 同 Windows；SDK 镜像设 `ANDROID_SDK_MANAGER_OPTS="--download-url=https://mirrors.cloud.tencent.com/android/repository/"` 或代理 `--proxy=http --proxy_host=mirrors.aliyun.com --proxy_port=80`。
- 修复：`sudo xattr -r -d com.apple.quarantine /Applications/Android\ Studio.app`；授予 `$ANDROID_SDK_ROOT` 权限；模拟器重置 `~/Library/Android/sdk/emulator -wipe-data`。

## Ubuntu/Debian（22.04+，amd64/arm64）
- JDK 17：`sudo apt-get update && sudo apt-get install -y openjdk-17-jdk`。
- Gradle：`sudo apt-get install -y gradle`，或下载二进制放 `/opt/gradle` 并链接 `/usr/local/bin/gradle`。
- Android Studio：下载 https://dl.google.com/dl/android/studio/ide-zips/<version>/android-studio-<version>-linux.tar.gz 解压到 `/opt/android-studio`，链接 `/usr/local/bin/studio`。
- 命令行工具：下载 `commandlinetools-linux-{version}_latest.zip`，解压到 `$ANDROID_SDK_ROOT/cmdline-tools/latest`。
- SDK/NDK 安装：`$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$ANDROID_SDK_ROOT "platform-tools" "platforms;android-34" "build-tools;34.0.0" "ndk;26.3.11579264"`。
- 环境变量（`/etc/profile.d/android.sh`）：
  ```sh
  export ANDROID_SDK_ROOT="/opt/android-sdk"
  export JAVA_HOME="/usr/lib/jvm/java-17-openjdk-amd64"
  export GRADLE_HOME="/opt/gradle"
  export PATH="$PATH:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/emulator:$GRADLE_HOME/bin:$JAVA_HOME/bin"
  ```
- 镜像：添加 Gradle 镜像；SDK 镜像用 `sdkmanager --sdk_root=$ANDROID_SDK_ROOT --no_https --download-cache --download-url=https://mirrors.aliyun.com/android/repository/`。
- 修复：`chmod -R +x $ANDROID_SDK_ROOT/emulator`；模拟器 x86 镜像需 32 位依赖 `sudo apt-get install libc6:i386 libncurses5:i386 libstdc++6:i386 zlib1g:i386`；卡在 license 时运行 `yes | sdkmanager --licenses`。

## 公共镜像/代理片段
- Gradle 镜像：`distributionUrl=https\://mirrors.cloud.tencent.com/gradle/gradle-8.6-bin.zip`
- Android Maven 镜像（项目 `settings.gradle`）：
  ```kotlin
  pluginManagement {
    repositories {
      maven { url = uri("https://maven.aliyun.com/repository/google") }
      maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
      gradlePluginPortal()
    }
  }
  dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
      maven { url = uri("https://maven.aliyun.com/repository/google") }
      mavenCentral()
    }
  }
  ```
- 代理导出（bash）：`export http_proxy=http://proxy:port; export https_proxy=http://proxy:port`。

## 验证命令
- `java -version`
- `gradle -v`
- `$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --list`
- `adb version`
- `echo $JAVA_HOME $ANDROID_SDK_ROOT $GRADLE_HOME`

