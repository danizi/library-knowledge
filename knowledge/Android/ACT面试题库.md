# Activity 面试题库（网络汇总 + AI 时代校准）

> 用途：应对面试的「真题雷达」，不是背诵手册。  
> 原则：**场景题 > 背回调名**；**状态怎么活 > 四大组件百科**。  
> 采集来源（2024–2026 面经/专题常见）：掘金启动模式专题、腾讯云四大组件高频、Medium 情景题（lifecycle / singleTop vs singleTask）、InterviewPrep Activity 题单、2026 Android 面试综述（Compose + ViewModel 仍绑 Activity 生命周期）。

和本库学习闭环配合：[ACT学习计划](ACT学习计划.md) — **7 天稳妥** Demo → 卡片 → 默答。  
自己栽过的题进 [ACT错题库](ACT错题库.md)，复习先刷那一页，不要指望对话还记得。

### 按天刷题（7 天稳妥版）

| 天 | 必刷题号 |
|----|----------|
| D1 | A10 |
| D2 | A1 A2 A3 |
| D3 | A4 A5 A6 |
| D4 | A7 A8 |
| D5 | A9 |
| D6 | A1–A10 全真 |
| D7 | B1 B2 B5（可选）+ [错题库](ACT错题库.md) |

---

## 0. AI 盛行时代，Activity 还考什么？

| 态度 | 说明 |
|------|------|
| **不会消失** | Compose **不替代** Activity：Composable 仍挂在 Activity/Fragment 这个 LifecycleOwner 上；进程死亡、任务栈、系统回收仍是系统层问题。 |
| **考法变了** | 少考「背 onXxx 顺序当背课文」；多考「旋转/杀进程后状态怎么办」「通知点进来为什么叠两层」「单 Activity 为啥成主流」。 |
| **AI 写得出代码 ≠ 你能过关** | 面试官用追问区分：你能否讲清 **trade-off**、画栈、指出 `onNewIntent` / `ViewModel` / `SavedState` 边界。 |
| **过时风险高的点** | 死记 XML 布局细节、把「多 Activity 堆页面」当唯一架构、把 `onRetainNonConfigurationInstance` 当主力、只背 AMS 源码名词却说不清业务场景。 |
| **加分** | 能把 Activity 和 **ViewModel / Navigation / Compose setContent / 深链 / 导出安全 exported** 串起来讲。 |

一句话校准：Activity 从「页面容器知识点」变成「**系统入口 + 生命周期边界**」知识点。

---

## 1. 真题分层（按面试价值）

### A. 仍高频 · 必会（几乎每场基础/中级都会碰）

| # | 真题（情景化） | 你要能答到的点 | 建议落点 |
|---|----------------|----------------|----------|
| A1 | 完整走一遍 Activity 生命周期；来电打断时先走哪个？ | `onPause` 先于遮挡；重活别堵主线程；可见 vs 可交互（Start vs Resume） | 阶段 2 → `ACT-001,1` |
| A2 | A 打开 B，再按返回，各自回调顺序？ | Pause→Create/Start/Resume→Stop；返回 Restart 路径 | 阶段 1–2 Demo |
| A3 | 透明/Dialog 主题 Activity 盖上来，底下会不会 `onStop`？ | 仍部分可见 → 常只到 `onPause`，不 `onStop` | 阶段 2 加分实验 |
| A4 | 四种 `launchMode` 区别与场景？ | standard / singleTop / singleTask / singleInstance + 典型场景（通知防重复、主页、极少用单实例） | 阶段 3 后补子卡 |
| A5 | `singleTop` vs `singleTask` 举业务例子 | 栈顶防叠 vs 栈内复用并清上面；Home/搜索/通知 | 同上 |
| A6 | 何时调 `onNewIntent`？要不要 `setIntent`？ | 复用实例时；更新 Intent 数据常要 `setIntent` | 阶段 3 |
| A7 | 旋转屏幕发生了什么？数据怎么保？ | Activity 可销毁重建；**ViewModel 扛业务态**；`onSaveInstanceState` / `rememberSaveable` / `SavedStateHandle` 扛轻量 UI 态；别把大对象塞 Bundle | 阶段 4 |
| A8 | 配置变更 vs 进程被杀（process death）差别？ | 旋转 ViewModel 常还在；进程死后内存没了，要靠 SavedState/磁盘恢复 | 阶段 4 · **现代必问** |
| A9 | 为啥现在流行 single Activity + 多 Fragment/Compose？ | 导航与返回栈统一、状态更好管、少泄漏；复杂外链/独立任务仍可能多 Activity | 阶段 5 |
| A10 | Manifest 里 Activity 要声明啥？`exported` 注意什么？ | 未声明起不来；导出与 intent-filter 安全 | 链 `PD-001,3` |

### B. 仍会问 · 中高级加深度（看岗位）

| # | 真题 | 要点 | 备注 |
|---|------|------|------|
| B1 | Intent Flag：`CLEAR_TOP` / `SINGLE_TOP` / `NEW_TASK` 和 launchMode 关系 | Flag 可覆盖/叠加行为；能举通知点击案例 | 别只背名字 |
| B2 | `taskAffinity` 和 singleTask「是不是一定新栈」 | **不一定**；affinity 不同才常新栈 | 高频追问 |
| B3 | 启动流程（应用侧）：`startActivity` → AMS → 目标进程 `handleLaunchActivity` | 知道跨进程 + 主线程 Handler 即可；不必默写全链路 | 大厂偏爱「说到关键节点」 |
| B4 | 冷启动 / 热启动差异 | 进程有无、是否走完整 create | 性能面常挂钩 |
| B5 | Activity 里放业务逻辑有什么问题？ | 配置变更丢失、难测、上帝类；应 ViewModel + 分层 | 链 `AAP-001` |
| B6 | Compose 里还要不要懂 Activity 生命周期？ | 要；`setContent` 挂在 Activity；Effect 取消随销毁 | 链 `V-005` |

### C. 价值下降 · 少投入死记（知道即可）

| # | 内容 | 为啥降温 |
|---|------|----------|
| C1 | 逐行背 AMS/WMS 源码调用栈 | AI 与文档可查；面试更认「你能画图讲职责」 |
| C2 | 把 `singleInstance` 当常用银弹 | 真实业务少用，易搞乱任务栈/多窗口 |
| C3 | 只考「onCreate 里干什么」清单式背诵 | 太浅；会换成场景追问 |
| C4 | 多 Activity 每屏一个当唯一正确架构 | 与官方导航/Compose 实践脱节；遗留项目才需要精通迁移 |
| C5 | 详细背旧版 `startActivityForResult` 样板 | 已被 Activity Result API 取代；提一句迁移即可 |

### D. AI 时代「新」加分题（和 Activity 交界）

| # | 真题方向 | 你怎么答出差异化 |
|---|----------|------------------|
| D1 | 状态：`remember` / `rememberSaveable` / ViewModel / 磁盘各扛啥 | 按「活过多久」分层讲，不混为一谈 |
| D2 | 深链/通知点进 App：任务栈与 launchMode 怎么配 | 结合防多重实例、清栈、回到已有任务 |
| D3 | 如何用 Demo 证明你懂，而不是背题 | 拿出你 act-d2 Logcat / act-d4 旋转 Demo（本计划要求的产物） |
| D4 | AI 能生成 Activity 模板，你还学它干嘛？ | 生成管「写法」；你管「系统约束、边界、线上坑」——这是 AI 不替你背锅的部分 |

---

## 2. 默答清单（遮住解析自己说）

**基础 5 连（必须流畅）**
1. 生命周期主路径 + Home / 返回 / 打开新页三种差异  
2. 四种 launchMode + 各一个业务场景  
3. `onNewIntent` 什么时候来  
4. 旋转时谁死谁活（Activity vs ViewModel vs Bundle）  
5. 单 Activity 架构的收益与代价  

**追问 3 连（中级）**
6. 透明 Activity 对生命周期的影响  
7. singleTask 是否一定新任务栈  
8. 进程死后你如何恢复「用户以为还在」的界面状态  

**表达模板（防 AI 味空话）**  
「结论 → 机制一句话 → 一个真实场景 → 一个易错点」。例如：  
「通知进详情我倾向 singleTop/清栈策略，避免栈上叠多个相同页；复用时走 onNewIntent，记得 setIntent；大对象不塞 Bundle。」

---

## 3. 映射回学习计划（先练后背）

| 阶段 | 练什么 Demo | 用本题库练哪几题 |
|------|-------------|------------------|
| 0 Hello | 能跑 | — |
| 1 双屏跳转 | A1 部分、A10 | A2 |
| 2 生命周期日志 | A1 A2 A3 | 默答 1 |
| 3 Intent | A6 + 传参 | 默答补 Intent |
| 4 旋转保状态 | A7 A8 | 默答 4、追问 8 |
| 5 宿主 Fragment | A9 | 默答 5 |
| 加餐 | 通知/Flag 小实验 | A4 A5 B1 B2 |

**输出卡片时**：每通过一阶段，不只写概念卡，可把本表对应真题收进该卡的「易错 / 例」——面试复习时直接刷卡。

---

## 4. 参考链接（复查用）

- [掘金：Activity 启动模式面试](https://juejin.cn/post/7510450493086384162)  
- [腾讯云：四大组件及 Fragment 高频](https://cloud.tencent.com/developer/article/1618374)  
- [Medium：情景化 Android 面试（含 lifecycle / launchMode / 单 Activity）](https://medium.com/@anandgaur2207/android-interview-questions-answers-real-scenario-based-with-in-depth-explanations-aaaac3195813)  
- [Android Alchemy：Lifecycle 面试题](https://medium.com/android-alchemy/android-interview-2-activity-lifecycle-4a89db2a3003)  
- [Kemal：2026 Android 面试题综述](https://kemalcodes.com/posts/android-interview-questions-2026/)  
- [官方 ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)  

题库会过期：每季度用「配置变更 / 进程死亡 / 深链通知 / Compose 宿主」四个关键词搜一轮新面经，替换 C 档、强化 A/D 档即可。
