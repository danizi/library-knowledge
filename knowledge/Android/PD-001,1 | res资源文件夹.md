# res 资源文件夹

**问：** 系统怎样为当前设备选择 `res` 里的资源？

> **一句话：** 资源放在 `res`。目录名上的限定符告诉系统使用哪一份资源。

**例：** 高密度屏幕优先使用 `drawable-hdpi/icon.png`。没有匹配的限定符时，系统使用 `drawable/icon.png`。

<details>
<summary>答</summary>

`res/` 下的目录和文件是资源。
资源包括 layout、drawable 和 values。
目录名可以带限定符。
限定符的例子有 `-hdpi`、`-zh` 和 `-night`。
系统用限定符匹配当前设备配置。
没有匹配的目录时，系统使用默认目录。

</details>

<details>
<summary>类比</summary>

衣柜按季节分区。冬天拿冬天那一格。没有冬天那一格时，拿常服。

</details>

<details>
<summary>易错</summary>

- 不要省掉默认目录。默认目录是没有匹配时的回落位置。
- 不要拼错限定符名称。拼错的目录等于这份资源找不到。

</details>

<details>
<summary>相关</summary>

- [PD-001,1,a | drawable目录](<./PD-001,1,a | drawable目录.md>)
- [PD-001,1,b | values目录](<./PD-001,1,b | values目录.md>)
- [PD-001,2 | res资源文件夹下命名规范](<./PD-001,2 | res资源文件夹下命名规范.md>)

</details>

<details>
<summary>参考</summary>

- [提供资源](https://developer.android.com/guide/topics/resources/providing-resources?hl=zh-cn)

</details>
