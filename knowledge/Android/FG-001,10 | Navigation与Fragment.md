# Navigation与Fragment

**问：**
Navigation 和手写 `FragmentManager` 怎么选？现代单 Activity 项目为啥多用 Nav？

**答：**
手写是自己 `replace` + `addToBackStack`；Navigation 把目的地和连线画进 **nav graph**，舞台是 **NavHost**，跳转用 **NavController.navigate**，系统返回（`defaultNavHost=true`）由它 pop。  
底层仍是 Fragment 回退栈那套规则：先弹隔断，空了才关 Activity。Nav 省的是样板和路由清单，不是另发明一种返回物理。  
现代单 Activity 项目多用 Nav：图一眼能看清路由，深链 / Safe Args 也顺。小 Demo、特殊栈、或必须精细控制事务时，手写仍要说得清入栈/弹出。

一句话：现代项目多用 Nav graph；手写要能讲清入栈与返回——规则与 Fragment 栈相同。

**例 / 类比：**
- 例子（`fg-d8-navigation`）：Activity 只挂 NavHost；Home `navigate(action)` 进 Detail；系统返回 Detail 销毁、Home 回来，宿主 hash 不变。对照 `fg-d1` 同一条返回规则，只是账本换成 Navigation。
- 类比：地铁线路图（graph）+ 检票闸机（Controller）；你不用每次自己铺轨（手写事务）。

**易错：**
- 以为用了 Nav 就没有 Fragment 回退栈——只是换人管账。
- Activity 里又手写一套 `replace`，和 NavHost 抢容器。
- 忘了 `defaultNavHost=true`，系统返回直接 finish Activity。
- 面试只说「官方推荐」说不出：图 / Host / Controller 各管啥。

**相关：**
- Demo：[fg-d8-navigation](../demos/fragment/fg-d8-navigation/)
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 返回先弹隔断；本卡换管账方式
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 单 Activity 收拢导航
- [FG-001,9 | Fragment传参与状态](<./FG-001,9 | Fragment传参与状态.md>) — 传参；Nav 可用 Safe Args
- [FG面试题库](./FG面试题库.md) — F13
- [FG错题库](./FG错题库.md)

**参考：**
- [Navigation 入门](https://developer.android.com/guide/navigation/get-started?hl=zh-cn)
