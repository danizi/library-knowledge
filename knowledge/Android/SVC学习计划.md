# Service 学习计划 · 8 天稳妥

目标：**8 天内**把 Service 高频点走完（能讲、有 Demo、有卡）。每天约 **3 h**，到点停。  
原则：Demo 不过不写卡；卡写完必须 **15 分钟默答**。

日程用 **D0–D7**。题库用 **S1–S12**。卡片用 **SVC-001,x**。  
当前在 SVC 线时，「教我 D2」指本计划；切线说「教我 ACT D2」/「教我 BR D1」/「教我 FG D3」。

配套：[SVC面试题库](SVC面试题库.md) · [SVC错题库](SVC错题库.md)  
环境：Android Studio + Kotlin  
Demo 目录：`knowledge/Android/demos/service/`  
命名：`svc-d{日程天}-{主题}`（如 `svc-d2-lifecycle`）。与 `act-d*` / `fg-d*` / `br-d*` / `cp-d*` 各算各的。

---

## 8 天总览

| 天 | 主题 | 产出 | 面试覆盖 |
|----|------|------|----------|
| D0 | Manifest 登记 + 空工程 | `svc-d0-hello` | — |
| D1 | Service 是什么 / startService | `SVC-001` + svc-d1 | S1 S2 |
| D2 | 生命周期 onCreate/StartCommand/Destroy | `SVC-001,1` + svc-d2 | S3 |
| D3 | bindService + Binder | `SVC-001,2` + svc-d3 | S4 S5 |
| D4 | 前台 Service + 通知渠道 | `SVC-001,3` + svc-d4 | S6 S7 |
| D5 | IntentService 过时 → WorkManager 边界 | `SVC-001,4` + svc-d5 | S8 S9 |
| D6 | 题库冲刺 | 默答 S1–S9 | 全真 |
| D7 | 弱项复跑 | 错题库 | 选做 S10+ |

---

## 知识点总表

| 知识点 | 卡 | 天 | 状态 |
|--------|----|----|------|
| 是什么、Manifest、不能当线程池乱用 | `SVC-001` | D1 | **待建** |
| 生命周期 start/stop | `SVC-001,1` | D2 | **待建** |
| 绑定与 Binder | `SVC-001,2` | D3 | **待建** |
| 前台 Service / 通知 | `SVC-001,3` | D4 | **待建** |
| 后台限制与 WorkManager 边界 | `SVC-001,4` | D5 | **待建** |

---

## 每日日程（每段到点停）

> 格式：`输入 → Demo → 输出卡 → 默答`。

### D0 · 工程能跑（2 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:40 | 40 min | 扫 Manifest `<service>` / `exported` | 知道要登记 |
| 0:40–1:20 | 40 min | Demo `svc-d0-hello/` Run | 工程能装 |
| 1:20–2:00 | 40 min | memory 记卡点 | ≤5 行 |

**D0 验收**：`[ ]` Run OK `[ ]` Manifest 能找到 service 位

### D1 · 是什么 + startService（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | Service vs Thread vs WorkManager 边界直觉 | 能说「无界面、可后台」 |
| 0:25–1:35 | 70 min | Demo `svc-d1-start-service/`：按钮 start / stop | Logcat 有生命周期 |
| 1:35–2:05 | 30 min | 写 `SVC-001 \| Service.md` | 费曼齐 |
| 2:05–2:25 | 20 min | 默答 S1 S2 | |
| 2:25–3:00 | 35 min | 审 demo | |

**D1 验收**：`[ ]` Demo `[ ]` SVC-001 落盘 `[ ]` 默答

### D2 · 生命周期（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | onCreate / onStartCommand / onDestroy | 多次 start 不重复 Create |
| 0:20–1:40 | 80 min | Demo `svc-d2-lifecycle/`：连点 start、stop | Log 对照 |
| 1:40–2:10 | 30 min | 写 `SVC-001,1 \| Service生命周期.md` | |
| 2:10–2:30 | 20 min | 默答 S3 | |
| 2:30–3:00 | 30 min | 审 demo | |

**D2 验收**：`[ ]` Demo `[ ]` 卡 `[ ]` 默答

### D3 · 绑定（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | start vs bind；Binder 本地接口直觉 | |
| 0:25–1:35 | 70 min | Demo `svc-d3-bind/`：bind 调方法、unbind | |
| 1:35–2:05 | 30 min | 写 `SVC-001,2 \| 绑定与Binder.md` | |
| 2:05–2:25 | 20 min | 默答 S4 S5 | |
| 2:25–3:00 | 35 min | 审 demo | |

**D3 验收**：`[ ]` Demo `[ ]` 卡 `[ ]` 默答

### D4 · 前台 Service（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 为啥要前台；通知渠道 | |
| 0:25–1:35 | 70 min | Demo `svc-d4-foreground/`：startForeground | 通知可见 |
| 1:35–2:05 | 30 min | 写 `SVC-001,3 \| 前台Service.md` | |
| 2:05–2:25 | 20 min | 默答 S6 S7 | |
| 2:25–3:00 | 35 min | 审 demo | |

**D4 验收**：`[ ]` Demo `[ ]` 卡 `[ ]` 默答

### D5 · 后台限制与 WorkManager（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | IntentService 过时；延时/约束任务用 WM | |
| 0:25–1:35 | 70 min | Demo `svc-d5-workmanager-boundary/`：对照说明即可 | 能口头分界 |
| 1:35–2:05 | 30 min | 写 `SVC-001,4 \| 后台限制与WorkManager.md` | |
| 2:05–2:25 | 20 min | 默答 S8 S9 | |
| 2:25–3:00 | 35 min | 审 demo | |

**D5 验收**：`[ ]` Demo `[ ]` 卡 `[ ]` 默答

### D6 · 题库冲刺（3 h）

S1–S9 全真口述；卡壳进 [SVC错题库](SVC错题库.md)。

**D6 验收**：`[ ]` S1–S9 ≥7/9

### D7 · 弱项复跑（3 h）

复跑 1 个 Demo + 刷错题库；选做 S10+。

**D7 验收**：`[ ]` 错题库无未订正开口题

---

## 硬规则

1. **到点停**：Demo 做不完先记 blocker。  
2. **卡不超过 30 min/张**。  
3. **每天默答 ≥15 min**。  
4. **不深挖**：AIDL 跨进程细节、AMS 源码——知道有即可。  
5. **Studio 打开 Demo 工程根**（有 `settings.gradle.kts` 的 `svc-d*`），不要打开 `demos/` 或 `service/`。

---

## 和我怎么配合

| 你说 | 何时 |
|------|------|
| 「教我 SVC D2」或「教我 D2」（当前在 SVC 线） | 输入阶段 |
| 「帮我做」 | Demo |
| 「审 demo」 | Demo 后 |
| 「输出 SVC-001,1」 | 写卡 |
| 「默答 S3」 | 遮卡开口 |
| 「记错题」 | 写入错题库 |
| 「教我 BR D1」 / 「教我 CP D1」 / 「教我 ACT D2」 | 切线 |

**现在进行到**：SVC **D0 待开**。说「教我 SVC D0」或「教我 D0」。
