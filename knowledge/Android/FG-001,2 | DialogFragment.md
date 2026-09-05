# DialogFragment

**问：**
对话框为啥用 DialogFragment，不用裸 `AlertDialog.show()`？旋转后为啥还能在？

**答：**
DialogFragment 是「会弹出对话框的 Fragment」。用 `show(FragmentManager, tag)` 登记进宿主，配置变更时跟别的 Fragment 一起被系统恢复：Activity 实例换了，对话框按 tag 再走 `onCreateDialog`，浮层还在。  
裸 `Dialog` / `AlertDialog.Builder().show()` 只是盖在窗口上的一层，**不进** FragmentManager。旋转宿主 Destroy，这一层没人帮你重建，看起来像「对话框丢了」；若还握着旧 Activity，还可能泄漏。

一句话：对话框也当 Fragment 管，旋转才跟得上；裸 Dialog 不登记、不恢复。

**例 / 类比：**
- 例子（`fg-d2-dialog-fragment`）：点「对话框」弹出；旋转后宿主 hash 变了，Logcat `HOST` 有 `ConfirmDialogFragment onCreate restored=true`，浮层还在。
- 类比：便利贴贴在旧桌子上（裸 Dialog），换桌就掉了；写进房间登记本的告示（DialogFragment），换桌系统按本再贴一张。

**易错：**
- 旋转后又在 `onCreate` 里 `show` 一次 → 叠两层。系统已经按 tag 恢复了；fg-d2 用 `findFragmentByTag("confirm")` 挡重复。
- 把 Dialog 引用存在 Activity 字段里抗旋转——实例都换了，引用是空的或指向已销毁窗口。
- 和回退栈混：DialogFragment 默认也占一层返回，按返回常常先关对话框，不是退出 App。
- 这张卡不讲 Compose `Dialog` / `ModalBottomSheet`——那是另一套。

**相关：**
- Demo：[fg-d2-dialog-fragment](../demos/fragment/fg-d2-dialog-fragment/)
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — 对话框也是隔断，不是另一栋楼
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 返回可能先关对话框
- [ACT-001,3 | 配置变更与状态](<./ACT-001,3 | 配置变更与状态.md>) — 旋转会重建 Activity；对话框要跟 Fragment 恢复，数据仍放 ViewModel
- [FG面试题库](./FG面试题库.md) — F8
- [FG错题库](./FG错题库.md)

**参考：**
- [显示对话框](https://developer.android.com/guide/fragments/dialogs?hl=zh-cn)
