# ContentProvider 学习计划 · 5 天稳妥

目标：**5 天内**把 ContentProvider 高频点走完。每天约 **3 h**，到点停。  
原则：Demo 不过不写卡；卡写完必须 **15 分钟默答**。

日程用 **D0–D4**。题库用 **P1–P8**。卡片用 **CP-001,x**。  
切线：「教我 SVC D1」/「教我 BR D1」/「教我 ACT D2」。

配套：[CP面试题库](CP面试题库.md) · [CP错题库](CP错题库.md)  
Demo 目录：`knowledge/Android/demos/contentprovider/`  
命名：`cp-d{日程天}-{主题}`（如 `cp-d2-crud`）。

## 学习重点

必学：ContentProvider 是数据出口，ContentResolver 是调用方（D1）、增删改查和 Cursor 谁负责关（D2）、导出和权限（D3）。

知道即可：插入不一定要理解成「永远在主线程」，先会把耗时查询放离界面。没有 Provider 也能用 CursorLoader，那是加载器的用法，不是本模块要先建的东西。

不必深挖：用 Provider 安装 APK、为了 SyncAdapter 硬凑一个空 Provider、用 Gradle build type 在同一台机器装两个带同一个 authority 的包。这些是排障题，不是入门主线。

## 读过的材料

- https://developer.android.com/guide/topics/providers/content-provider-basics
- https://developer.android.com/guide/topics/providers/content-provider-creating

## 常见坑

`[android]` 下按标题搜 ContentProvider、按票排序。这个标签组合没有单独的高票列表，下面是搜到的 8 条。

| 票 | 现象 | 链接 |
|----|------|------|
| 132 | 同一台机器装两个变体，authority 撞车 | [16777534](https://stackoverflow.com/questions/16777534/using-build-types-in-gradle-to-run-same-app-that-uses-contentprovider-on-one-dev) |
| 108 | 没有 ContentProvider 也想用 CursorLoader | [7182485](https://stackoverflow.com/questions/7182485/cursorloader-usage-without-contentprovider) |
| 77 | Provider 里数据库何时关闭 | [4547461](https://stackoverflow.com/questions/4547461/closing-the-database-in-a-contentprovider) |
| 61 | Provider 和 Resolver 有什么差别 | [18874801](https://stackoverflow.com/questions/18874801/what-is-difference-between-contentprovider-and-contentresolver-in-android) |
| 46 | SQLite 和 Provider 怎么摆 | [1725880](https://stackoverflow.com/questions/1725880/best-practices-for-sqlite-db-and-contentprovider) |
| 43 | 能不能从 Provider 安装 APK | [9637629](https://stackoverflow.com/questions/9637629/can-we-install-an-apk-from-a-contentprovider) |
| 39 | SyncAdapter 能否不要 Provider | [4649808](https://stackoverflow.com/questions/4649808/syncadapter-without-a-contentprovider) |
| 38 | 以为 `insert` 总在主线程 | [11604914](https://stackoverflow.com/questions/11604914/contentprovider-insert-always-runs-on-ui-thread) |

---

## 5 天总览

| 天 | 主题 | 产出 | 面试覆盖 |
|----|------|------|----------|
| D0 | 空工程 | `cp-d0-hello` | — |
| D1 | 是什么 / URI / Manifest | `CP-001` + cp-d1 | P1 P2 |
| D2 | CRUD + Cursor | `CP-001,1` + cp-d2 | P3 P4 |
| D3 | 权限 / exported / 跨应用 | `CP-001,2` + cp-d3 | P5 P6 |
| D4 | 冲刺；FileProvider 交界 | 默答 + 选做 | P7 P8 |

---

## 知识点总表

| 知识点 | 卡 | 天 | 状态 |
|--------|----|----|------|
| 是什么、authority、URI | `CP-001` | D1 | **待建** |
| query/insert/update/delete + Cursor | `CP-001,1` | D2 | **待建** |
| 权限与导出 | `CP-001,2` | D3 | **待建** |
| FileProvider（选做加厚，可不单开卡） | — | D4 | 选做 |

---

## 每日日程

### D0 · 工程能跑（2 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–1:20 | 80 min | Demo `cp-d0-hello/` | Run OK |
| 1:20–2:00 | 40 min | 扫 Manifest `provider` / authorities | |

**D0 验收**：`[ ]` Run OK

### D1 · 是什么 + URI（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 跨应用数据出口直觉；content:// | |
| 0:25–1:35 | 70 min | Demo `cp-d1-uri-manifest/` | authority 对得上 |
| 1:35–2:05 | 30 min | 写 `CP-001 \| ContentProvider.md` | |
| 2:05–2:25 | 20 min | 默答 P1 P2 | |
| 2:25–3:00 | 35 min | 审 demo | |

**D1 验收**：`[ ]` Demo `[ ]` 卡 `[ ]` 默答

### D2 · CRUD（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 四个方法 + Cursor 要关 | |
| 0:20–1:30 | 70 min | Demo `cp-d2-crud/`：增查改删 | |
| 1:30–2:00 | 30 min | 写 `CP-001,1 \| CRUD与Cursor.md` | |
| 2:00–2:20 | 20 min | 默答 P3 P4 | |
| 2:20–3:00 | 40 min | 审 demo | |

**D2 验收**：`[ ]` Demo `[ ]` 卡 `[ ]` 默答

### D3 · 权限与跨应用（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | read/write permission；exported | |
| 0:25–1:35 | 70 min | Demo `cp-d3-permission/` | |
| 1:35–2:05 | 30 min | 写 `CP-001,2 \| 权限与导出.md` | |
| 2:05–2:25 | 20 min | 默答 P5 P6 | |
| 2:25–3:00 | 35 min | 审 demo | |

**D3 验收**：`[ ]` Demo `[ ]` 卡 `[ ]` 默答

### D4 · 冲刺 + FileProvider（3 h）

P1–P6 全真；口头 FileProvider（分享文件 URI）。卡壳进错题库。

**D4 验收**：`[ ]` ≥5/6 `[ ]` 能说 FileProvider 一句话 `[ ]` 错题库无未订正

---

## 硬规则

1. 到点停。  
2. 卡 ≤30 min。  
3. 每天默答 ≥15 min。  
4. 不深挖：自研同步适配器全家桶。  
5. Studio 打开 `cp-d*` 工程根。

---

## 和我怎么配合

| 你说 | 何时 |
|------|------|
| 「教我 CP D1」 | 输入 |
| 「帮我做」 / 「审 demo」 / 「输出 CP-001,1」 / 「默答 P3」 / 「记错题」 | 闭环 |
| 「教我 SVC D0」等 | 切线 |

**现在进行到**：CP 线已建骨架，**等你点名**「教我 CP D0」（建议先走完 SVC）。
