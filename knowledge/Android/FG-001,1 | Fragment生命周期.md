# Fragment 生命周期

**问：**
Fragment 生命周期和 Activity 比，多记哪几段？为啥要多？

**答：**
Fragment 除了熟悉的创建/启动/暂停/停止/销毁，还多了和「被加入界面、显示/隐藏、离开」相关的回调（如与 view 创建销毁、`onHiddenChanged` 等）。  
因为它可以在同一个 Activity 里被反复装卸、隐藏显示，不能只套 Activity 的 onCreate/onDestroy。

一句话：Fragment 多出来的生命周期，是为了描述「在同一个宿主里被装卸/显隐」。

**例 / 类比：**
- 例子：`hide()/show()` 时不一定走完整销毁，却可能走 `onHiddenChanged`——此时用它刷新数据往往比只靠 `onResume` 靠谱（见 ERR-001）。
- 类比：租客搬进搬出同一套房——不止「房子建好/拆掉」，还有「入住/退房」。

**易错：**
- 以为每次切 Tab 都会 `onResume` 到你期望的刷新点。
- view 已销毁后还碰 view 会崩。

**相关：**
- [FG-001 | Fragment](./FG-001%20%7C%20Fragment.md)
- [ERR-001 | 开发踩坑记录](./ERR-001%20%7C%20开发踩坑记录.md)

**参考：**
- [Fragment 生命周期](https://developer.android.com/guide/fragments/lifecycle?hl=zh-cn)
