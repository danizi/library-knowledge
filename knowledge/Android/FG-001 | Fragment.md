# Fragment

**问：** Fragment 是啥？为啥要用、啥时候用？

**答：**  
Fragment 是可塞进 Activity 的一块「子界面」：有自己的布局和生命周期，能在 Activity 还活着时被加进来或换掉。  
**为啥用：** 把又肥又长的 Activity 拆开，界面好复用、好适配（平板/底部导航/多 Tab）。  
**啥时候用：** 需要在同一个 Activity 里灵活切换页面时。  
**限制：** 不能单独活着，必须挂在 Activity（或已挂上的 Fragment）上。创建容器优先 `FragmentContainerView`。

**例：** 底部三个 Tab，每个 Tab 一个 Fragment，宿主只有一个 Activity。

**易错：**
- 容器别随便用普通 `FrameLayout` 代替 `FragmentContainerView`。
- 生命周期、通信、回退栈要分开记（见子卡）。

**相关：**
- [FG-001,1 | Fragment生命周期](./FG-001,1%20%7C%20Fragment生命周期.md) — 比 Activity 多出来的节点（待充实）
- [FG-001,4 | Fragment通信](./FG-001,4%20%7C%20Fragment通信.md) — 怎么跟别人说话（待充实）
- [ERR-001 | 开发踩坑记录](./ERR-001%20%7C%20开发踩坑记录.md) — 配置变更时刷新等坑
- [V-001 | View的定义](./V-001%20%7C%20View的定义.md) — Fragment 里仍是 View 树

**参考：**
- [官方 Fragments 指南](https://developer.android.com/guide/fragments?hl=zh-cn)
