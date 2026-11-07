# Activity

**问：** 系统根据什么识别一个 Activity？

> **一句话：** Activity 是必须在清单里登记的系统窗口。View 绘制内容。Fragment 是窗口里的隔断。

**例：** `act-d1-two-screens` 里，用户在 Main 点击按钮。Main 调用 `startActivity`。系统打开 Second。返回键把 Second 弹出栈。系统回到 Main。

<details>
<summary>答</summary>

Activity 是系统窗口。
系统启动 Activity。
系统把 Activity 放进返回栈。
Activity 有生命周期。
界面上的按钮和文字是 View。
Fragment 是这个窗口里的隔断。
Fragment 不能单独存在。
系统不根据 Kotlin 类是否写完来识别 Activity。
系统根据清单里的 `<activity>` 识别 Activity。
清单里没有这个 Activity。系统抛出 `ActivityNotFoundException`。
桌面图标对应的 Activity 使用 `MAIN` 和 `LAUNCHER`。
这个 Activity 的 `exported` 是 `true`。
应用内部打开的第二个 Activity 不要再加 `LAUNCHER`。
第二个 Activity 的 `exported` 通常是 `false`。

</details>

<details>
<summary>类比</summary>

Activity 是一栋楼的大门和楼层；View 是屋里家具；Fragment 是可拆的隔断墙——隔断不能当整栋楼用。

</details>

<details>
<summary>易错</summary>

- 新写了 Activity 忘了写进 Manifest。
- 第二屏也加 `LAUNCHER` → 桌面出现两个图标。
- 有 `intent-filter` 却把 `exported` 乱开，等于对外暴露入口（题库 A10）。
- 把业务全堆在 Activity 里（架构卡：界面层要瘦，状态 D4 再进 ViewModel）。
- 和 Fragment 混：Fragment 不是另一扇系统窗口。

</details>

<details>
<summary>相关</summary>

- [PD-001,3 | AndroidManifest清单文件](<./PD-001,3 | AndroidManifest清单文件.md>) — 登记与 `exported`
- [V-001 | View的定义](<./V-001 | View的定义.md>) — 窗里谁在画
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — 窗里的隔断，不是另一栋楼
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 宿主管理窗口。单 Activity 的原因见 A9
- [AAP-001 | Android 架构模式](<./AAP-001 | Android 架构模式.md>) — Activity 属于界面层，要瘦
- [ACT面试题库](./ACT面试题库.md) — D1 默答 A10
- [ACT-001,1 | Activity生命周期](<./ACT-001,1 | Activity生命周期.md>) — 这扇窗怎么活
- [ACT-001,2 | Intent与传参](<./ACT-001,2 | Intent与传参.md>) — 窗口之间怎么送信
- [ACT-001,3 | 配置变更与状态](<./ACT-001,3 | 配置变更与状态.md>) — 旋转谁死谁活；杀进程怎么办
- [ACT-001,4 | 启动模式与任务栈](<./ACT-001,4 | 启动模式与任务栈.md>) — 信送到哪一个实例

</details>

<details>
<summary>参考</summary>

- [Activity 概览](https://developer.android.com/guide/components/activities/intro-activities?hl=zh-cn)

</details>
