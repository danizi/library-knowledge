# Intent 与传参

**问：**
两个 Activity 之间怎么传小数据、怎么把结果送回来？Extra 有什么不能塞？

**答：**
Intent 是系统信使：Explicit 直接点名目标类（`SecondActivity::class.java`），用 `putExtra` 塞字符串、id 这类小数据。对方 `getStringExtra` 取。  
回传用 **Activity Result API**：Main 先登记合约再 `launch`；Second `setResult` + `finish`，Main 回调里收。别再写已过时的 `startActivityForResult`。  
Extra 走 Binder，别塞大图、整表、大对象——会撑爆或变慢；大东西放文件/数据库，Intent 只带路径或 id。

一句话：小数据走 Extra；回传走 Result API；大数据不进 Intent。

**例 / 类比：**
- 例子（`act-d3-intent-extra`）：Main 带 `name=xiaomin` 打开 Second；点「回传并关闭」后第一屏变成 `收到了 xiaomin`。
- 类比：信封只装便条和门牌号，家具用货车另送。

**易错：**
- Extra 键名写错，对面静默拿到 null。
- 把 Bitmap / 列表当 Extra 图省事。
- 只 `startActivity` 不登记 Result，回传永远收不到。

**相关：**
- [ACT-001 | Activity](<./ACT-001 | Activity.md>) — 送信的两端是系统窗口
- [ACT-001,4 | 启动模式与任务栈](<./ACT-001,4 | 启动模式与任务栈.md>) — 复用实例时新 Intent 走 `onNewIntent`
- [ACT面试题库](./ACT面试题库.md) — 传参边界；A6 和启动模式交界

**参考：**
- [Intent 概览](https://developer.android.com/guide/components/intents-filters?hl=zh-cn)
- [Activity Result API](https://developer.android.com/training/basics/intents/result?hl=zh-cn)
