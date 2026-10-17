# 写入 Hosts 并刷新 DNS

> 类型：过程卡 · 目标：**冷启动可复现**  
> 状态：**已晋升 · 仍待审查**（未在本机冷启动验收）  
> 用法：hosts 为何优先于 DNS、为何会「看起来改了却不生效」，见 [TL-003 | 修改 Hosts](<./TL-003 | 修改 Hosts.md>)

## 元信息

| 项 | 填写 |
|----|------|
| 结果定义 | 指定域名解析到你写的 IP；浏览器打开该域名走到这条记录 |
| 耗时预估 | 10 分钟 |
| 环境前提 | macOS（要管理员密码）或 Windows（要管理员身份）。一次只改当前这台机器 |
| 关联概念卡 | TL-003（链接在文首） |
| 产物路径 | macOS：`/etc/hosts`；Windows：`C:\Windows\System32\drivers\etc\hosts` |
| 审查 | 仍待冷启动验收 |

---

<details open>
  <summary>1. 准备</summary>

  - [ ] 已经知道要绑定的域名和 IP（例：`127.0.0.1` + `www.my-dev.local`）
  - [ ] 先备份再改
  - 验证命令（macOS）：

    ```bash
    ls -l /etc/hosts
    ```

</details>

<details open>
  <summary>2. 步骤（按顺序执行）</summary>

  ### 步骤 1 · macOS：写入并刷新

  - 操作：用 sudo 编辑。普通权限打开会改不动，或改在一份没被系统读取的副本上。
  - 关键 / 命令：

    ```bash
    sudo cp /etc/hosts /etc/hosts.bak.$(date +%Y%m%d)
    sudo nano /etc/hosts
    ```

  - 在文件末尾另起一行，域名和 IP 之间用空白分开，例如：

    ```text
    127.0.0.1   www.my-dev.local
    ```

  - 保存后刷新本机 DNS 缓存，并重开浏览器：

    ```bash
    sudo dscacheutil -flushcache
    sudo killall -HUP mDNSResponder
    ```

  - 预期现象：`ping -c 1 www.my-dev.local` 的地址是你写的 IP。

  ### 步骤 2 · Windows：写入并刷新

  - 操作：用管理员身份打开记事本或终端，再改 hosts。改完刷新 DNS，并重开浏览器。
  - 关键 / 命令（管理员 CMD 或 PowerShell）：

    ```bat
    copy %SystemRoot%\System32\drivers\etc\hosts %SystemRoot%\System32\drivers\etc\hosts.bak
    notepad %SystemRoot%\System32\drivers\etc\hosts
    ipconfig /flushdns
    ```

  - 同样在末尾加一行 `IP` + 空白 + `域名`。
  - 预期现象：`ping www.my-dev.local` 显示你写的 IP。

</details>

<details>
  <summary>3. 验收</summary>

  - [ ] hosts 末尾能看到新行，且文件确实保存到系统路径（不是下载目录里的副本）
  - [ ] macOS：`ping -c 1 该域名` 的 IP 与新行一致
  - [ ] Windows：`ping 该域名` 的 IP 与新行一致
  - [ ] 重开浏览器后，该域名不再走改之前的地址

</details>

<details>
  <summary>4. 故障排查</summary>

  | 现象 | 原因 | 处理 |
  |------|------|------|
  | 编辑器里改了，再打开还是旧内容 | 没有管理员权限，保存失败 | macOS 用 `sudo`；Windows 用「以管理员身份运行」 |
  | ping 仍是旧 IP | 只改了文件，没刷 DNS | 重做对应系统的 flush 命令，再开一个新终端 ping |
  | ping 对了，网页仍是旧站 | 浏览器自己还有缓存 | 完全退出浏览器再开；必要时清该站点缓存 |
  | 一行没生效 | 写在了注释 `#` 后面，或 IP 与域名贴在一起 | 每条记录单独一行，中间用空格或 Tab |

</details>

<details>
  <summary>5. 关联与参考</summary>

  - 概念卡：[TL-003 | 修改 Hosts](<./TL-003 | 修改 Hosts.md>) — 本机点名为何盖过 DNS
  - 只影响这一台电脑，不会改公网 DNS

</details>
