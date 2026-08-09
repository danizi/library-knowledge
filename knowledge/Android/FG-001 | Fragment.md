# Fragment

<details open>
  <summary>卡片内容</summary>

  - **核心问题**
    Fragment 是什么？为什么、何时、如何使用？

  - **标准答案**
    - **what**：可嵌入 Activity 的 UI 组件，有独立布局与生命周期，可在宿主 Activity 运行期间添加/删除。
    - **why**：拆解臃肿 Activity，提升界面复用与多屏适配，便于模块化维护。
    - **who**：系统提供，供开发者做平板布局、底部菜单、多标签等模块化 UI。
    - **when**：需要灵活切换页面时（横竖屏、底部导航、多 Tab）。
    - **where**：必须依托 Activity，或已绑定到 Activity 的 Fragment，不能单独存在。
    - **how**：从创建、生命周期、FragmentManager、事务、回退栈、通信、状态保存、动画逐项掌握。创建时优先使用 `FragmentContainerView`。

  - **易错点**
    - 容器应使用 `FragmentContainerView`，不要随意用普通 `FrameLayout` 代替。

</details>

<details>
  <summary>关联卡片</summary>

  - [FG-001,1 | Fragment生命周期](./FG-001,1%20%7C%20Fragment生命周期.md) — 比 Activity 多出的生命周期节点
  - [FG-001,4 | Fragment通信](./FG-001,4%20%7C%20Fragment通信.md) — 接口 / 共享 ViewModel 等通信方式
  - [ERR-001 | 开发踩坑记录](./ERR-001%20%7C%20开发踩坑记录.md) — 含配置变更时 Fragment 刷新等坑

  待建卡（占位）：
  - FG-001,2 DialogFragment
  - FG-001,3 回退栈
  - FG-001,5 Fragment 与 Activity 关联

</details>

<details>
  <summary>参考文献</summary>

  - [官方 Fragments 指南](https://developer.android.com/guide/fragments?hl=zh-cn)
  - [菜鸟教程 Fragment](https://www.runoob.com/w3cnote/android-tutorial-fragment-base.html)

</details>
