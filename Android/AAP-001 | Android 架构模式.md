# Android 架构模式

<details open>
  <summary>卡片内容</summary>

  - **核心问题**
    Android 推荐的架构原则与分层是什么？常见架构模式有哪些？

  - **标准答案**
    - **原则**：分离关注点、妥善处理配置变更、数据模型驱动界面、单一可信来源（SSOT）、单向数据流（UDF）。
    - **推荐分层**：界面层（Activity/Fragment）→ 网域层（ViewModel）→ 数据层（Repository）。
    - **常见模式**：MVC / MVP / MVVM。

</details>

<details>
  <summary>关联卡片</summary>

  - [FG-001 | Fragment](./FG-001%20%7C%20Fragment.md) — 界面层常见宿主组件
  - [DP-001 | 设计模式](./DP-001%20%7C%20设计模式.md) — 更通用的设计模式

</details>

<details>
  <summary>参考文献</summary>

  - [Android 应用架构指南](https://developer.android.com/topic/architecture?hl=zh-cn)

</details>
