# macOS 安装 Homebrew

> 状态：步骤已拆到过程卡 · **本卡仍待审卡**

**问：**
国内 Mac 上，Homebrew 为啥往往不走官方安装脚本？装完的目录为啥还分两种？

**答：**
Homebrew 是 macOS 的包管理器。官方安装脚本从 GitHub 拉安装器，国内网络经常超时。镜像改的是「安装器从哪下载」，装完仍落在官方约定的位置：Apple Silicon 是 `/opt/homebrew`，Intel 是 `/usr/local`。  
PATH 若指到另一套目录，终端里就找不到 `brew`，等于没装上。

一句话：镜像只换下载地址；装到哪、PATH 指哪，仍按 CPU 架构走官方路径。

**例 / 类比：**
- 例子：`uname -m` 打出 `arm64` 就该落到 `/opt/homebrew`；打出 `x86_64` 就该落到 `/usr/local`。命令见过程卡。
- 类比：不从总仓排队进口，改走保税仓提货；货架位置还是店里原来那两排。

**易错：**
- Silicon 机器却装到 `/usr/local`，或 PATH 指错。
- 浅克隆、残缺拷贝导致以后 `brew update` 怪问题。
- 把镜像 URL 写死在脑子里；镜像站改路径时以帮助页为准。

**相关：**
- [TL-002,1 | 镜像安装 Homebrew](<./TL-002,1 | 镜像安装 Homebrew.md>) — 冷启动步骤（已晋升，仍待验收）
- [TL-001 | VS Code Git](<./TL-001 | VS Code Git.md>) — 装完常用 brew 装 git/node

**参考：**
- [Homebrew](https://brew.sh)
- [清华 Homebrew 镜像说明](https://mirrors.tuna.tsinghua.edu.cn/help/homebrew/)
