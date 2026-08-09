# Android 架构模式

**问：** 官方推荐的 App 架构，核心原则和分层是啥？MVC/MVP/MVVM 又处在哪？

**答：**  
原则可以记五句：**分离关注点、扛住配置变更、界面由数据模型驱动、单一可信来源（SSOT）、单向数据流（UDF）**。  
推荐分层：界面层（Activity/Fragment）→ 网域层（ViewModel）→ 数据层（Repository）。  
MVC / MVP / MVVM 是常见「角色怎么分工」的模式名称；现代 Android 文档更强调上面的原则 + 分层，而不是死背三个缩写。

**例：** 列表数据来自 Repository；ViewModel 暴露 UI 状态；Fragment 只负责显示和把点击事件交上去。

**易错：**
- 把业务逻辑堆在 Activity/Fragment 里——直接违反分离关注点。
- 多个地方各改各的数据副本——没有 SSOT，状态会打架。

**相关：**
- [FG-001 | Fragment](./FG-001%20%7C%20Fragment.md) — 界面层常见宿主
- [DP-001 | 设计模式](./DP-001%20%7C%20设计模式.md) — 更通用的模式（待充实）
- [V-005,2 | 声明式与命令式对比](./V-005,2%20%7C%20声明式与命令式对比.md) — UI 层写法也会影响架构体验

**参考：**
- [应用架构指南](https://developer.android.com/topic/architecture?hl=zh-cn)
