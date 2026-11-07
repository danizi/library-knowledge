# drawable 目录

**问：** `res/drawable` 里，日常最常用哪几类 Drawable？

> **一句话：** 先掌握位图、点九图、状态列表和形状。状态列表使用 `selector`。形状使用 `shape`。

**例：** 按钮背景用 `selector` 切换按下时的颜色。圆角渐变用 `shape` 和 `gradient`。

<details>
<summary>答</summary>

`res/drawable` 可以放多种 Drawable。
这些种类包括位图、点九图、图层列表、状态列表、级别列表和形状。
这些种类还包括裁剪、缩放和插入。
日常最常用位图。
日常最常用 `.9.png`。
日常最常用状态列表。状态列表使用 `selector`。
日常最常用形状。形状使用 `shape`。形状可以带渐变。

</details>

<details>
<summary>类比</summary>

位图像粉饼。状态列表像按场景更换的色盘。形状像自制的膏体。

</details>

<details>
<summary>易错</summary>

- 不要把未压缩的大图放进 drawable。
- 状态列表的 `item` 顺序反了时，按下状态可能永远匹配不到。

</details>

<details>
<summary>相关</summary>

- [PD-001,1,a,1 | 状态列表的使用](<./PD-001,1,a,1 | 状态列表的使用.md>)
- [PD-001,1,a,2 | View状态常量](<./PD-001,1,a,2 | View状态常量.md>)
- [PD-001,1,a,3 | 形状可绘制对象](<./PD-001,1,a,3 | 形状可绘制对象.md>)

</details>

<details>
<summary>参考</summary>

- [Drawable 资源](https://developer.android.com/guide/topics/resources/drawable-resource?hl=zh-cn)

</details>
