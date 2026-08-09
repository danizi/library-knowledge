# Fragment 通信

**问：**
两个 Fragment（或 Fragment 与 Activity）要传数据，优先用哪种方式？

**答：**
优先：**共享 ViewModel**（同一作用域）做持续状态；**Fragment Result API** / 接口回调做一次性结果回传。  
少用「互相拿对方实例字段硬改」——配置变更后引用容易失效。

一句话：共享状态用 ViewModel，一次性结果用 Result/回调。

**例 / 类比：**
- 例子：列表 Fragment 与详情 Fragment 共享同一个 Activity 作用域 ViewModel。
- 类比：别互相塞纸条进对方口袋，把纸条放公共信箱（ViewModel）。

**易错：**
- 持有另一个 Fragment 的强引用过配置变更。
- 什么都用全局单例「图省事」。

**相关：**
- [FG-001 | Fragment](./FG-001%20%7C%20Fragment.md)
- [AAP-001 | Android 架构模式](./AAP-001%20%7C%20Android%20架构模式.md)

**参考：**
- [Fragment 通信](https://developer.android.com/guide/fragments/communicate?hl=zh-cn)
