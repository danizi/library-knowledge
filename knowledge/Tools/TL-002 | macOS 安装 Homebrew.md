# macOS 安装 Homebrew

**问：** 用镜像安装 Homebrew 时，安装目录由什么决定？

> **一句话：** 镜像只更换安装器的下载地址。安装目录和 `PATH` 仍按 CPU 架构使用官方路径。

**例：** `uname -m` 输出 `arm64` 时，Homebrew 装在 `/opt/homebrew`。输出 `x86_64` 时，Homebrew 装在 `/usr/local`。命令写在过程卡里。

<details>
<summary>答</summary>

Homebrew 是 macOS 的包管理器。
官方安装脚本从 GitHub 下载安装器。
国内网络访问 GitHub 时经常超时。
镜像改变的是安装器从哪里下载。
安装完成后，文件仍放在官方约定的位置。
Apple Silicon 的位置是 `/opt/homebrew`。
Intel 的位置是 `/usr/local`。
`PATH` 指向另一套目录时，终端找不到 `brew`。
这时等于没有装上。

</details>

<details>
<summary>类比</summary>

换一个仓库提货。货架仍是店里原来的两排。

</details>

<details>
<summary>易错</summary>

- Apple Silicon 的机器不要装到 `/usr/local`。不要把 `PATH` 指到另一套目录。
- 浅克隆或不完整的拷贝会让以后的 `brew update` 出问题。
- 不要把镜像 URL 记成固定地址。镜像站改路径时，以帮助页为准。

</details>

<details>
<summary>相关</summary>

- [TL-002,1 | 镜像安装 Homebrew](<./TL-002,1 | 镜像安装 Homebrew.md>) — 冷启动步骤。已晋升，仍待验收
- [TL-001 | VS Code Git](<./TL-001 | VS Code Git.md>) — 装完之后常用 brew 安装 git 或 node

</details>

<details>
<summary>参考</summary>

- [Homebrew](https://brew.sh)
- [清华 Homebrew 镜像说明](https://mirrors.tuna.tsinghua.edu.cn/help/homebrew/)

</details>
