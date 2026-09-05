# Fragment 学习计划 · 11 天稳妥

目标：**11 天内**把 Fragment 知识点走完（能讲、有 Demo、有卡）。每天约 **3 h**，到点停。  
原则：Demo 不过不写卡；卡写完必须 **15 分钟默答**。

日程用 **D0–D10**（和 ACT 同一套写法）。题库用 **F1–F14**。卡片用 **FG-001,x**。  
当前在 FG 线时，「教我 D3」指本计划；要回到 Activity 说「教我 ACT D2」。

ACT 的 D5 只交了「宿主」（`FG-001,5` + act-d5）。本计划从入口复盘到 ViewPager2。  
**只这一条路径**，不另开 5/10 天旁路。

配套：[FG面试题库](FG面试题库.md)（按天刷题）· [FG错题库](FG错题库.md)（只记你答歪的）  
环境：Android Studio + Kotlin Empty Activity  
Demo 目录：D1 起 `knowledge/Android/demos/fragment/`；`act-d5`（D0 交界 / ACT D5）仍在 `demos/activity/`  
命名：`fg-d{日程天}-{主题}`（如 `fg-d1-backstack`）。与 ACT 的 `act-d*` 各算各的，不共用全局 `stageN`。

---

## 11 天总览

| 天 | 主题 | 产出 | 面试覆盖 |
|----|------|------|----------|
| D0 | 入口：是什么 + 容器 | 默答 `FG-001` | F1 F3 |
| D1 | 回退栈 | `FG-001,3` + fg-d1 | F6 |
| D2 | DialogFragment | `FG-001,2` + fg-d2 | F8 |
| D3 | 事务 add/replace/commit | `FG-001,6` + fg-d3 | F7 F9 |
| D4 | 生命周期加深 + 空安全 | 加厚 `FG-001,1` + `FG-001,7` + fg-d4 | F2 F10 |
| D5 | 通信加深 Result API | 加厚 `FG-001,4` + fg-d5 | F4 |
| D6 | 嵌套 Fragment | `FG-001,8` + fg-d6 | F11 |
| D7 | 传参与状态 | `FG-001,9` + fg-d7 | F12 |
| D8 | Navigation | `FG-001,10` + fg-d8 | F13 |
| D9 | ViewPager2 | `FG-001,11` + fg-d9 | F14 |
| D10 | 收口 / 全真默答 | 题库 F1–F14 | 卡壳进错题库 |

D0 若刚默答过「隔断 vs 大楼」，可跳过输入，仍建议遮卡过一遍 F1、F3。

---

## 知识点总表

一张卡只装一个概念。已有卡先默答，空的按天新建。

| 知识点 | 卡 | 天 | 状态 |
|--------|----|----|------|
| 是什么、不能单独活、空构造 | `FG-001` | D0 | 卡在，待默答 F1 |
| 容器 `FragmentContainerView` | `FG-001`（问里带） | D0 / D3 | 卡在，D3 用事务 Demo 再钉死 |
| 与 Activity 关联、单 Activity 边界 | `FG-001,5` | ACT D5 | 卡在；门外入口见错题库 |
| 生命周期（装卸 / 显隐） | `FG-001,1` | D4 | 已加厚 |
| 回退栈 `addToBackStack` | `FG-001,3` | D1 | Demo+卡；待默答 F6 |
| DialogFragment vs 裸 Dialog | `FG-001,2` | D2 | Demo+卡；F8 在错题库 |
| 通信：共享 ViewModel + Result | `FG-001,4` | ACT D5 / D5 | 已加厚 Result 例 |
| 事务：add / replace / commit | `FG-001,6` | D3 | 卡已落盘，默答过 |
| `viewLifecycleOwner` / `getActivity` 空 | `FG-001,7` | D4 | 卡已落盘，待默答 |
| 嵌套：child vs parent FM | `FG-001,8` | D6 | Demo+卡；待默答 F11 |
| 传参 arguments、SavedState | `FG-001,9` | D7 | Demo+卡；待默答 F12 |
| Navigation vs 手写 FM | `FG-001,10` | D8 | **待建** |
| ViewPager2 + `FragmentStateAdapter` | `FG-001,11` | D9 | **待建** |
| 转场 `setCustomAnimations` | 不单开卡 | D10 选做 | 知道有、面试少深挖 |
| `setRetainInstance` / 事务源码 | — | 降温 | 过时，不占日程 |

和 ACT 交界：任务栈、launchMode、进程死亡仍在 ACT；本表只管「同一扇窗里怎么装卸」。

---

## 每日日程（每段到点停）

> 格式：`输入 → Demo → 输出卡 → 默答`。  
> 计时器建议：手机倒计时，响铃即停，未完成记入 `.cursor/memory/` 当晚补。

### D0 · 入口（1 h，可跳输入）

遮「答」默念 `FG-001`：是什么、为啥不能单独活、为啥容器用 `FragmentContainerView`。卡壳写进错题库。

**D0 验收**：`[ ]` 默答 F1 F3

### D1 · 回退栈（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 分清：Activity 任务栈 vs Fragment 回退栈 | 系统返回先弹哪一层 |
| 0:20–1:30 | 70 min | Demo `fg-d1-backstack/`：`replace` + `addToBackStack` | Home→Detail 返回回 Home，不退出 App |
| 1:30–2:00 | 30 min | 写 `FG-001,3 \| Fragment回退栈.md` | 问/答/一句话/例/易错 |
| 2:00–2:30 | 30 min | 默答 F6：为啥 act-d5 按返回会退出 | |
| 2:30–3:00 | 30 min | 审 demo + INDEX 挂上 FG-001,3 | |

**D1 验收**：`[x]` 返回只换 Fragment `[x]` FG-001,3 落盘（2026-08-29）`[ ]` 默答 F6

### D2 · DialogFragment（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | Dialog vs DialogFragment（旋转、配置变更） | 能说为啥不用裸 Dialog |
| 0:20–1:20 | 60 min | Demo `fg-d2-dialog-fragment/` | 弹出后旋转，浮层还在 |
| 1:20–1:50 | 30 min | 写 `FG-001,2 \| DialogFragment.md` | |
| 1:50–2:20 | 30 min | 默答 F8 | |
| 2:20–3:00 | 40 min | INDEX + 错题库 | |

**D2 验收**：`[x]` Demo（2026-08-29）`[x]` FG-001,2 落盘 `[ ]` 默答 F8

### D3 · 事务（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | add vs replace vs hide/show；`commit` vs `commitNow`；`setReorderingAllowed` | 能说 replace 常拆 view |
| 0:25–1:35 | 70 min | Demo `fg-d3-fragment-transaction/`：同一容器三种切法，Logcat 对照生命周期 | 看清 replace 销毁、hide 不销毁 |
| 1:35–2:05 | 30 min | 写 `FG-001,6 \| Fragment事务.md` | 含 runOnCommit 不能配回退栈 |
| 2:05–2:25 | 20 min | 默答 F7 F9 | |
| 2:25–3:00 | 35 min | 审 demo | |

**D3 验收**：`[x]` Demo（2026-08-29）`[x]` FG-001,6 落盘 `[x]` 默答 F7 F9（2026-08-29）

### D4 · 生命周期加深 + 空安全（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | Fragment 生命周期 vs **view** 生命周期；`getActivity()` / `view` 何时空 | 观察用 `viewLifecycleOwner` |
| 0:20–1:30 | 70 min | Demo `fg-d4-view-lifecycle/`：hide/show 打 log；destroy view 后再碰要崩的点 | `onHiddenChanged` 能看到 |
| 1:30–2:10 | 40 min | 加厚 `FG-001,1`；写 `FG-001,7 \| viewLifecycleOwner.md` | |
| 2:10–2:30 | 20 min | 默答 F2 F10 | |
| 2:30–3:00 | 30 min | 审 demo | |

**D4 验收**：`[x]` Demo（2026-09-05）`[x]` FG-001,1 加厚 + FG-001,7 落盘 `[ ]` 默答 F2 F10

### D5 · 通信加深（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 共享 VM vs Fragment Result API vs 接口回调 | 持续状态 vs 一次性结果 |
| 0:20–1:20 | 60 min | Demo `fg-d5-fragment-result/`：Detail `setFragmentResult`，Home 收到 | 不互握 Fragment 引用 |
| 1:20–1:50 | 30 min | 加厚 `FG-001,4`（补 Result 例子） | |
| 1:50–2:20 | 30 min | 默答 F4 | |
| 2:20–3:00 | 40 min | 审 demo | |

**D5 验收**：`[x]` Demo（2026-09-05）`[x]` FG-001,4 加厚 `[x]` 默答 F4（2026-09-05）

### D6 · 嵌套 Fragment（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | `childFragmentManager` vs `parentFragmentManager` / `supportFragmentManager` | 嵌套用 child |
| 0:20–1:20 | 60 min | Demo `fg-d6-nested-fragment/`：父隔断里再塞子隔断 | 返回先弹子层 |
| 1:20–1:50 | 30 min | 写 `FG-001,8 \| 嵌套Fragment.md` | |
| 1:50–2:20 | 30 min | 默答 F11 | |
| 2:20–3:00 | 40 min | 审 demo | |

**D6 验收**：`[x]` Demo（2026-09-05）`[x]` FG-001,8 落盘（2026-09-05）`[x]` 默答 F11（2026-09-05）

### D7 · 传参与状态（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 用 `arguments` Bundle 传参，不用带参构造器；旋转谁恢复 | 空构造 + setArguments |
| 0:20–1:20 | 60 min | Demo `fg-d7-fragment-args/`：带 id 进 Detail，旋转 id 还在 | |
| 1:20–1:50 | 30 min | 写 `FG-001,9 \| Fragment传参与状态.md` | 点名不要 `setRetainInstance` |
| 1:50–2:20 | 30 min | 默答 F12 | |
| 2:20–3:00 | 40 min | 审 demo | |

**D7 验收**：`[x]` Demo（2026-09-05）`[x]` FG-001,9 落盘（2026-09-05）`[x]` 默答 F12（2026-09-05）

### D8 · Navigation（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | NavHost + NavController vs 手写 `FragmentManager` | 能说现代项目为啥用 Nav |
| 0:25–1:35 | 70 min | Demo `fg-d8-navigation/`：graph 上 Home→Detail，系统返回弹出 | |
| 1:35–2:05 | 30 min | 写 `FG-001,10 \| Navigation与Fragment.md` | |
| 2:05–2:25 | 20 min | 默答 F13 | |
| 2:25–3:00 | 35 min | 审 demo | |

**D8 验收**：`[ ]` Demo `[ ]` FG-001,10 落盘 `[ ]` 默答

### D9 · ViewPager2（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | `FragmentStateAdapter` vs 自己 `replace` 一堆 Tab | 离屏会拆 view |
| 0:20–1:20 | 60 min | Demo `fg-d9-viewpager2/`：两页滑动，切走再回来实例策略能讲 | |
| 1:20–1:50 | 30 min | 写 `FG-001,11 \| ViewPager2与Fragment.md` | |
| 1:50–2:20 | 30 min | 默答 F14 | |
| 2:20–3:00 | 40 min | 审 demo | |

**D9 验收**：`[ ]` Demo `[ ]` FG-001,11 落盘 `[ ]` 默答

### D10 · 收口（3 h）

题库 F1–F14 全真口述；卡壳进 [FG错题库](FG错题库.md)。转场动画选做，不新开卡。  
弱项复跑对应 Demo，不要只重读卡。

**D10 验收**：`[ ]` F1–F14 口述 ≥11/14 `[ ]` 错题库无未订正开口题

---

## 硬规则

1. **到点停**：Demo 做不完先记 blocker，不无限拖；当晚 memory 补 15 min 上限。
2. **卡不超过 30 min/张**：费曼格式，禁止粘贴官方长文。
3. **每天必须默答 ≥15 min**：没默答 = 当天不算完成。
4. **不碰降温项**：`setRetainInstance`、逐行事务源码——知道过时即可。
5. **Studio 打开 Demo 工程根目录**（有 `settings.gradle.kts` 的 `act-d*` / `fg-d*` 文件夹），不要打开 `demos/`、`activity/` 或 `fragment/`。

---

## 和我怎么配合（带天数）

| 你说 | 何时 |
|------|------|
| 「教我 D3」 | 当天输入阶段（当前 FG 线） |
| 「审 demo fg-d3」 | Demo 时段结束后 |
| 「输出 FG-001,6」 | 写卡时段 |
| 「默答 F8」 | 遮卡开口（F 是题号） |
| 「记错题」 | 默答栽了，写入 [FG错题库](FG错题库.md) |
| 「教我 ACT D2」 | 切回 Activity 日程 |

**现在进行到**：D7 已过。下一站 **D8**（Navigation · fg-d8 · FG-001,10 · F13）。说「教我 D8」。
