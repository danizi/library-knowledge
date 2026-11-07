# NFC 学习计划 · 10 天稳妥

目标：**10 天内**走完读一条 NDEF、前台拦截、写回一条 NDEF。每天约 **3 h**，D0 为 **2 h**，到点停。  
原则：Demo 不过不写卡；卡写完必须 **15 分钟默答**。一天只有一个新行为。

D0–D7 各有一个 Demo 和一张卡。D8 默答，D9 复跑，不新建工程。  
题号 **NFC1–**。卡片 **NFC-001,x**。Demo：`knowledge/Android/demos/nfc/nfc-d{天}-{主题}`。

不做支付，不写 `HostApduService`，不做 Android Beam。

## 学习重点

必学：清单声明（D0）、适配器是没有芯片还是没打开（D1）、三种 Action 的优先级（D2）、读一条 NDEF 时跳过语言码（D4）、前台拦截的打开和关闭（D5）、写一条 NDEF 且不在主线程（D6）。面试和 Demo 都落在这六天。

知道即可：Android 16 起网址标签改走 `ACTION_VIEW`（D3）。手机当卡是另一种模式（D7），能讲清和读写标签的差别即可，不实现。

不必深挖：支付和 `HostApduService`、Android Beam、MIFARE 认证、用代码开关 NFC、远距离 RFID、在模拟器里贴卡。这些在常见问题里出现过，但不在这条实战里。

## 读过的材料

官方：

- https://developer.android.com/develop/connectivity/nfc
- https://developer.android.com/develop/connectivity/nfc/nfc
- https://developer.android.com/develop/connectivity/nfc/advanced-nfc
- https://developer.android.com/develop/connectivity/nfc/hce
- https://developer.android.com/reference/android/nfc/NfcAdapter#isEnabled()
- https://developer.android.com/reference/android/nfc/tech/NdefFormatable

博客（只用来判断要不要拆天）：

- https://nfc.guide/nfc-for-developers/writing-ndef-from-an-android-app.html
- https://xckevin.com/blog/android-nfc-nfcadapter-ndef/

Stack Overflow（`[android] [nfc]` 按票排序）：

- https://stackoverflow.com/questions/6138077/can-an-android-nfc-phone-act-as-an-nfc-tag
- https://stackoverflow.com/questions/33346378/how-to-get-nfc-working-on-android-using-qt-5-6
- https://stackoverflow.com/questions/13081926/reading-rfid-with-android-phones
- https://stackoverflow.com/questions/20055497/emulate-mifare-card-with-android-4-4
- https://stackoverflow.com/questions/19438554/android-authenticating-with-nxp-mifare-ultralight-c
- https://stackoverflow.com/questions/49146532/nfc-tag-emulation-for-android-virtual-device-avd
- https://stackoverflow.com/questions/7618657/how-to-check-whether-nfc-is-enabled-or-not-in-android
- https://stackoverflow.com/questions/5945100/android-changing-nfc-settings-on-off-programmatically

## 常见坑

| 票 | 现象 | 链接 |
|----|------|------|
| 131 | 手机想反过来当一张卡 | [6138077](https://stackoverflow.com/questions/6138077/can-an-android-nfc-phone-act-as-an-nfc-tag) |
| 56 | 用 Qt 调 Android NFC | [33346378](https://stackoverflow.com/questions/33346378/how-to-get-nfc-working-on-android-using-qt-5-6) |
| 55 | 把 RFID 远距离读卡当成手机 NFC | [13081926](https://stackoverflow.com/questions/13081926/reading-rfid-with-android-phones) |
| 42 | 要模拟 MIFARE 卡 | [20055497](https://stackoverflow.com/questions/20055497/emulate-mifare-card-with-android-4-4) |
| 39 | Ultralight C 认证失败 | [19438554](https://stackoverflow.com/questions/19438554/android-authenticating-with-nxp-mifare-ultralight-c) |
| 38 | 模拟器里做不了贴卡 | [49146532](https://stackoverflow.com/questions/49146532/nfc-tag-emulation-for-android-virtual-device-avd) |
| 37 | 分不清「没有芯片」和「设置里关掉」 | [7618657](https://stackoverflow.com/questions/7618657/how-to-check-whether-nfc-is-enabled-or-not-in-android) |
| 31 | 想用代码直接开关 NFC | [5945100](https://stackoverflow.com/questions/5945100/android-changing-nfc-settings-on-off-programmatically) |

131 票的「手机当卡」就是官方第二种模式，单独放在 D7，不写成支付。37 票和 31 票并进 D1：官方指南只把 `getDefaultAdapter() == null` 写成「没有硬件」；开关是否打开要再看 `isEnabled()`，而且这四篇指南里没有蓝牙那种申请打开的弹窗。38 票写进 D1 的易错：模拟器经常是 null。56、39 票不单开一天，本路线不走 Qt，也不做卡片密码。

## 10 天总览

| 天 | 主题 | 产出 | 题号 |
|----|------|------|------|
| D0 | 清单 + 空界面 | nfc-d0-hello + NFC-001,1 | NFC1 |
| D1 | 适配器在不在、开没开 | NFC-001 + nfc-d1-adapter | NFC2 NFC3 |
| D2 | 三种 Action | NFC-001,2 + nfc-d2-dispatch | NFC4 |
| D3 | 网址标签走 VIEW | NFC-001,3 + nfc-d3-view | NFC5 |
| D4 | 读一条 NDEF | NFC-001,4 + nfc-d4-read | NFC6 |
| D5 | 前台拦截 | NFC-001,5 + nfc-d5-foreground | NFC7 |
| D6 | 写一条 NDEF | NFC-001,6 + nfc-d6-write | NFC8 |
| D7 | HCE 对照 | NFC-001,7 + nfc-d7-hce | NFC9 |
| D8 | 默答 | 口头过 NFC1–NFC9 | 全真 |
| D9 | 复跑 | 复跑 1 个 Demo | 选做 NFC10 |

配套：[NFC面试题库](NFC面试题库.md) · [NFC错题库](NFC错题库.md)

---

## 每日日程

格式：`输入 → Demo → 输出卡 → 默答`。

### D0 · 清单（2 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:40 | 40 min | 读 NFC basics 的 manifest 一节 | 能指出 permission 和 uses-feature |
| 0:40–1:20 | 40 min | Run `nfc-d0-hello/` | 屏幕写明今天不读卡 |
| 1:20–2:00 | 40 min | 写 NFC-001,1，默答 NFC1 | 不看卡 |

**D0 验收**：`[ ]` Run OK `[ ]` 未弹出运行时权限框 `[ ]` 卡已落盘

### D1 · 适配器（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 读 basics 里 `getDefaultAdapter()` 为 null 的那句，再读 `NfcAdapter.isEnabled` | 能分开两句 |
| 0:20–1:30 | 70 min | `nfc-d1-adapter/` | 不出现「打开 NFC」按钮 |
| 1:30–2:20 | 50 min | 写 NFC-001，默答 NFC2、NFC3 | 不看卡 |
| 2:20–3:00 | 40 min | 在模拟器上看一次 null | 知道要换真机 |

**D1 验收**：`[ ]` 无芯片和已关闭文案不同 `[ ]` 卡已落盘

### D2 · 三种 Action（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:30 | 30 min | 读 basics 的三种 intent | 能按优先级排序 |
| 0:30–1:40 | 70 min | `nfc-d2-dispatch/` | 没贴卡时显示「还没贴到标签」 |
| 1:40–2:20 | 40 min | 写 NFC-001,2，默答 NFC4 | 不看卡 |
| 2:20–3:00 | 40 min | 有标签时只看 Action 名字 | 今天不解析正文 |

**D2 验收**：`[ ]` 没贴卡不造记录 `[ ]` 卡已落盘

### D3 · 网址标签（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 读 basics 的 ACTION_VIEW 小节 | 能说出从哪一版开始 |
| 0:25–1:30 | 65 min | `nfc-d3-view/` | 只显示 action 或网址 |
| 1:30–2:10 | 40 min | 写 NFC-001,3，默答 NFC5 | 不看卡 |
| 2:10–3:00 | 50 min | 对照 D2，口头讲网址为什么可能进不了 NDEF | 不改 D2 工程 |

**D3 验收**：`[ ]` 卡写明 Android 16 `[ ]` 默答通过

### D4 · 读 NDEF（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 读 basics 的 EXTRA_NDEF_MESSAGES 和 RTD_TEXT | 知道正文前有语言码 |
| 0:25–1:40 | 75 min | `nfc-d4-read/` | 没贴卡时不显示假文本 |
| 1:40–2:20 | 40 min | 写 NFC-001,4，默答 NFC6 | 不看卡 |
| 2:20–3:00 | 40 min | 有标签时只读第一条记录 | 今天不写卡 |

**D4 验收**：`[ ]` 读到的是去掉语言码后的文本，或明确没贴到 `[ ]` 卡已落盘

### D5 · 前台拦截（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:30 | 30 min | 读 Advanced NFC 的 foreground dispatch | 能说出 onResume / onPause |
| 0:30–1:40 | 70 min | `nfc-d5-foreground/` | 离开页面会 disable |
| 1:40–2:20 | 40 min | 写 NFC-001,5，默答 NFC7 | 不看卡 |
| 2:20–3:00 | 40 min | 对照博客里的 enableReaderMode | 这个 Demo 不调用它 |

**D5 验收**：`[ ]` 只实现官网这一套 `[ ]` 卡已落盘

### D6 · 写一条 NDEF（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 读 NdefFormatable.format 的阻塞说明 | 知道不能在主线程写 |
| 0:25–1:40 | 75 min | `nfc-d6-write/` | 没贴卡时停在提示句 |
| 1:40–2:20 | 40 min | 写 NFC-001,6，默答 NFC8 | 不看卡 |
| 2:20–3:00 | 40 min | 贴着标签时写一条文本再读回 | 不锁定成只读 |

**D6 验收**：`[ ]` 写和读回都在后台线程 `[ ]` 卡已落盘

### D7 · HCE 对照（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:30 | 30 min | 读 overview 的两种模式和 HCE 页开头 | 能说出手机当卡是另一种模式 |
| 0:30–1:10 | 40 min | `nfc-d7-hce/` | 工程里没有 HostApduService |
| 1:10–1:50 | 40 min | 写 NFC-001,7，默答 NFC9 | 不看卡 |
| 1:50–3:00 | 70 min | 看 131 票和 42 票的问题 | 口头说明为什么不做门禁复制 |

**D7 验收**：`[ ]` 未注册 HCE 服务 `[ ]` 卡已落盘

### D8 · 默答（3 h）

遮住答案，口头过 NFC1–NFC9。答歪的原句写入错题库。

**D8 验收**：`[ ]` 开口过 `[ ]` 答歪的已记下

### D9 · 复跑（3 h）

错题库有题就复跑相关 Demo；仍空则复跑 `nfc-d6-write`。再默答选做 NFC10。

**D9 验收**：`[ ]` 复跑 1 个 Demo `[ ]` NFC10 开口过
