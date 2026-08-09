# Fragment

**问：**
Fragment 是啥？为啥用、啥时候用？

**答：**
Fragment 是可塞进 Activity 的一块「子界面」：有自己的布局和生命周期，能在 Activity 还活着时被加进来或换掉。  
用来拆肥 Activity、做底部导航/多 Tab/平板多栏。不能单独活着，须挂在 Activity（或已挂上的 Fragment）上；容器优先 `FragmentContainerView`。

一句话：Fragment = Activity 里可装卸的子页面模块。

**例 / 类比：**
- 例子：底部三 Tab，每 Tab 一个 Fragment，宿主一个 Activity。
- 类比：一栋楼（Activity）里的可换房间隔断，不是另一栋楼。

**易错：**
- 别用普通 FrameLayout 随便代替 FragmentContainerView。
- 生命周期、通信、回退栈要分开记。

**相关：**
- [FG-001,1 | Fragment生命周期](./FG-001,1%20%7C%20Fragment生命周期.md)
- [FG-001,4 | Fragment通信](./FG-001,4%20%7C%20Fragment通信.md)
- [ERR-001 | 开发踩坑记录](./ERR-001%20%7C%20开发踩坑记录.md)
- [V-001 | View的定义](./V-001%20%7C%20View的定义.md)

**参考：**
- [Fragments 指南](https://developer.android.com/guide/fragments?hl=zh-cn)
