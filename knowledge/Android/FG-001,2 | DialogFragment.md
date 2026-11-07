# DialogFragment

**问：** 为什么对话框使用 DialogFragment，不使用 `AlertDialog.show()`？

> **一句话：** DialogFragment 登记在 FragmentManager 中。配置变更后，系统可以恢复它。裸 Dialog 不登记，也不恢复。

**例：** `fg-d2-dialog-fragment` 点击「对话框」后弹出对话框。旋转后宿主的 hash 改变。Logcat 标签 `HOST` 有 `ConfirmDialogFragment onCreate restored=true`。对话框仍在。

<details>
<summary>答</summary>

DialogFragment 是可以显示对话框的 Fragment。
调用 `show(FragmentManager, tag)` 把 DialogFragment 登记进宿主。
配置变更时，系统把 DialogFragment 和其他 Fragment 一起恢复。
Activity 实例更换之后，系统按 tag 再次调用 `onCreateDialog`。
对话框仍然显示。
`Dialog` 不进入 FragmentManager。
`AlertDialog.Builder().show()` 不进入 FragmentManager。
宿主执行 `onDestroy` 时，系统不重建这个 Dialog。
代码仍持有旧 Activity 时，这个引用可能泄漏。

</details>

<details>
<summary>类比</summary>

便利贴贴在旧桌子上。换桌子之后，便利贴掉了。告示写进登记本。换桌子之后，系统按登记本再贴一张。

</details>

<details>
<summary>易错</summary>

- 系统已经按 tag 恢复对话框。不要在 `onCreate` 里再调用一次 `show`。`fg-d2` 用 `findFragmentByTag("confirm")` 避免重复。
- 不要把 Dialog 引用存在 Activity 字段里来度过旋转。新实例替换旧实例之后，旧引用指向已销毁的窗口，或者引用是空的。
- DialogFragment 默认占用一层返回。按返回键常常先关闭对话框。按返回键不一定退出应用。
- 这张卡不讲 Compose 的 `Dialog`。这张卡不讲 `ModalBottomSheet`。

</details>

<details>
<summary>相关</summary>

- Demo：[fg-d2-dialog-fragment](../demos/fragment/fg-d2-dialog-fragment/)
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — 对话框也是 Fragment，不是另一个 Activity
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 返回可能先关闭对话框
- [ACT-001,3 | 配置变更与状态](<./ACT-001,3 | 配置变更与状态.md>) — 旋转会重建 Activity。对话框跟随 Fragment 恢复。数据仍放在 ViewModel
- [FG面试题库](./FG面试题库.md) — F8
- [FG错题库](./FG错题库.md)

</details>

<details>
<summary>参考</summary>

- [显示对话框](https://developer.android.com/guide/fragments/dialogs?hl=zh-cn)

</details>
