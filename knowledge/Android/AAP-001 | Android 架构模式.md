# Android 架构模式

**问：**
官方推荐的 App 架构，核心原则和分层是啥？MVC/MVP/MVVM 处在哪？

**答：**
原则记五句：分离关注点、扛住配置变更、界面由数据驱动、单一可信来源（SSOT）、单向数据流（UDF）。  
分层：界面层（Activity/Fragment）→ 网域层（ViewModel）→ 数据层（Repository）。  
MVC/MVP/MVVM 是常见分工名；现代文档更强调原则+分层。

一句话：UI 变瘦，状态进 ViewModel，数据进 Repository，单向流动。

**例 / 类比：**
- 例子：列表数据来自 Repository；ViewModel 暴露 UI 状态；Fragment 只渲染和上报点击。
- 类比：餐厅——前厅点菜（UI）、后厨出菜（ViewModel/领域）、仓库备货（Repository）。

**易错：**
- 业务堆在 Activity/Fragment。
- 多处各改各的数据副本，没有 SSOT。

**相关：**
- [FG-001 | Fragment](./FG-001%20%7C%20Fragment.md)
- [DP-001 | 设计模式](./DP-001%20%7C%20设计模式.md)
- [V-005,2 | 声明式与命令式对比](./V-005,2%20%7C%20声明式与命令式对比.md)

**参考：**
- [应用架构指南](https://developer.android.com/topic/architecture?hl=zh-cn)
