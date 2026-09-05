# Fragment 错题库（只记你栽过的）

> 题库是「可能考什么」。这页是「你已经答歪过 / Demo 已暴露的坑」。  
> 对话会忘。复习遮「要说的那句」。新栽喊「记错题」。  
> Activity 交界的坑若已在 [ACT错题库](ACT错题库.md)，这里只留指针，不抄两份长文。

对照：[FG面试题库](FG面试题库.md)

---

## 怎么用

1. 只看 **题** 和 **坑**，先开口。  
2. 对不上再看 **要说的那句**。  
3. 仍卡 → 复跑 Demo，不要只重读卡。

---

## 待订正 / 已知缺口

| 题 | 坑 | 要说的那句 | 相关 | 记于 |
|----|----|------------|------|------|
| **F5 / ACT A9** 门外仍要 Activity | 默答只说泄漏、复用、好管 | 里面换页单 Activity；分享 / 外链 / 支付相机是系统砸大门 | [ACT错题库 A9 行](ACT错题库.md) · [FG-001,5](<./FG-001,5 | Fragment与Activity关联.md>) | ACT D5–D6 |
| **F6** 返回退出 App | act-d5 `replace` 没入栈 | 系统返回先弹 **Fragment 回退栈**；要 `addToBackStack`，空了才关掉 Activity | [fg-d1](../demos/fragment/fg-d1-backstack/) · [FG-001,3](<./FG-001,3 | Fragment回退栈.md>) | Demo 已订正，待默答 |
| **F8** 为啥用 DialogFragment | 只说到「带生命周期的弹框」 | 它进 **FragmentManager**，旋转按 tag 重建（`restored=true`）；裸 `AlertDialog.show()` 不登记，宿主一换实例就丢，还可能泄漏 | [fg-d2](../demos/fragment/fg-d2-dialog-fragment/) · [FG-001,2](<./FG-001,2 | DialogFragment.md>) | D2 默答 2026-08-29 |
| **F4** 切页覆盖信箱 | act-d5 宿主 `markSwitch` 覆盖 Detail 写下的字 | 同一份 ViewModel 会被两边改；不是 VM 丢了 | act-d5 · [FG-001,4](<./FG-001,4 | Fragment通信.md>) | ACT D5 审 demo |
| **F2** 生命周期多记啥 | 只说到 `onHiddenChanged`、hide 不走 Resume | 还要说 **人 vs view**：入栈 replace 可 `onDestroyView` 而人还在；切 Tab 刷新常靠 HiddenChanged | [fg-d4](../demos/fragment/fg-d4-view-lifecycle/) · [FG-001,1](<./FG-001,1 | Fragment生命周期.md>) | D4 默答 2026-09-05 |
| **F10** view / Activity 空 | 说到没 attach、view 可能 null | 观察 UI 必须绑 **`viewLifecycleOwner`**；别 `observeForever` 再碰已拆 view | [fg-d4](../demos/fragment/fg-d4-view-lifecycle/) · [FG-001,7](<./FG-001,7 | viewLifecycleOwner.md>) | D4 默答 2026-09-05 |

---

## 已开口（防回潮）

| 题 | 别缩水 | 记于 |
|----|--------|------|
| F1 | 不能单独活；容器用 `FragmentContainerView` | ACT D5 |
| F5 前半 | 切页宿主可不 Destroy；状态挂宿主 ViewModel | ACT D5 |
| F7 F9 | replace 会拆 view，没入栈连实例也 Destroy；`commitNow` / `runOnCommit` 配 `addToBackStack` 会崩 | D3 |
| F4 | Activity 作用域 VM 持续状态；Result 一次性；别互握实例（切页盖信箱仍见待订正行） | D5 |

---

## 空模板

```text
| **F题号** | 一句话写歪在哪 | 订正后要说的那句 | 卡 / Demo | 日期 |
```
