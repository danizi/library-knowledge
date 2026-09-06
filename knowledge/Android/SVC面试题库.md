# Service 面试题库

> 雷达，不是背诵手册。场景题 > 背回调名。  
> 和 [SVC学习计划](SVC学习计划.md) 配合。日程 **D0–D7**，题号 **S1–S12**，卡片 **SVC-001,x**。  
> 栽过的进 [SVC错题库](SVC错题库.md)。

### 按天刷题

| 天 | 必刷题号 |
|----|----------|
| D1 | S1 S2 |
| D2 | S3 |
| D3 | S4 S5 |
| D4 | S6 S7 |
| D5 | S8 S9 |
| D6 | S1–S9 全真 |
| D7 | 错题库 + 选做 S10–S12 |

---

## 0. 还考 Service 吗？

| 态度 | 说明 |
|------|------|
| **还考** | start vs bind、前台通知、后台限制、和 WorkManager 怎么选。 |
| **考法** | 少背 AMS；多问「音乐播放为啥前台」「下载为啥不随便起 Service」。 |
| **和 ACT/FG** | 窗口与隔断在 ACT/FG；**无界面长任务与进程保活边界**在这页。 |

---

## 1. 真题

| # | 真题 | 要答到的点 | 落点 |
|---|------|------------|------|
| S1 | Service 是啥？和 Activity / Thread 差在哪？ | 无界面组件；须 Manifest；不是线程，默认主线程 | `SVC-001` · svc-d1 |
| S2 | 怎么启动？`startService` 干什么？ | 启动型；多次 start 可多次 `onStartCommand`；要配对 stop | 同上 |
| S3 | 生命周期主路径？多次 start 会多次 onCreate 吗？ | Create 一次；StartCommand 可多次；Destroy 在停掉时 | `SVC-001,1` · svc-d2 |
| S4 | `bindService` 和 start 差在哪？ | 绑定拿通信接口；客户端全解绑可停 | `SVC-001,2` · svc-d3 |
| S5 | Binder 本地怎么理解？ | 同进程直接调服务方法；别神话成必跨进程 | 同上 |
| S6 | 为啥音乐/导航常用前台 Service？ | 系统对后台限制严；前台要可见通知 | `SVC-001,3` · svc-d4 |
| S7 | `startForeground` 要注意啥？ | 限时内拉起通知；渠道；类型（API 新版本） | 同上 |
| S8 | IntentService 还能用吗？ | 过时；后台任务优先 WorkManager / 协程+明确生命周期 | `SVC-001,4` · svc-d5 |
| S9 | 什么时候用 Service，什么时候用 WorkManager？ | 用户可感知的进行中任务 / 与 UI 强绑定 → Service；延时、约束、可延期 → WM | 同上 |
| S10 | 进程被杀 Service 会怎样？`START_STICKY`？ | 看返回值与系统策略；不能当永活保证 | 选做 |
| S11 | `exported` 和被外部拉起风险？ | Manifest 暴露面；默认收紧 | 选做 |
| S12 | 和 JobScheduler / 前台服务类型政策 | 知道 Android 版本在收紧；面试点到政策即可 | 选做 |

### 降温

死记 AIDL 样板、保证「杀不死」、把 Service 当万能线程池。

---

## 2. 默答清单（遮解析）

1. 是啥、不是线程（S1）  
2. start 路径与停（S2 S3）  
3. bind + Binder（S4 S5）  
4. 前台与通知（S6 S7）  
5. WM 边界（S8 S9）  

表达：结论 → 机制一句 → Demo → 易错。
