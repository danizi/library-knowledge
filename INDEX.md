# 卡片索引

按领域浏览；新建或移动卡片后请同步更新本页。  
知识根目录：`knowledge/` · 概念卡模板（费曼：问/答/一句话/例或类比/易错）：`[.cursor/templates/card.md](.cursor/templates/card.md)` · 编排：`[.cursor/README.md](.cursor/README.md)`

含 `|` 的卡片文件名，链接写成 `[标题](<knowledge/Android/编码 | 标题.md>)`，用 `<>` 包真实路径；不要写成 `%20%7C`，Cursor 会点不开。

> `knowledge/Android|Web|Tools` 下概念卡正面是问、一句话、一个例子。答、类比、易错、相关、参考折叠。句子一句一个事实。  
> `JavascriptDemo/` 仍是练习场，不成概念卡。  
> 学习法和固定项目文档在 `.cursor/docs/`，不占领域编号。

---

## 固定文档

- [费曼学习法](.cursor/docs/费曼学习法.md) — 本库学习行为准则；图 [feynman-method.canvas.tsx](.cursor/docs/feynman-method.canvas.tsx)；**仍待审查**
- [知识看板](.cursor/docs/kanban.html) — 网页。先运行 `python3 .cursor/docs/kanban_server.py`。数据在 SQLite，点开可看详情，普通编辑和 Markdown 可切换。不回写正式卡

---

## Android

### ACT · Activity

- [ACT 学习计划 · 7 天稳妥](knowledge/Android/ACT学习计划.md)
- [ACT 面试题库](knowledge/Android/ACT面试题库.md)
- [ACT 错题库](knowledge/Android/ACT错题库.md) — 只记你答歪过的，复习优先于题库全表
- Demo（命名：`act-d{天}-主题`；与 `fg-d*` / `svc-d*` / `br-d*` / `cp-d*` 各算各的）
  - [act-d0-hello](knowledge/Android/demos/activity/act-d0-hello/)（D0）
  - [act-d1-two-screens](knowledge/Android/demos/activity/act-d1-two-screens/)（D1）
  - [act-d2-lifecycle-log](knowledge/Android/demos/activity/act-d2-lifecycle-log/)（D2）
  - [act-d3-intent-extra](knowledge/Android/demos/activity/act-d3-intent-extra/)（D3）
  - [act-d4-survive-rotate](knowledge/Android/demos/activity/act-d4-survive-rotate/)（D4）
  - [act-d5-host-fragment](knowledge/Android/demos/activity/act-d5-host-fragment/)（D5）
- [ACT-001 | Activity](<knowledge/Android/ACT-001 | Activity.md>)
  - [ACT-001,1 | Activity生命周期](<knowledge/Android/ACT-001,1 | Activity生命周期.md>)
  - [ACT-001,2 | Intent与传参](<knowledge/Android/ACT-001,2 | Intent与传参.md>)
  - [ACT-001,3 | 配置变更与状态](<knowledge/Android/ACT-001,3 | 配置变更与状态.md>)
  - [ACT-001,4 | 启动模式与任务栈](<knowledge/Android/ACT-001,4 | 启动模式与任务栈.md>)



### FG · Fragment

- [FG 学习计划 · 11 天稳妥](knowledge/Android/FG学习计划.md)
- [FG 面试题库](knowledge/Android/FG面试题库.md)
- [FG 错题库](knowledge/Android/FG错题库.md)
- Demo（交界工程在 ACT 目录；FG 自有 `fg-d*`）
  - [act-d5-host-fragment](knowledge/Android/demos/activity/act-d5-host-fragment/)（D0 · 交界，工程仍在 ACT 目录）
  - [fg-d1-backstack](knowledge/Android/demos/fragment/fg-d1-backstack/)（D1）
  - [fg-d2-dialog-fragment](knowledge/Android/demos/fragment/fg-d2-dialog-fragment/)（D2）
  - [fg-d3-fragment-transaction](knowledge/Android/demos/fragment/fg-d3-fragment-transaction/)（D3）
  - [fg-d4-view-lifecycle](knowledge/Android/demos/fragment/fg-d4-view-lifecycle/)（D4）
  - [fg-d5-fragment-result](knowledge/Android/demos/fragment/fg-d5-fragment-result/)（D5）
  - [fg-d6-nested-fragment](knowledge/Android/demos/fragment/fg-d6-nested-fragment/)（D6）
  - [fg-d7-fragment-args](knowledge/Android/demos/fragment/fg-d7-fragment-args/)（D7）
  - [fg-d8-navigation](knowledge/Android/demos/fragment/fg-d8-navigation/)（D8）
  - [fg-d9-viewpager2](knowledge/Android/demos/fragment/fg-d9-viewpager2/)（D9）
- [FG-001 | Fragment](<knowledge/Android/FG-001 | Fragment.md>)
  - [FG-001,1 | Fragment生命周期](<knowledge/Android/FG-001,1 | Fragment生命周期.md>)
  - [FG-001,2 | DialogFragment](<knowledge/Android/FG-001,2 | DialogFragment.md>)
  - [FG-001,3 | Fragment回退栈](<knowledge/Android/FG-001,3 | Fragment回退栈.md>)
  - [FG-001,4 | Fragment通信](<knowledge/Android/FG-001,4 | Fragment通信.md>)
  - [FG-001,5 | Fragment与Activity关联](<knowledge/Android/FG-001,5 | Fragment与Activity关联.md>)
  - [FG-001,6 | Fragment事务](<knowledge/Android/FG-001,6 | Fragment事务.md>)
  - [FG-001,7 | viewLifecycleOwner](<knowledge/Android/FG-001,7 | viewLifecycleOwner.md>)
  - [FG-001,8 | 嵌套Fragment](<knowledge/Android/FG-001,8 | 嵌套Fragment.md>)
  - [FG-001,9 | Fragment传参与状态](<knowledge/Android/FG-001,9 | Fragment传参与状态.md>)
  - [FG-001,10 | Navigation与Fragment](<knowledge/Android/FG-001,10 | Navigation与Fragment.md>)
  - [FG-001,11 | ViewPager2与Fragment](<knowledge/Android/FG-001,11 | ViewPager2与Fragment.md>)



### SVC · Service

- [SVC 学习计划 · 8 天稳妥](knowledge/Android/SVC学习计划.md)
- [SVC 面试题库](knowledge/Android/SVC面试题库.md)
- [SVC 错题库](knowledge/Android/SVC错题库.md)
- Demo（命名：`svc-d{天}-主题`，目录 `demos/service/`；待建）
  - svc-d0-hello … svc-d5-workmanager-boundary（按计划待建）
- 卡片（待建）
  - SVC-001 Service
  - SVC-001,1 生命周期
  - SVC-001,2 绑定与 Binder
  - SVC-001,3 前台 Service
  - SVC-001,4 后台限制与 WorkManager



### BR · BroadcastReceiver

- [BR 学习计划 · 6 天稳妥](knowledge/Android/BR学习计划.md)
- [BR 面试题库](knowledge/Android/BR面试题库.md)
- [BR 错题库](knowledge/Android/BR错题库.md) — 题号 **BR1–**，勿与 ACT「B 档」混淆
- Demo（命名：`br-d{天}-主题`，目录 `demos/broadcast/`；待建）
  - br-d0-hello … br-d4-local-vs-system（按计划待建）
- 卡片（待建）
  - BR-001 BroadcastReceiver
  - BR-001,1 动态注册
  - BR-001,2 有序与权限
  - BR-001,3 本地广播与替代



### CP · ContentProvider

- [CP 学习计划 · 5 天稳妥](knowledge/Android/CP学习计划.md)
- [CP 面试题库](knowledge/Android/CP面试题库.md)
- [CP 错题库](knowledge/Android/CP错题库.md)
- Demo（命名：`cp-d{天}-主题`，目录 `demos/contentprovider/`；待建）
  - cp-d0-hello … cp-d3-permission（按计划待建）
- 卡片（待建）
  - CP-001 ContentProvider
  - CP-001,1 CRUD 与 Cursor
  - CP-001,2 权限与导出



### BT · Bluetooth

- [BT 学习计划 · 12 天稳妥](knowledge/Android/BT学习计划.md)
- [BT 面试题库](knowledge/Android/BT面试题库.md)
- [BT 错题库](knowledge/Android/BT错题库.md) — 只记你答歪过的，复习优先于题库全表
- Demo（命名：`bt-d{天}-主题`；与 `act-d*` / `fg-d*` / `svc-d*` / `br-d*` / `cp-d*` 各算各的）
  - [bt-d0-hello](knowledge/Android/demos/bluetooth/bt-d0-hello/)（D0）
  - [bt-d1-adapter](knowledge/Android/demos/bluetooth/bt-d1-adapter/)（D1）
  - [bt-d2-permission](knowledge/Android/demos/bluetooth/bt-d2-permission/)（D2）
  - [bt-d3-scan](knowledge/Android/demos/bluetooth/bt-d3-scan/)（D3）
  - [bt-d4-scan-settings](knowledge/Android/demos/bluetooth/bt-d4-scan-settings/)（D4）
  - [bt-d5-connect](knowledge/Android/demos/bluetooth/bt-d5-connect/)（D5）
  - [bt-d6-read](knowledge/Android/demos/bluetooth/bt-d6-read/)（D6）
  - [bt-d7-write](knowledge/Android/demos/bluetooth/bt-d7-write/)（D7）
  - [bt-d8-notify](knowledge/Android/demos/bluetooth/bt-d8-notify/)（D8）
  - [bt-d9-classic](knowledge/Android/demos/bluetooth/bt-d9-classic/)（D9）
- [BT-001 | 蓝牙](<knowledge/Android/BT-001 | 蓝牙.md>) — **仍待审查**
  - [BT-001,1 | 蓝牙权限](<knowledge/Android/BT-001,1 | 蓝牙权限.md>) — **仍待审查**
  - [BT-001,2 | BLE扫描](<knowledge/Android/BT-001,2 | BLE扫描.md>) — **仍待审查**
  - [BT-001,3 | GATT读写](<knowledge/Android/BT-001,3 | GATT读写.md>) — **仍待审查**
  - [BT-001,4 | 经典蓝牙对照](<knowledge/Android/BT-001,4 | 经典蓝牙对照.md>) — **仍待审查**
  - [BT-001,5 | 清单声明](<knowledge/Android/BT-001,5 | 清单声明.md>) — **仍待审查**
  - [BT-001,6 | 扫描设置](<knowledge/Android/BT-001,6 | 扫描设置.md>) — **仍待审查**
  - [BT-001,7 | 连接与关闭](<knowledge/Android/BT-001,7 | 连接与关闭.md>) — **仍待审查**
  - [BT-001,8 | 写特征](<knowledge/Android/BT-001,8 | 写特征.md>) — **仍待审查**
  - [BT-001,9 | 通知](<knowledge/Android/BT-001,9 | 通知.md>) — **仍待审查**



### NFC · NFC

- [NFC 学习计划 · 10 天稳妥](knowledge/Android/NFC学习计划.md)
- [NFC 面试题库](knowledge/Android/NFC面试题库.md)
- [NFC 错题库](knowledge/Android/NFC错题库.md) — 只记你答歪过的，复习优先于题库全表
- Demo（命名：`nfc-d{天}-主题`；与 `act-d*` / `bt-d*` 各算各的）
  - [nfc-d0-hello](knowledge/Android/demos/nfc/nfc-d0-hello/)（D0）
  - [nfc-d1-adapter](knowledge/Android/demos/nfc/nfc-d1-adapter/)（D1）
  - [nfc-d2-dispatch](knowledge/Android/demos/nfc/nfc-d2-dispatch/)（D2）
  - [nfc-d3-view](knowledge/Android/demos/nfc/nfc-d3-view/)（D3）
  - [nfc-d4-read](knowledge/Android/demos/nfc/nfc-d4-read/)（D4）
  - [nfc-d5-foreground](knowledge/Android/demos/nfc/nfc-d5-foreground/)（D5）
  - [nfc-d6-write](knowledge/Android/demos/nfc/nfc-d6-write/)（D6）
  - [nfc-d7-hce](knowledge/Android/demos/nfc/nfc-d7-hce/)（D7）
- [NFC-001 | NFC](<knowledge/Android/NFC-001 | NFC.md>) — **仍待审查**
  - [NFC-001,1 | 清单声明](<knowledge/Android/NFC-001,1 | 清单声明.md>) — **仍待审查**
  - [NFC-001,2 | 三种Action](<knowledge/Android/NFC-001,2 | 三种Action.md>) — **仍待审查**
  - [NFC-001,3 | 网址标签](<knowledge/Android/NFC-001,3 | 网址标签.md>) — **仍待审查**
  - [NFC-001,4 | 读NDEF](<knowledge/Android/NFC-001,4 | 读NDEF.md>) — **仍待审查**
  - [NFC-001,5 | 前台分发](<knowledge/Android/NFC-001,5 | 前台分发.md>) — **仍待审查**
  - [NFC-001,6 | 写NDEF](<knowledge/Android/NFC-001,6 | 写NDEF.md>) — **仍待审查**
  - [NFC-001,7 | HCE对照](<knowledge/Android/NFC-001,7 | HCE对照.md>) — **仍待审查**



### V · View / UI / Compose

- [V-001 | View的定义](<knowledge/Android/V-001 | View的定义.md>)
- [V-002 | 盒子模型](<knowledge/Android/V-002 | 盒子模型.md>)
- [V-003 | 坐标系](<knowledge/Android/V-003 | 坐标系.md>)
- [V-004 | Widget](<knowledge/Android/V-004 | Widget.md>)
- [V-005 | Jetpack Compose](<knowledge/Android/V-005 | Jetpack Compose.md>)
  - [V-005,1 | Preview注解](<knowledge/Android/V-005,1 | Preview注解.md>)
  - [V-005,2 | 声明式与命令式对比](<knowledge/Android/V-005,2 | 声明式与命令式对比.md>)
  - [V-005,4 | Compose 核心 API 分类](<knowledge/Android/V-005,4 | Compose 核心 API 分类.md>)
    - [V-005,4,a | Modifier](<knowledge/Android/V-005,4,a | Modifier.md>)



### PD · 工程与资源

- [PD-001 | 工程文件目录](<knowledge/Android/PD-001 | 工程文件目录.md>) — 易错已补「Open Gradle 根」；**仍待审卡**
  - [PD-001,1 | res资源文件夹](<knowledge/Android/PD-001,1 | res资源文件夹.md>)
    - [PD-001,1,a | drawable目录](<knowledge/Android/PD-001,1,a | drawable目录.md>)
      - [PD-001,1,a,1 | 状态列表的使用](<knowledge/Android/PD-001,1,a,1 | 状态列表的使用.md>)
      - [PD-001,1,a,2 | View状态常量](<knowledge/Android/PD-001,1,a,2 | View状态常量.md>)
      - [PD-001,1,a,3 | 形状可绘制对象](<knowledge/Android/PD-001,1,a,3 | 形状可绘制对象.md>)
    - [PD-001,1,b | values目录](<knowledge/Android/PD-001,1,b | values目录.md>)
      - [PD-001,1,b,1 | 记一次主题样式重构](<knowledge/Android/PD-001,1,b,1 | 记一次主题样式重构.md>)
  - [PD-001,2 | res资源文件夹下命名规范](<knowledge/Android/PD-001,2 | res资源文件夹下命名规范.md>)
  - [PD-001,3 | AndroidManifest清单文件](<knowledge/Android/PD-001,3 | AndroidManifest清单文件.md>)



### KT · Kotlin

- [KT-001 | Kotlin](<knowledge/Android/KT-001 | Kotlin.md>)
  - [KT-001,1 | 基本概念](<knowledge/Android/KT-001,1 | 基本概念.md>)
  - [KT-001,2 | Flow 的使用](<knowledge/Android/KT-001,2 | Flow 的使用.md>)



### AAP · 架构 / DP · 设计模式 / ERR · 踩坑

- [AAP-001 | Android 架构模式](<knowledge/Android/AAP-001 | Android 架构模式.md>)
- [DP-001 | 设计模式](<knowledge/Android/DP-001 | 设计模式.md>)
- [ERR-001 | 开发踩坑记录](<knowledge/Android/ERR-001 | 开发踩坑记录.md>)



### 待建（原 INDEX 规划）

- 系统控件：RecyclerView / TabLayout
- 第三方库：AOP / Router

---



## Web



### R · React

- [R-001 | React](<knowledge/Web/R-001 | React.md>)
  - [R-001,a | 环境搭建](<knowledge/Web/R-001,a | 环境搭建.md>) — 只留「为何 Vite」；**仍待审卡**
    - [R-001,a,1 | 跑起 Vite React](<knowledge/Web/R-001,a,1 | 跑起 Vite React.md>) — 过程卡，**已晋升 · 仍待审查**
  - [R-001,b | 基本语法](<knowledge/Web/R-001,b | 基本语法.md>)
  - [R-001,c | 项目使用及说明](<knowledge/Web/R-001,c | 项目使用及说明.md>)



### 练习场（非卡片）

- [JavascriptDemo · JS 学习计划](knowledge/Web/JavascriptDemo/JS学习计划.md)

---



## Tools

- [TL-001 | VS Code Git](<knowledge/Tools/TL-001 | VS Code Git.md>)
  - [TL-001,1 | git pull 分叉速查](<knowledge/Tools/TL-001,1 | git pull 分叉速查.md>)
- [TL-002 | macOS 安装 Homebrew](<knowledge/Tools/TL-002 | macOS 安装 Homebrew.md>) — 只留「为何镜像、路径分两种」；**仍待审卡**
  - [TL-002,1 | 镜像安装 Homebrew](<knowledge/Tools/TL-002,1 | 镜像安装 Homebrew.md>) — 过程卡，**已晋升 · 仍待审查**
- [TL-003 | 修改 Hosts](<knowledge/Tools/TL-003 | 修改 Hosts.md>) — 只留「为何不生效」；**仍待审卡**
  - [TL-003,1 | 写入 Hosts 并刷新 DNS](<knowledge/Tools/TL-003,1 | 写入 Hosts 并刷新 DNS.md>) — 过程卡，**已晋升 · 仍待审查**
- [TL-004 | Cursor Canvas是什么](<knowledge/Tools/TL-004 | Cursor Canvas是什么.md>) — **仍待审查**
  - [TL-004,1 | cursor怎么打开Canvas预览](<knowledge/Tools/TL-004,1 | cursor怎么打开Canvas预览.md>) — **仍待审查**
- [TL-005 | Anthropic提示词结构](<knowledge/Tools/TL-005 | Anthropic提示词结构.md>) — **仍待审查**
- [TL-006 | OpenAI提示词结构](<knowledge/Tools/TL-006 | OpenAI提示词结构.md>) — **仍待审查**

---



## memory · 未审核

- [AI 学习计划 · 三个练习项目](.cursor/memory/AI学习计划.md) — **仍待审查**。笔记问答、订单客服、Docker。对得上技能词，对不上年限和真实用户上线
- [react语法学习](.cursor/memory/react语法学习.md) → 摘抄，不整篇晋升；能默答的一句再拆进 `R-001`

## memory · 已吸收

- [归档说明](.cursor/memory/absorbed/README.md) — 14 条 ACT/FG Demo 笔记。结论已在对应概念卡；D0 的「Open Gradle 根」写进了 PD-001 易错。不再当未审核。



## skills

- [android-studio-autosetup](.cursor/skills/android-studio-autosetup/SKILL.md)
- `kb-card-review` / `kb-code-review` / `kb-coach` / `kb-promote` / `kb-verify` / `kb-tutor`
- 每日新闻简报 | `daily-briefing`
- 改界面 / critique / polish | `impeccable`

