# 修改 Hosts

**问：** 改完 hosts 之后，为什么打开的常常还是旧地址？

> **一句话：** hosts 把域名写成这台电脑上的 IP。没有写进系统文件，或者缓存没有刷新，都等于没有改。

**例：** 把测试域名绑到本机时，写这一行。

```text
127.0.0.1   www.my-dev.local
```

<details>
<summary>答</summary>

hosts 把「域名到 IP」写在这一台电脑上。
查找时，hosts 优先于 DNS。
它用来把测试域名绑到本机或内网。
hosts 是系统文件。
普通权限常常保存不进去。
即使写进去了，系统和浏览器仍可能记着旧解析。
不刷新缓存时，打开的仍是旧地址。

</details>

<details>
<summary>类比</summary>

外号写在自己的通讯录里。只有这台机器认这个外号。通讯录没有保存成功，或者还翻着旧页，都找不到人。

</details>

<details>
<summary>易错</summary>

- 用普通权限打开时，看起来改了，其实没有存到系统那份文件。
- 文件改了之后，还要刷新 DNS，并重新打开浏览器。

</details>

<details>
<summary>相关</summary>

- [TL-003,1 | 写入 Hosts 并刷新 DNS](<./TL-003,1 | 写入 Hosts 并刷新 DNS.md>) — macOS 和 Windows 的写入与刷新。已晋升，仍待验收

</details>

<details>
<summary>参考</summary>

- 各系统的路径和 flush 命令写在过程卡里。不要在这张卡再记第二份。

</details>
