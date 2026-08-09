# res 资源文件夹

**问：**
Android 的资源文件指什么？设备那么碎，系统怎么挑对资源？

**答：**
`res/` 下的目录和文件就是资源（layout、drawable、values…）。  
系统靠**目录名限定符**（如 `-hdpi`、`-zh`、`-night`）匹配当前设备配置；没有匹配就回落到默认目录。

一句话：资源放 res，靠文件夹后缀（限定符）自动适配。

**例 / 类比：**
- 例子：`drawable-hdpi/icon.png` 与 `drawable/icon.png`——高分屏优先 hdpi。
- 类比：衣柜按季节分区，今天冬天就拿冬天那格，没有再拿「常服」。

**易错：**
- 默认目录不能省——那是兜底。
- 拼错限定符名等于资源失踪。

**相关：**
- [PD-001,1,a | drawable目录](./PD-001,1,a%20%7C%20drawable目录.md)
- [PD-001,1,b | values目录](./PD-001,1,b%20%7C%20values目录.md)
- [PD-001,2 | res资源文件夹下命名规范](./PD-001,2%20%7C%20res资源文件夹下命名规范.md)

**参考：**
- [提供资源](https://developer.android.com/guide/topics/resources/providing-resources?hl=zh-cn)
