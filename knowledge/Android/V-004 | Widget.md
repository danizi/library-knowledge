# Widget（常用系统控件）

**问：**
传统 View 体系里，最常用的叶子控件先认哪几个？各自干啥？

**答：**
先认四类：`TextView` 显示字、`EditText` 输入字、`ImageView` 显示图、`Button` 触发点击。  
它们都是 View，放进 ViewGroup 里拼界面。

一句话：先会显示、输入、图片、按钮，就够搭大多数简单页。

**例 / 类比：**
- 例子：登录页 = 两个 EditText + 一个 Button + 若干 TextView。
- 类比：像表单四件套——标签、输入框、图、提交键。

**易错：**
- EditText 容易一进页就抢焦点弹键盘。
- 长列表别堆控件，用 RecyclerView（另卡）。

**相关：**
- [V-001 | View的定义](./V-001%20%7C%20View的定义.md)
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md)

**参考：**
- [TextView](https://developer.android.com/reference/android/widget/TextView)
