# Fragment 通信

**问：** 两个 Fragment（或 Fragment 和 Activity）要传数据，优先用哪种方式？

**答：**  
（待写）推荐方向：共享 `ViewModel`（同一作用域）做状态共享；接口回调/Fragment Result API 做一次性结果回传。少用「互相拿对方实例硬改」。

**例：** （待补）

**易错：** （待补：配置变更后引用失效等）

**相关：**
- [FG-001 | Fragment](./FG-001%20%7C%20Fragment.md) — 总入口
- [AAP-001 | Android 架构模式](./AAP-001%20%7C%20Android%20架构模式.md) — 为啥倾向 ViewModel

**参考：**
- [Fragment 结果回传](https://developer.android.com/guide/fragments/communicate?hl=zh-cn)
