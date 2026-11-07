# Navigation与Fragment

**问：** Navigation 和手写 `FragmentManager` 事务，返回时遵循的是不是同一套规则？

> **一句话：** Navigation 用导航图、NavHost 和 NavController 管理跳转。返回时仍先弹出 Fragment。回退栈空了才结束 Activity。

**例：** `fg-d8-navigation` 里，Activity 只挂 NavHost。Home 调用 `navigate(action)` 进入 Detail。系统返回后，系统销毁 Detail，并回到 Home。宿主 hash 不变。`fg-d1` 使用同一条返回规则。管理回退栈的是 Navigation。

<details>
<summary>答</summary>

手写事务时，自己调用 `replace` 和 `addToBackStack`。
Navigation 把目的地和连线放进导航图。
画面放在 NavHost 里。
跳转调用 `NavController.navigate`。
`defaultNavHost=true` 时，系统返回由 Navigation 执行 pop。
底层仍是 Fragment 回退栈。
系统先弹出 Fragment。
回退栈空了，才结束 Activity。
Navigation 减少手写事务和路由清单。
Navigation 不改变返回的顺序。
现代单 Activity 项目多用 Navigation。
导航图可以一次看清路由。
深链和 Safe Args 也使用这套图。
小 Demo 仍可以手写事务。
特殊栈仍可以手写事务。
必须精确控制事务时，仍可以手写事务。
手写时要能说明入栈和弹出。

</details>

<details>
<summary>类比</summary>

导航图是地铁线路图。NavController 是检票闸机。你不用每次自己铺轨道。

</details>

<details>
<summary>易错</summary>

- 不要认为使用了 Navigation 就没有 Fragment 回退栈。换的是管理回退栈的组件。
- 不要在 Activity 里再手写一套 `replace`，和 NavHost 抢同一个容器。
- 不要忘记 `defaultNavHost=true`。否则系统返回会直接结束 Activity。
- 说明 Navigation 时，要分别说明导航图、NavHost 和 NavController 各管什么。

</details>

<details>
<summary>相关</summary>

- Demo：[fg-d8-navigation](../demos/fragment/fg-d8-navigation/)
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 返回先弹出 Fragment。这张卡更换管理方式
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 单 Activity 把导航收在一处
- [FG-001,9 | Fragment传参与状态](<./FG-001,9 | Fragment传参与状态.md>) — 传参。Navigation 可以使用 Safe Args
- [FG面试题库](./FG面试题库.md) — F13
- [FG错题库](./FG错题库.md)

</details>

<details>
<summary>参考</summary>

- [Navigation 入门](https://developer.android.com/guide/navigation/get-started?hl=zh-cn)

</details>
