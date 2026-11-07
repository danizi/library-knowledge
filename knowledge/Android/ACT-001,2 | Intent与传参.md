# Intent 与传参

**问：** 两个 Activity 之间怎样传递小数据，怎样把结果送回？

> **一句话：** 小数据使用 Extra。结果使用 Activity Result API。大数据不要放进 Intent。

**例：** `act-d3-intent-extra` 里，Main 用 extra `name=xiaomin` 打开 Second。点击「回传并关闭」后，第一屏显示 `收到了 xiaomin`。

<details>
<summary>答</summary>

Intent 把数据从一个 Activity 送到另一个 Activity。
显式 Intent 用目标类名指定接收方。
用 `putExtra` 放入字符串或 id 这类小数据。
接收方用 `getStringExtra` 读取字符串。
回传结果使用 Activity Result API。
发送方先登记合约，再调用 `launch`。
接收方调用 `setResult`，然后调用 `finish`。
发送方在回调中接收结果。
不要再使用 `startActivityForResult`。
Extra 通过 Binder 传递。
不要把大图、整张表或大对象放进 Extra。
大对象放在文件或数据库中。
Intent 只携带路径或 id。

</details>

<details>
<summary>类比</summary>

信封只装便条和门牌号，家具用货车另送。

</details>

<details>
<summary>易错</summary>

- Extra 的键名不一致时，接收方得到 null。系统不报错。
- 不要把 Bitmap 或列表放进 Extra。
- 只调用 `startActivity`、不登记结果合约时，发送方收不到回传结果。

</details>

<details>
<summary>相关</summary>

- [ACT-001 | Activity](<./ACT-001 | Activity.md>) — 送信的两端是系统窗口
- [ACT-001,4 | 启动模式与任务栈](<./ACT-001,4 | 启动模式与任务栈.md>) — 复用实例时新 Intent 走 `onNewIntent`
- [ACT面试题库](./ACT面试题库.md) — 传参边界；A6 和启动模式交界

</details>

<details>
<summary>参考</summary>

- [Intent 概览](https://developer.android.com/guide/components/intents-filters?hl=zh-cn)
- [Activity Result API](https://developer.android.com/training/basics/intents/result?hl=zh-cn)

</details>
