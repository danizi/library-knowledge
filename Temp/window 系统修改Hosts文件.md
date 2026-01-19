<details>
  <summary>前置知识说明 </summary>
  
  1. **hosts文件路径**<br>
     C:\Windows\System32\drivers\etc\hosts
  2. **必须使用【管理权限】修改**<br>
     Windows 对hosts文件做了系统级保护，普通用户权限打开，只能看不能改 / 不能保存，必须用「管理员身份」打开编辑器才能修改。
  3. **文件的书写格式**<br>
     IP 和域名之间，用空格 / 制表符 (Tab) 隔开，都行；<br>
     一行写一条规则，多行写多条；<br>
     开头加 # 代表「注释」，不会生效，可用来备注；<br>
     `格式如下:`
      ```
      目标IP地址    要绑定的域名
      ```
     `示例如下：`
      ```
      # 本地开发 - 绑定域名到本地IP
      127.0.0.1       www.baidu.com
      # 测试环境 - 绑定接口域名到测试服务器IP
      192.168.1.100   api.xxx.com
      ```
  4. **常见问题排查**<br>
    ❌ **问题 1：保存时提示「拒绝访问 / 权限不足」**<br>
    ✅ 解决方案：一定是没开管理员权限，关闭当前记事本，重新「右键→以管理员身份运行」再打开修改即可。<br>
    ❌ **问题 2：修改保存成功了，但是访问域名还是原来的 IP，配置不生效**<br>
    ✅ 解决方案（按顺序排查，99% 能解决）：<br>
    先执行 ipconfig /flushdns 刷新 DNS 缓存（必做）；<br>
    关闭浏览器所有窗口，重新打开浏览器访问（浏览器有缓存）；<br>
    关闭电脑的代理 / 梯子（代理会绕过本地 hosts 解析）；<br>
    检查 hosts 文件的书写格式：IP 和域名之间有没有空格，有没有写错 IP / 域名，没有加多余的后缀。<br>
    ❌ **问题 3：找不到 hosts 文件，或者打开后是空白的**<br>
    ✅ 解决方案：<br>
    确认路径是 C:\Windows\System32\drivers\etc\hosts ，路径绝对不能错；<br>
    确认「文件类型」选择的是「所有文件」，不是「文本文档」；<br>
    hosts 文件本身就是纯文本，空白也正常，直接在末尾添加规则即可。<br>
    ❌ **问题 4：修改后生效了，重启电脑又失效了**<br>
    ✅ 解决方案：大概率是电脑装了「杀毒软件 / 防火墙」（比如 360、火绒），会自动还原 hosts 文件；去杀毒软件的「防护中心」，关闭「hosts 文件保护」即可。<br>
     
</details>

<details>
  <summary>修改方法一:不带命令操作 </summary>

  - 步骤一：打开记事本【管理员身份】<br>
    <img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/ca5d2bfd-2126-4589-a041-8e0ec8672cb0" />
  - 步骤二：在管理员笔试本中，打开hosts文件<br>
    <img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/a01f622a-b54b-4441-bdb2-9e860a6ec39f" /><br>
    <img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/bcee37ec-af83-48e3-9502-b3f45245471e" /><br>
  - 步骤三：编辑hosts文件+保存<br>
    <img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/813b6684-1443-4786-9f58-e03c46081291" />
  
</details>

<details>
  <summary>修改方法二:带命令快捷键操作</summary>
</details>

<details>
  <summary>修改方法三:VSCode / 其他编辑器修改</summary>
</details>
