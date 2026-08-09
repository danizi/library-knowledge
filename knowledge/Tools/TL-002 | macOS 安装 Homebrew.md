# macOS 安装 Homebrew

**问：**
国内网络下，Mac 上怎么把 Homebrew 装到能用？（Apple Silicon / Intel）

**答：**
Homebrew 是 macOS 包管理器。国内直连 GitHub 常超时，可用清华等镜像**手动克隆** brew 仓到：  
Apple Silicon → `/opt/homebrew`；Intel → `/usr/local`。  
装完把对应 `bin` 写进 PATH，用 `brew -v` / `brew update` 验证。

一句话：镜像克隆到官方路径 + 配 PATH，比死磕官方安装脚本稳。

**例 / 类比：**
```bash
uname -m   # arm64 → /opt/homebrew；x86_64 → /usr/local
# 赋权后 git clone 镜像 brew.git 到上述路径，再配置 shell PATH
brew -v && brew update
```
（具体镜像 URL 以当前可用源为准，见当时笔记/镜像站说明。）
- 类比：不从总仓排队进口，改走本地保税仓提货。

**易错：**
- Silicon 机器却装到 `/usr/local` 或 PATH 指错。
- 浅克隆不当导致以后 `brew update` 怪问题。

**相关：**
- [TL-001 | VS Code Git](./TL-001%20%7C%20VS%20Code%20Git.md) — 装完常用 brew 装 git/node

**参考：**
- [Homebrew](https://brew.sh)
- [清华 Homebrew 镜像说明](https://mirrors.tuna.tsinghua.edu.cn/help/homebrew/)
