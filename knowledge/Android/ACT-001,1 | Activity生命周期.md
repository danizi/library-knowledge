# Activity 生命周期

**问：** Activity 生命周期的主路径有哪些回调？

> **一句话：** 先失去焦点，再变为不可见。`onStart` 表示可见。`onResume` 表示可操作。

**例：** `act-d2-lifecycle-log` 的 tag 是 `LIFE`。冷启动时，Main 的顺序是 `onCreate`、`onStart`、`onResume`。A 打开 B 时，A 先执行 `onPause`。然后 B 执行 `onCreate`、`onStart`、`onResume`。然后 A 执行 `onStop`。B 返回时，B 先执行 `onPause`。然后 A 执行 `onRestart`、`onStart`、`onResume`。然后 B 执行 `onStop` 和 `onDestroy`。按 Home 时，A 执行 `onPause` 和 `onStop`。再次进入应用时，顺序是 `onRestart`、`onStart`、`onResume`。

<details>
<summary>答</summary>

主路径是 `onCreate`。
然后是 `onStart`。
然后是 `onResume`。
然后可以到 `onPause`。
然后是 `onStop`。
然后是 `onDestroy`。
从后台返回时，在 `onStart` 之前还有 `onRestart`。
`onStart` 表示用户看得见这个 Activity。
`onStart` 不表示用户可以操作这个 Activity。
`onResume` 表示这个 Activity 有焦点。用户可以操作它。
另一个 Activity 盖住当前 Activity 时，当前 Activity 先执行 `onPause`。
新 Activity 显示完成之后，当前 Activity 再执行 `onStop`。
来电也先执行 `onPause`。
不要在 `onPause` 中做网络请求或重计算。
按 Home 后，Activity 执行到 `onStop`。
按 Home 后，Activity 通常不执行 `onDestroy`。
旋转屏幕会销毁当前实例，再创建新实例。

</details>

<details>
<summary>类比</summary>

开会时有人进门——你先停嘴（Pause），对方站定（Resume），你才离开会场（Stop）。

</details>

<details>
<summary>易错</summary>

- 打开 B 时，不要说 A 立刻执行 `onDestroy`。标准路径里 A 只执行 `onStop`。B 返回时，系统销毁 B。
- 透明 Activity 或 Dialog 主题盖住当前 Activity 时，当前 Activity 常常只执行 `onPause`。
- 这种情况下，当前 Activity 不到 `onStop`。
- 不要在 `onPause` 中做网络请求或重计算。来电时会挡住下一个界面。
- 不要把 Fragment 生命周期和 Activity 生命周期记成同一组回调。

</details>

<details>
<summary>相关</summary>

- [ACT-001 | Activity](<./ACT-001 | Activity.md>) — 窗口是谁，这张卡讲它怎么活
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — 不要和 Activity 六段混记
- [ACT面试题库](./ACT面试题库.md) — A1 A2 A3

</details>

<details>
<summary>参考</summary>

- [Activity 生命周期](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=zh-cn)

</details>
