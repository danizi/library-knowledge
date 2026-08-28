# Fragment与Activity关联

**问：**
Fragment 挂在 Activity 上时，各自管什么？为啥现在流行 single Activity + 多 Fragment/Compose？

**答：**
Activity 是宿主：Manifest 必须登记，管窗口、任务栈、`FragmentManager`。Fragment 是隔断：不写进 Manifest，塞进宿主的 `FragmentContainerView`，由事务 `replace`/`add` 装卸，**不能单独活**。  
切页时宿主可以不 Destroy——换的是房间隔断，不是另开一栋楼。状态优先放 **Activity 作用域 ViewModel**，两个 Fragment 读同一份。  
流行单 Activity，是因为导航和返回栈收在一处、状态更好跟、少一套 Activity 泄漏和 launchMode 坑。Compose 用 Navigation 也是同一思路。独立任务、复杂外链、系统分享入口仍可能多 Activity。

一句话：Activity 管楼和大门，Fragment 只换隔断；单 Activity 是为了导航和状态收拢，不是消灭 Activity。

**例 / 类比：**
- 例子（`stage5-host-fragment`）：切 Home ↔ Detail，顶栏 hash 不变；Logcat `HOST` 只有 Fragment 的 create/destroy，没有 `MainActivity onDestroy`。Detail 往公共信箱写一句，靠的是同一个宿主 ViewModel。
- 类比：一栋楼（宿主）里拆隔断换房间；不要每换一间就盖一栋新楼（再 `startActivity`）。

**易错：**
- 把 Fragment 写进 Manifest——系统不认它当窗口。
- 容器用普通 `FrameLayout` 凑，不用 `FragmentContainerView`。
- 以为切 Fragment 等于 Activity 重建（D2 那套生命周期会误套上来）。
- 本 Demo 没 `addToBackStack`，系统返回会退出；面试说「返回栈统一」时要补：真项目用 Navigation 或把事务入栈。
- 宿主和 Fragment 改同一份 ViewModel 会互相覆盖（stage5 切页会把「写过」改成「已切 N 次」）——不是 ViewModel 丢了。

**相关：**
- [ACT-001 | Activity](<./ACT-001 | Activity.md>) — 宿主是系统窗口，隔断不是另一栋楼
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — 隔断是什么
- [FG-001,4 | Fragment通信](<./FG-001,4 | Fragment通信.md>) — 公共信箱用共享 ViewModel
- [ACT-001,4 | 启动模式与任务栈](<./ACT-001,4 | 启动模式与任务栈.md>) — 多 Activity 才跟系统任务栈较劲
- [ACT面试题库](./ACT面试题库.md) — A9

**参考：**
- [Fragments 指南](https://developer.android.com/guide/fragments?hl=zh-cn)
- [Fragment 管理](https://developer.android.com/guide/fragments/fragmentmanager?hl=zh-cn)
