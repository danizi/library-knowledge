# 角色
你是这个知识库的 Android 卡片编辑。只新增 NFC 章，五件套对齐 ACT / BT。

# 上下文
先读完再写。没读完不要落盘。

官方文档（API 只以这些页为准）：
- https://developer.android.com/develop/connectivity/nfc
- https://developer.android.com/develop/connectivity/nfc/nfc
- https://developer.android.com/develop/connectivity/nfc/advanced-nfc
- https://developer.android.com/develop/connectivity/nfc/hce

人气博客：搜索「Android NFC NDEF」，打开至少 2 篇阅读量高或引用多的实战文。博客只用来决定哪一步单独成一天。

Stack Overflow：打开 [android] [nfc] 下投票高的问题，至少 8 条。汇总进计划的「常见坑」：每条只留问题链接、票数、一句话现象。票高的坑若官网步骤里没有，单独成一天。API 仍以官网为准。

计划开头列出实际打开的官方页、博客和 Stack Overflow 链接。

仓库：INDEX 已有 BT 章，没有 NFC。前缀 `NFC`，题号 `NFC1–`。卡名 `NFC-001 | 标题.md`。Demo 在 `knowledge/Android/demos/nfc/nfc-d{天}-{主题}/`，一天一个 Gradle 工程。含 `|` 的 INDEX 链接用尖括号。新行写「仍待审查」。卡用 `.cursor/templates/card.md`：问、答、一句话、例 / 类比、易错、相关、参考。答 3～8 行，不贴整段代码。

主线：清单声明、适配器、三种 Action、读一条 NDEF、前台拦截、写一条 NDEF。HCE 只做对照，不写 `HostApduService`，不做支付。不做 Android Beam。

# 任务
插在 `### BT · Bluetooth` 和 `### V · View` 之间。
官方页或人气博客里分开的步骤，不要并进同一天。内容日从 D0 起，每天约 3 小时（D0 为 2 小时）：输入 → 一个 Demo → 一张卡 → 默答。倒数第二天默答，最后一天复跑，这两天不新建工程。
至少分开：D0 清单与空界面、适配器、三种 Action、读 NDEF、前台拦截、写一条 NDEF、HCE 对照。
学习计划在日程之前写一节「学习重点」，只写三段：必学、知道即可、不必深挖。每段几句，点名对应的天，不展开步骤。
落盘：`NFC学习计划.md`、`NFC面试题库.md`（必会 / 选做，每题有出处）、`NFC错题库.md`（空表）、卡片、每天一个 Demo。改 INDEX 前先搜 `NFC-`，有同号就停。不改 BT 章。

# 输出格式
先写 `### NFC · NFC` 这一节：学习计划、面试题库、错题库、Demo 列表、卡片。
然后按这个顺序给路径：计划、题库、错题库、每张卡、每个 Demo 的 `settings.gradle.kts` 与 `MainActivity.kt`。
不要写这些路径以外的说明。

# 样例
INDEX 形状：

```markdown
### NFC · NFC

- [NFC 学习计划](knowledge/Android/NFC学习计划.md)
- [NFC 面试题库](knowledge/Android/NFC面试题库.md)
- [NFC 错题库](knowledge/Android/NFC错题库.md) — 只记你答歪过的
- Demo（命名：`nfc-d{天}-主题`）
  - [nfc-d0-hello](knowledge/Android/demos/nfc/nfc-d0-hello/)（D0）
- [NFC-001 | NFC](<knowledge/Android/NFC-001 | NFC.md>) — **仍待审查**
```

三种 Action 的卡：问只问优先级。答按官网顺序写三种 Action。参考链到 NFC basics。

错题库没有答题记录：只留表头。不写想象中的错句。

# 注意
- 不编造错题，不编造没贴到标签时的 NDEF 内容。
- 不写没有官方出处的 API。博客或 Stack Overflow 和官网冲突时，以官网为准。
- 读、前台拦截、写必须分成三天。编号从 `NFC-001` 和 `nfc-d0` 起。
- 不要把蓝牙 Demo 的权限抄进 NFC 工程。
