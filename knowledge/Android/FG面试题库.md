# Fragment 面试题库

> 雷达，不是背诵手册。场景题 > 背回调名。  
> 和 [FG学习计划](FG学习计划.md) 配合。日程是 **D0–D10**，本题号是 **F1–F14**，卡片是 **FG-001,x**。  
> 栽过的进 [FG错题库](FG错题库.md)。和 Activity 交界的题（单 Activity / 门外入口）也见 [ACT面试题库 A9](ACT面试题库.md)。

### 按天刷题（11 天稳妥版）

| 天 | 必刷题号 |
|----|----------|
| D0 | F1 F3 |
| D1 | F6 |
| D2 | F8 |
| D3 | F7 F9 |
| D4 | F2 F10 |
| D5 | F4 |
| D6 | F11 |
| D7 | F12 |
| D8 | F13 |
| D9 | F14 |
| D10 | F1–F14 全真；卡壳进错题库 |

---

## 0. 还考 Fragment 吗？

| 态度 | 说明 |
|------|------|
| **还考** | Compose 流行后仍问：宿主是谁、回退谁先弹、为啥不用裸 Dialog、通信别互握引用。 |
| **考法** | 少默写 `onCreateView` 全文；多问「返回退出了为啥」「旋转对话框丢了为啥」「两个 Fragment 怎么说话」。 |
| **和 ACT 分工** | 任务栈 / launchMode / exported 在 ACT；**隔断里的装卸、栈、对话框、Nav / ViewPager** 在这页。 |

一句话：Fragment 考的是「同一扇窗里怎么装卸」，不是再背一遍 Activity。

---

## 1. 真题分层

### F. 必会（对上计划知识点；待建卡跟 D0–D10 走）

| # | 真题 | 要答到的点 | 落点 |
|---|------|------------|------|
| F1 | Fragment 是啥？为啥不能单独活？ | 子界面；须挂 Activity；容器优先 `FragmentContainerView` | `FG-001` |
| F2 | 比 Activity 生命周期多记啥？`hide/show` 会不会每次 `onResume`？ | 多装卸/显隐；`onHiddenChanged` 常比只靠 Resume 靠谱；人还在 view 可能已拆 | `FG-001,1` · fg-d4 · ERR-001 |
| F3 | 为啥用 `FragmentContainerView` 不用普通 FrameLayout？ | 官方容器；事务/状态恢复更稳 | `FG-001` |
| F4 | 两个 Fragment 怎么通信？ | 共享 **Activity 作用域 ViewModel**；一次性用 Result API；别互握实例 | `FG-001,4` · act-d5 · fg-d5 |
| F5 | 宿主管什么？为啥单 Activity？何时仍要多 Activity？ | Manifest/窗口/FragmentManager；栈和状态收拢；分享/外链/支付仍可能多窗 | `FG-001,5` · ACT A9 |
| F6 | 系统返回先弹谁？`addToBackStack` 干什么？ | 先弹 Fragment 回退栈，空了才 finish Activity；act-d5 没入栈所以返回退出 | `FG-001,3` · fg-d1 |
| F7 | `replace` 和 `hide/show` 差在哪？ | replace 常销毁 view；hide/show 实例还在，不一定走完整销毁 | `FG-001,6` · fg-d3 |
| F8 | 为啥对话框用 DialogFragment 不用裸 `Dialog`？ | 跟 Fragment 走配置变更，旋转不易丢；裸 Dialog 容易泄漏/丢状态 | `FG-001,2` · fg-d2 |
| F9 | `add` / `replace` / `commit` / `commitNow` 差在哪？ | replace 常拆 view；commit 异步；commitNow 立刻；回退栈事务别配 `runOnCommit` | `FG-001,6` · fg-d3 |
| F10 | `getActivity()` / `view` 为啥会空？观察绑谁？ | 没 attach 或 view 已销毁；LiveData/Flow 观察用 `viewLifecycleOwner` | `FG-001,7` · fg-d4 |
| F11 | `childFragmentManager` vs `parentFragmentManager` | 嵌套用 child；和宿主同级用 parent / support | `FG-001,8` · fg-d6 |
| F12 | 给 Fragment 传参为啥不用带参构造器？ | 系统恢复要空构造 + `arguments`；旋转靠 Bundle | `FG-001,9` · fg-d7 |
| F13 | Navigation 和手写 `FragmentManager` 怎么选？ | 现代项目多用 Nav graph；手写要能讲清入栈/弹出 | `FG-001,10`（待建） |
| F14 | ViewPager2 为啥用 `FragmentStateAdapter`？ | 离屏页会拆 view 省内存；别自己 replace 一堆 Tab 硬撑 | `FG-001,11`（待建） |

### G. 加深度（收口选做，不单开卡）

| # | 真题 | 要点 |
|---|------|------|
| G1 | 转场 `setCustomAnimations` / 共享元素 | 知道有；面试少深挖 |
| G2 | `FragmentFactory` / 依赖注入进 Fragment | 空构造约束下怎么塞依赖 |

### 降温

死记 `setRetainInstance`、逐行背事务源码、把多 Activity 当唯一换页方式——知道过时即可。

---

## 2. 默答清单（遮解析）

1. 隔断 vs 大楼（F1 F5）  
2. hide/show 刷新走哪（F2 F7）  
3. 共享 ViewModel + Result 签收单，别互塞引用（F4）  
4. 返回先弹 Fragment 栈（F6）  
5. 对话框为啥是 DialogFragment（F8）  
6. 事务 add/replace/commit（F9）  
7. view 空了绑 `viewLifecycleOwner`（F10）  
8. 嵌套用 child FM（F11）  
9. 传参用 arguments（F12）  
10. Nav vs 手写（F13）  
11. ViewPager2 Adapter（F14）  

表达仍用：结论 → 机制一句 → 对应 Demo → 易错。
