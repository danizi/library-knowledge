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

（空。D10 已清；新栽再往下加。）

---

## 已开口（防回潮）

| 题 | 别缩水 | 记于 |
|----|--------|------|
| F1 | 不能单独活；容器用 `FragmentContainerView` | ACT D5 |
| F5 | 里面换页单 Activity（栈/状态收拢）；分享/外链/支付仍可能多窗 | D10 |
| F7 F9 | replace 会拆 view，没入栈连实例也 Destroy；`commitNow` / `runOnCommit` 配 `addToBackStack` 会崩 | D3 |
| F4 | Activity 作用域 VM 持续；Result 一次性；别互握；切页盖信箱是两边改同一份，不是 VM 丢了 | D10 |
| F6 | 返回先弹 Fragment 回退栈；要 `addToBackStack`，空了才 finish；act-d5 没入栈所以退出 | D10 |
| F8 | DialogFragment 进 FM，旋转 tag 重建；裸 Dialog 不登记易丢/泄漏 | D10 |
| F2 | hide 看 HiddenChanged；人 vs view：入栈可 DestroyView 人还在 | D10 |
| F10 | 观察 UI 绑 `viewLifecycleOwner`；别 forever 碰已拆 view | D10 |
| F1 F3 | 子界面须挂 Activity；容器优先 `FragmentContainerView`（事务/状态恢复更稳） | D10 |
| F11 | 嵌套用 child；同级用 parent/support | D6 |
| F12 | 空构造 + arguments；旋转靠 Bundle | D7 |
| F13 | 现代多用 Nav graph；手写要讲清入栈/弹出 | D8 |
| F14 | FragmentStateAdapter；离屏拆 view；别手写一堆 Tab replace | D9 |

---

## 空模板

```text
| **F题号** | 一句话写歪在哪 | 订正后要说的那句 | 卡 / Demo | 日期 |
```
