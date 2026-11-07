# Fragment与Activity关联

**问：** Fragment 挂在 Activity 上时，Activity 管什么，Fragment 管什么？

> **一句话：** Activity 管理窗口和 `FragmentManager`。Fragment 放在宿主的容器里。Fragment 不能单独存在。

**例：** `act-d5-host-fragment` 在 Home 和 Detail 之间切换。顶栏 hash 不变。Logcat 标签 `HOST` 只有 Fragment 的 create 和 destroy。没有 `MainActivity onDestroy`。Detail 和 Home 使用同一个宿主 ViewModel。

<details>
<summary>答</summary>

Activity 是宿主。
清单必须登记 Activity。
Activity 管理窗口、任务栈和 `FragmentManager`。
Fragment 不写入清单。
Fragment 放进宿主的 `FragmentContainerView`。
事务用 `replace` 或 `add` 添加和替换 Fragment。
Fragment 不能单独存在。
切换 Fragment 时，宿主可以不执行 `onDestroy`。
两个 Fragment 优先读取 Activity 作用域的同一个 ViewModel。
单 Activity 把导航和回退栈放在一处。
单 Activity 减少 Activity 泄漏。
单 Activity 减少 `launchMode` 问题。
Compose 的 Navigation 也使用一个宿主。
独立任务仍可以使用多个 Activity。
复杂外链仍可以使用多个 Activity。
系统分享入口仍可以使用多个 Activity。

</details>

<details>
<summary>类比</summary>

在同一栋楼里更换隔断。不要每换一间就再建一栋楼。

</details>

<details>
<summary>易错</summary>

- 不要把 Fragment 写进清单。系统不把 Fragment 当作窗口。
- 容器使用 `FragmentContainerView`。不要用普通 `FrameLayout` 代替。
- 不要认为切换 Fragment 等于重建 Activity。
- `act-d5` 没有调用 `addToBackStack`。系统返回会退出应用。订正见 FG-001,3 和 `fg-d1`。
- 宿主和 Fragment 修改同一个 ViewModel 时会互相覆盖。`act-d5` 切页会把「写过」改成「已切 N 次」。ViewModel 没有丢失。

</details>

<details>
<summary>相关</summary>

- [ACT-001 | Activity](<./ACT-001 | Activity.md>) — 宿主是系统窗口。Fragment 不是另一个窗口
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — Fragment 是什么
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 返回先弹出 Fragment，然后才结束 Activity
- [FG-001,4 | Fragment通信](<./FG-001,4 | Fragment通信.md>) — 共享状态使用共享 ViewModel
- [ACT-001,4 | 启动模式与任务栈](<./ACT-001,4 | 启动模式与任务栈.md>) — 多个 Activity 才使用系统任务栈的启动模式
- [ACT面试题库](./ACT面试题库.md) — A9

</details>

<details>
<summary>参考</summary>

- [Fragments 指南](https://developer.android.com/guide/fragments?hl=zh-cn)
- [Fragment 管理](https://developer.android.com/guide/fragments/fragmentmanager?hl=zh-cn)

</details>
