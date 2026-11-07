# Widget（常用系统控件）

**问：** 传统 View 体系里，先认识哪四个控件？

> **一句话：** 先会显示文字、输入文字、显示图片和响应点击。这四项足够搭大多数简单页面。

**例：** 一个登录页使用两个 `EditText`、一个 `Button` 和若干 `TextView`。

<details>
<summary>答</summary>

`TextView` 显示文字。
`EditText` 接收输入的文字。
`ImageView` 显示图片。
`Button` 接收点击。
这四个类都是 View。
把它们放进 ViewGroup 里组成界面。

</details>

<details>
<summary>类比</summary>

一张表单先有标签、输入框、图片和提交键。

</details>

<details>
<summary>易错</summary>

- `EditText` 容易在进入页面时取得焦点，并弹出键盘。
- 不要用大量控件堆成长列表。长列表使用 `RecyclerView`。那是另一张卡。

</details>

<details>
<summary>相关</summary>

- [V-001 | View的定义](<./V-001 | View的定义.md>)
- [V-005 | Jetpack Compose](<./V-005 | Jetpack Compose.md>)

</details>

<details>
<summary>参考</summary>

- [TextView](https://developer.android.com/reference/android/widget/TextView)

</details>
