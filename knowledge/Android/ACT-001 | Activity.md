# Activity

**问：**
Activity 是啥？它和 View、Fragment 各管哪一层？系统怎么认出它来？

**答：**
Activity 是系统认的一扇「窗口 / 入口」：负责被启动、进返回栈、走生命周期。界面上的按钮文字是 View（或 Compose）；Fragment 只是这扇窗里可换的隔断，不能单独活。  
系统不认你 Kotlin 类写没写完，只认 Manifest 里登记过的 `<activity>`。没登记就 `ActivityNotFoundException`。  
桌面图标那扇门要 `MAIN` + `LAUNCHER`，且 `exported="true"`（启动器从应用外调它）。应用里再开的第二扇窗不要再加 LAUNCHER，通常 `exported="false"`。

一句话：Activity = 必须在 Manifest 登记的系统窗口；View 画内容，Fragment 是窗里的隔断。

**例 / 类比：**

- 例子：`act-d1-two-screens` 里 Main 点按钮 `startActivity` 打开 Second；返回键把 Second 弹出栈，回到 Main。
- 类比：Activity 是一栋楼的大门和楼层；View 是屋里家具；Fragment 是可拆的隔断墙——隔断不能当整栋楼用。

**易错：**

- 新写了 Activity 忘了写进 Manifest。
- 第二屏也加 `LAUNCHER` → 桌面出现两个图标。
- 有 `intent-filter` 却把 `exported` 乱开，等于对外暴露入口（题库 A10）。
- 把业务全堆在 Activity 里（架构卡：界面层要瘦，状态 D4 再进 ViewModel）。
- 和 Fragment 混：Fragment 不是另一扇系统窗口。

**相关：**

- [PD-001,3 | AndroidManifest清单文件](<./PD-001,3 | AndroidManifest清单文件.md>) — 登记与 `exported`
- [V-001 | View的定义](<./V-001 | View的定义.md>) — 窗里谁在画
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — 窗里的隔断，不是另一栋楼
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 宿主职责；为啥单 Activity（A9）
- [AAP-001 | Android 架构模式](<./AAP-001 | Android 架构模式.md>) — Activity 属于界面层，要瘦
- [ACT面试题库](./ACT面试题库.md) — D1 默答 A10
- [ACT-001,1 | Activity生命周期](<./ACT-001,1 | Activity生命周期.md>) — 这扇窗怎么活
- [ACT-001,2 | Intent与传参](<./ACT-001,2 | Intent与传参.md>) — 窗口之间怎么送信
- [ACT-001,3 | 配置变更与状态](<./ACT-001,3 | 配置变更与状态.md>) — 旋转谁死谁活；杀进程怎么办
- [ACT-001,4 | 启动模式与任务栈](<./ACT-001,4 | 启动模式与任务栈.md>) — 信送到哪一个实例

**参考：**

- [Activity 概览](https://developer.android.com/guide/components/activities/intro-activities?hl=zh-cn)

