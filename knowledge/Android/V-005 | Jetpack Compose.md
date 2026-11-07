# Jetpack Compose

**问：** Jetpack Compose 怎样描述界面？

> **一句话：** Compose 根据状态描述界面现在应该是什么样。它不先找到控件再修改控件。

**例：** `count` 改变之后，`Text("$count")` 按新的 `count` 更新。不需要手写 `textView.text = …`。

<details>
<summary>答</summary>

Compose 是 Android 的声明式界面工具包。
你描述界面在当前状态下应该是什么样。
系统按状态把界面对齐到这个描述。
传统 View 先找到控件，再修改控件。
这张卡是入口。细节在子卡里。

</details>

<details>
<summary>类比</summary>

点菜时说出要一份辣的。厨房按菜单出菜。你不进厨房一勺一勺炒。

</details>

<details>
<summary>易错</summary>

- 不要在 Compose 里保存控件引用再去改它。
- Compose 可以和 XML View 放在同一个应用里。先把「描述状态」这件事立住。

</details>

<details>
<summary>相关</summary>

- [V-005,1 | Preview注解](<./V-005,1 | Preview注解.md>)
- [V-005,2 | 声明式与命令式对比](<./V-005,2 | 声明式与命令式对比.md>)
- [V-005,4 | Compose 核心 API 分类](<./V-005,4 | Compose 核心 API 分类.md>)
- [V-005,4,a | Modifier](<./V-005,4,a | Modifier.md>)
- [V-001 | View的定义](<./V-001 | View的定义.md>)

</details>

<details>
<summary>参考</summary>

- [Compose 组件](https://developer.android.com/develop/ui/compose/components?hl=zh-cn)

</details>
