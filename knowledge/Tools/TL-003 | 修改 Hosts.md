# 修改 Hosts

**问：**
hosts 文件是干啥的？改完为啥经常「不生效」？

**答：**
hosts 把「域名 → IP」写死在本机，优先于 DNS 查询，常用于本地/测试环境绑域名。  
Windows 路径在 `C:\Windows\System32\drivers\etc\hosts`，**必须管理员权限**才能保存。  
改完要 **刷新 DNS 缓存**（如 `ipconfig /flushdns`），否则浏览器还走旧解析。

一句话：hosts 本地点名 IP；没管理员或没刷 DNS，等于没改。

**例 / 类比：**
```text
127.0.0.1   www.my-dev.local
192.168.1.100   api.test.local
```
- 类比：给朋友起外号并写在自己通讯录——只有你的手机认，且通讯录要保存成功才算。

**易错：**
- 普通权限打开「看起来改了」其实存不上。
- 改完不 `flushdns`、不重开浏览器。

**相关：**
- （网络/环境类工具卡，可链到具体项目过程卡）

**参考：**
- Windows：管理员 CMD 执行 `ipconfig /flushdns`
