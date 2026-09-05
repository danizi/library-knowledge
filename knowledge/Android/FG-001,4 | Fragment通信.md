# Fragment 通信

**问：**
两个 Fragment（或 Fragment 与 Activity）要传数据，优先用哪种方式？共享 ViewModel 和 Fragment Result API 各管啥？

**答：**
优先两条路，别互握对方 Fragment 实例：  
1. **共享 ViewModel**（同一作用域，常见是 Activity）：持续状态——选中项、草稿、公共信箱，谁都能读改。  
2. **Fragment Result API**：一次性回传——选完城市、填完表。听的一方 `setFragmentResultListener(key)`，回传一方 `setFragmentResult(key, bundle)`，常再 `popBackStack`。  
接口回调是老写法，能讲但不如前两种稳。配置变更后互握引用容易失效。

一句话：持续状态放信箱（ViewModel）；一次性结果用签收单（Result）；别互塞口袋。

**例 / 类比：**
- 例子（共享 VM · `act-d5-host-fragment`）：Detail 往宿主 ViewModel 写信，Home 观察同一份。注意宿主切页 `markSwitch` 会盖掉 Detail 写的字——不是 VM 丢了（错题库 F4）。
- 例子（Result · `fg-d5-fragment-result`）：Home 在 `onCreate` 听 `city_pick`；Detail `setFragmentResult` 后 pop；Home 显示「签收单：选了「…」」，全程不 `findFragment` 拿对方。
- 类比：公共信箱 vs 快递签收单；都别往对方口袋里塞纸条。

**易错：**
- 持有另一个 Fragment 的强引用过配置变更。
- 什么都用全局单例「图省事」。
- 把一次性结果硬塞进 ViewModel 又从不清理，和「持续状态」搅在一起。
- 系统返回（不点回传）不会自动带 Result——没签收就没有单。

**相关：**
- Demo：[act-d5-host-fragment](../demos/activity/act-d5-host-fragment/) — 共享 VM  
- Demo：[fg-d5-fragment-result](../demos/fragment/fg-d5-fragment-result/) — Result API  
- [FG-001 | Fragment](<./FG-001 | Fragment.md>)
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 信箱挂在宿主上
- [FG-001,7 | viewLifecycleOwner](<./FG-001,7 | viewLifecycleOwner.md>) — 观察 UI 绑 view
- [AAP-001 | Android 架构模式](<./AAP-001 | Android 架构模式.md>)
- [FG-001,9 | Fragment传参与状态](<./FG-001,9 | Fragment传参与状态.md>) — 打开时身份证；本卡是通信
- [FG面试题库](./FG面试题库.md) — F4
- [FG错题库](./FG错题库.md) — F4 切页盖信箱

**参考：**
- [Fragment 通信](https://developer.android.com/guide/fragments/communicate?hl=zh-cn)
