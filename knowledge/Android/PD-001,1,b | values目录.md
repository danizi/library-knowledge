# values 目录

**问：** `res/values` 存放什么，它和布局、图片怎样分工？

> **一句话：** `values` 给字符串、颜色、尺寸和样式起名称。布局描述结构。drawable 描述外观。

**例：** 名称的例子有 `@string/app_name`、`@color/primary` 和 `@dimen/page_padding`。

<details>
<summary>答</summary>

`values` 存放可复用的名称和值。
这些资源包括 `strings`、`colors`、`dimens`、`styles` 和 `themes`。
有时还包括 `attrs` 和 `ids`。
布局描述结构。
drawable 描述外观。
修改 `values` 里的一处值时，引用它的地方一起改变。

</details>

<details>
<summary>类比</summary>

把色号放在总表里。不要在每个房间的墙上写死色号。

</details>

<details>
<summary>易错</summary>

- 不要把颜色和字符串硬编码进布局。后期会难以修改。见主题重构那张卡。
- 不要把 `styles` 和 `themes` 当成同一类资源。`styles` 是控件样式。`themes` 是窗口主题。

</details>

<details>
<summary>相关</summary>

- [PD-001,1,b,1 | 记一次主题样式重构](<./PD-001,1,b,1 | 记一次主题样式重构.md>)
- [PD-001,1 | res资源文件夹](<./PD-001,1 | res资源文件夹.md>)

</details>

<details>
<summary>参考</summary>

- [资源类型概览](https://developer.android.com/guide/topics/resources/available-resources)

</details>
