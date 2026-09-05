# Activity 生命周期

**问：**
Activity 生命周期主路径是哪几段？看得见和可交互差在哪？A 开 B、返回、Home 各怎么走？

**答：**
主路径 6 段：`onCreate` → `onStart` → `onResume` ⇄ `onPause` → `onStop` → `onDestroy`。从后台回来多一段 `onRestart`（在 `onStart` 前）。  
`onStart` = **看得见**（未必能点）；`onResume` = **可交互**（有焦点）。被另一面盖住时：先 `onPause` 让出焦点，对方亮完自己才 `onStop`——来电也是先 Pause，所以这里别做重活。  
Home 只停到 `onStop`，人还在，一般不 Destroy；旋转则会 Destroy 再 Create 新实例（状态怎么保见 D4）。

一句话：先丢焦点（Pause）再看不见（Stop）；Start 可见、Resume 可点。

**例 / 类比：**
- 例子（`act-d2-lifecycle-log`，tag `LIFE`）：
  - 冷启动：Main `Create → Start → Resume`
  - A 开 B：A `Pause` → B `Create → Start → Resume` → A `Stop`
  - B 返回：B `Pause` → A `Restart → Start → Resume` → B `Stop → Destroy`
  - Home：A `Pause → Stop`；再进 App：`Restart → Start → Resume`
- 类比：开会时有人进门——你先停嘴（Pause），对方站定（Resume），你才离开会场（Stop）。

**易错：**
- 以为开 B 时 A 立刻 Destroy；其实标准路径 A 只 Stop，B 返回才 Destroy B。
- 透明/Dialog 主题盖上来，底下常仍部分可见 → 往往只到 `onPause`，不到 `onStop`（题库 A3）。
- 在 `onPause` 里写网络/重计算，来电时会卡下一面。
- 和 Fragment 生命周期搅在一起；Fragment 多的是装卸/显隐，见 FG-001,1。

**相关：**
- [ACT-001 | Activity](<./ACT-001 | Activity.md>) — 窗口是谁，这张卡讲它怎么活
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — 不要和 Activity 六段混记
- [ACT面试题库](./ACT面试题库.md) — A1 A2 A3

**参考：**
- [Activity 生命周期](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=zh-cn)
