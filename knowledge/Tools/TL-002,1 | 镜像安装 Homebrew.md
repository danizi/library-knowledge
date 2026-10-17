# 镜像安装 Homebrew

> 类型：过程卡 · 目标：**冷启动可复现**  
> 状态：**已晋升 · 仍待审查**（未在本机冷启动验收）  
> 用法：为什么走镜像、路径为啥分两种，见 [TL-002 | macOS 安装 Homebrew](<./TL-002 | macOS 安装 Homebrew.md>)

## 元信息

| 项 | 填写 |
|----|------|
| 结果定义 | 新开一个终端，`brew -v` 有版本号，`brew update` 能结束 |
| 耗时预估 | 15–40 分钟（主要耗在下载） |
| 环境前提 | macOS；终端是 zsh；已能运行 `git`、`curl`、`bash` |
| 关联概念卡 | TL-002（链接在文首） |
| 产物路径 | Apple Silicon → `/opt/homebrew`；Intel → `/usr/local` |
| 审查 | 仍待冷启动验收。镜像 URL 以清华帮助页当时文字为准 |

---

<details open>
  <summary>1. 准备</summary>

  - [ ] 安装 Xcode 命令行工具（已装过会提示已安装，可继续）
  - [ ] 工作目录：任意，例如用户主目录
  - 验证命令：

    ```bash
    xcode-select --install
    git --version && curl --version
    uname -m
    ```

  - `arm64` → 后面 PATH 用 `/opt/homebrew`。`x86_64` → 用 `/usr/local`，安装脚本通常已把 PATH 配好。

</details>

<details open>
  <summary>2. 步骤（按顺序执行）</summary>

  ### 步骤 1 · 让安装脚本走镜像

  - 操作：在**当前这一个终端**里先导出远程地址，再克隆清华的安装脚本并执行。brew 4.0 起默认走 API，一般不用再克隆 `homebrew-core`。
  - 关键 / 命令：

    ```bash
    export HOMEBREW_BREW_GIT_REMOTE="https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git"
    export HOMEBREW_API_DOMAIN="https://mirrors.tuna.tsinghua.edu.cn/homebrew-bottles/api"
    export HOMEBREW_BOTTLE_DOMAIN="https://mirrors.tuna.tsinghua.edu.cn/homebrew-bottles"
    git clone --depth=1 https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/install.git brew-install
    /bin/bash brew-install/install.sh
    rm -rf brew-install
    ```

  - 预期现象：脚本结束且没有停在 GitHub 超时。安装位置与 `uname -m` 对应。
  - 核对：若上面两个 `HOMEBREW_*_DOMAIN` 与 [清华 bottles 帮助](https://mirrors.tuna.tsinghua.edu.cn/help/homebrew-bottles/)「临时替换」不一致，以该页为准再导出一次。

  ### 步骤 2 · 把 brew 写进 zsh，并让以后的 update 仍走镜像

  - 操作：只做一次。先看 `~/.zprofile` 里有没有同样的行，有就别再追加。
  - 关键 / 命令（Apple Silicon）：

    ```bash
    grep -n 'homebrew' ~/.zprofile || true
    cat >> ~/.zprofile <<'EOF'
export HOMEBREW_BREW_GIT_REMOTE="https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git"
export HOMEBREW_API_DOMAIN="https://mirrors.tuna.tsinghua.edu.cn/homebrew-bottles/api"
export HOMEBREW_BOTTLE_DOMAIN="https://mirrors.tuna.tsinghua.edu.cn/homebrew-bottles"
eval "$(/opt/homebrew/bin/brew shellenv)"
EOF
    source ~/.zprofile
    ```

  - Intel：不要写 `/opt/homebrew` 那一行 `eval`。三个 `export` 仍然要写入 `~/.zprofile`，然后 `source ~/.zprofile`。确认 `which brew` 是 `/usr/local/bin/brew`。
  - 预期现象：`which brew` 指向上面的官方路径，而不是空。

  ### 步骤 3 · 验证

  - 操作：关掉终端再开一个，避免用到旧的 PATH。
  - 关键 / 命令：

    ```bash
    uname -m
    which brew
    brew -v
    brew update
    ```

  - 预期现象：`brew -v` 打印 Homebrew 版本；`brew update` 结束（已最新也算过）。

</details>

<details>
  <summary>3. 验收</summary>

  - [ ] `uname -m` 为 `arm64` 时，`which brew` 含 `/opt/homebrew/bin/brew`
  - [ ] `uname -m` 为 `x86_64` 时，`which brew` 含 `/usr/local/bin/brew`
  - [ ] `brew -v` 有版本号
  - [ ] 新终端里 `brew update` 不再去裸连超时的 GitHub 安装地址

</details>

<details>
  <summary>4. 故障排查</summary>

  | 现象 | 原因 | 处理 |
  |------|------|------|
  | `brew: command not found` | PATH 指错架构，或没 `source ~/.zprofile` / 没开新终端 | 对照 `uname -m` 重做步骤 2 |
  | Silicon 上 brew 在 `/usr/local` | 装到了 Intel 路径 | 不要两套混用；按 `/opt/homebrew` 重装后再改 PATH |
  | `brew update` 很怪、历史不完整 | 用了过浅或不完整的克隆 | 不要手拷一个残缺的 `brew.git` 凑合；按本卡重跑安装脚本 |
  | `~/.zprofile` 里同一行出现多次 | 步骤 2 执行了不止一次 | 删掉重复行，只留一组 `export` 和一条 `eval` |
  | 镜像 URL 404 | 镜像站改了路径 | 打开清华 Homebrew / bottles 帮助页，替换对应 `export` |

</details>

<details>
  <summary>5. 关联与参考</summary>

  - 概念卡：[TL-002 | macOS 安装 Homebrew](<./TL-002 | macOS 安装 Homebrew.md>) — 为啥用镜像、路径为啥分两种
  - 外部文档：[清华 Homebrew 安装帮助](https://mirrors.tuna.tsinghua.edu.cn/help/homebrew/)
  - 外部文档：[清华 bottles 帮助](https://mirrors.tuna.tsinghua.edu.cn/help/homebrew-bottles/)
  - 外部文档：[Homebrew](https://brew.sh)

</details>
