# macOS 安装 Homebrew（国内网络）

面向国内网络环境，手动克隆镜像，一看就能装；适配 Apple Silicon（推荐路径 `/opt/homebrew`）与 Intel（`/usr/local`）。

## 1. 什么是 Homebrew & 为什么需要
- macOS 上最流行的包管理器，负责安装/升级 CLI 工具与开发依赖。
- 让 `git`, `node`, `wget`, `ffmpeg` 等一条命令即可安装、更新、卸载。
- 国内常见障碍：访问 GitHub 超时、安装脚本 404、浅克隆导致 update 失败。

## 2. 安装前提
- 确认架构与 shell  
  ```bash
  uname -m      # arm64 = Apple Silicon, x86_64 = Intel
  echo $SHELL   # 预期 /bin/zsh 或 /bin/bash
  ```
- 需要工具：`git`、`curl`、`sudo`（默认随 macOS 提供）。
- 目录权限：Apple 用 `/opt/homebrew`，Intel 用 `/usr/local`；如无写权限需 `sudo chown -R $(whoami) <路径>`。

## 3. 核心安装流程（手动克隆镜像，推荐）
> 默认使用清华镜像；若镜像不可达，再换 fastgit 备选。

### Step 0：会话内设镜像变量（可选）
```bash
export HOMEBREW_BREW_GIT_REMOTE="https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git"
export HOMEBREW_CORE_GIT_REMOTE="https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git"
```

### Step 1：创建目录并赋权
- Apple Silicon：
  ```bash
  sudo mkdir -p /opt/homebrew
  sudo chown -R $(whoami) /opt/homebrew
  ```
- Intel：
  ```bash
  sudo mkdir -p /usr/local
  sudo chown -R $(whoami) /usr/local
  ```

### Step 2：克隆 brew 主仓
```bash
git clone --depth=1 https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git /opt/homebrew   # Intel 改成 /usr/local/Homebrew
```
若此命令超时/失败，可换：
```bash
git clone --depth=1 https://raw.fastgit.org/Homebrew/brew.git /opt/homebrew
```

### Step 3：克隆 homebrew-core tap
```bash
mkdir -p /opt/homebrew/Library/Taps/homebrew
git clone --depth=1 https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git \
  /opt/homebrew/Library/Taps/homebrew/homebrew-core
# 备选：https://raw.fastgit.org/Homebrew/homebrew-core.git
```

### Step 4：写入 PATH 并立即生效
- Apple Silicon：
  ```bash
  echo 'eval "$(/opt/homebrew/bin/brew shellenv)"' >> ~/.zprofile
  eval "$(/opt/homebrew/bin/brew shellenv)"
  ```
- Intel：
  ```bash
  echo 'eval "$(/usr/local/Homebrew/bin/brew shellenv)"' >> ~/.zprofile
  eval "$(/usr/local/Homebrew/bin/brew shellenv)"
  ```

### Step 5：固定镜像远端，避免回到 GitHub
```bash
git -C "$(brew --repo)" remote set-url origin https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git
git -C "$(brew --repo homebrew/core)" remote set-url origin https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git
```

### Step 6：更新与验证
```bash
brew update   # 若提示 shallow clone，先执行下一行
git -C /opt/homebrew/Library/Taps/homebrew/homebrew-core fetch --unshallow   # Intel 路径改 /usr/local/Homebrew/...
brew -v
brew doctor
brew install wget   # 验证安装速度与可用性
```

## 4. 遇到问题如何解决（现象→原因→解决）
- **curl 404（mirrors install.sh）**：TUNA 无该脚本 → 直接用上方“手动克隆”流程，不再依赖 install.sh。  
- **curl/ghproxy 超时**：网络被阻 → 换 fastgit 链接或临时代理：`export https_proxy=http://127.0.0.1:7890; export http_proxy=$https_proxy`，成功后 `unset http_proxy https_proxy`。  
- **`no such file /opt/homebrew/bin/brew`**：主仓未克隆或 PATH 未写 → 重跑 Step 2/4。  
- **`could not lock config file .git/config`**：权限不足 → `sudo chown -R $(whoami) /opt/homebrew`（或 `/usr/local/Homebrew/.git`）。  
- **`brew update` 要求 unshallow**：浅克隆导致 → `git -C /opt/homebrew/Library/Taps/homebrew/homebrew-core fetch --unshallow`。  
- **目录已存在且非空**：可能旧残留 → 先备份/确认无用后 `rm -rf` 再克隆（谨慎操作，勿误删有效文件）。

## 5. 快速复盘（一屏搞定版)
**Apple Silicon 版**
```bash
sudo mkdir -p /opt/homebrew && sudo chown -R $(whoami) /opt/homebrew
git clone --depth=1 https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git /opt/homebrew
mkdir -p /opt/homebrew/Library/Taps/homebrew
git clone --depth=1 https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git /opt/homebrew/Library/Taps/homebrew/homebrew-core
echo 'eval "$(/opt/homebrew/bin/brew shellenv)"' >> ~/.zprofile
eval "$(/opt/homebrew/bin/brew shellenv)"
git -C "$(brew --repo)" remote set-url origin https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git
git -C "$(brew --repo homebrew/core)" remote set-url origin https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git
brew update || true
git -C /opt/homebrew/Library/Taps/homebrew/homebrew-core fetch --unshallow || true
brew update && brew -v
```

**Intel 版**
```bash
sudo mkdir -p /usr/local && sudo chown -R $(whoami) /usr/local
git clone --depth=1 https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git /usr/local/Homebrew
mkdir -p /usr/local/Homebrew/Library/Taps/homebrew
git clone --depth=1 https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git /usr/local/Homebrew/Library/Taps/homebrew/homebrew-core
echo 'eval "$(/usr/local/Homebrew/bin/brew shellenv)"' >> ~/.zprofile
eval "$(/usr/local/Homebrew/bin/brew shellenv)"
git -C "$(brew --repo)" remote set-url origin https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git
git -C "$(brew --repo homebrew/core)" remote set-url origin https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git
brew update || true
git -C /usr/local/Homebrew/Library/Taps/homebrew/homebrew-core fetch --unshallow || true
brew update && brew -v
```

## 6. 参考与附录
- 镜像：  
  - 清华：`https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/{brew,homebrew-core}.git`  
  - 备选：`https://raw.fastgit.org/Homebrew/{brew,homebrew-core}.git`
- 代理示例：  
  ```bash
  export https_proxy=http://127.0.0.1:7890
  export http_proxy=$https_proxy
  # 用完后
  unset http_proxy https_proxy
  ```
- 常用 brew 指令速查：`brew search <name>`、`brew install <pkg>`、`brew list`、`brew info <pkg>`、`brew outdated`、`brew upgrade`、`brew cleanup`。

---
> 复盘：本流程避开官方 install.sh，直接镜像克隆；若网络再受限，务必先确保可访问镜像或配置可用代理。
