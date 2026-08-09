# Widget（常用系统控件）

**问：** 传统 View 体系里，最常用的「叶子控件」先认哪几个？各自干啥？

**答：**  
先抓住这几类就够上手：  
- **TextView**：显示文字  
- **EditText**：输入文字（本质是可编辑的 TextView）  
- **ImageView**：显示图片  
- **Button**：可点的按钮（常用来触发动作）  
它们都是 `View` 的具体形态，放进 `ViewGroup` 里组成界面。

**例：** 登录页 = `EditText`（账号密码）+ `Button`（登录）+ 若干 `TextView`（标题/错误提示）。

**易错：**
- `EditText` 容易一进页面就抢焦点弹键盘，需要时再处理 `focusable` / 清除焦点。
- 复杂列表不要堆一排 Widget 硬写，用 `RecyclerView`（另卡）。

**相关：**
- [V-001 | View的定义](./V-001%20%7C%20View的定义.md) — Widget 都属于 View
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md) — Compose 里对应 Text/Button/…

**参考：**
- [TextView 参考](https://developer.android.com/reference/android/widget/TextView)
- [菜鸟 TextView 教程](https://www.runoob.com/w3cnote/android-tutorial-textview.html)
