# NFC 面试题库

> 只覆盖这条实战。题号 **NFC1–**。每题都有出处。  
> 配合 [NFC学习计划](NFC学习计划.md)。答歪的进 [NFC错题库](NFC错题库.md)，那一页现在是空的。

| 天 | 必刷 |
|----|------|
| D0 | NFC1 |
| D1 | NFC2 NFC3 |
| D2 | NFC4 |
| D3 | NFC5 |
| D4 | NFC6 |
| D5 | NFC7 |
| D6 | NFC8 |
| D7 | NFC9 |
| D8 | NFC1–NFC9 |
| D9 | NFC10（选做）+ [错题库](NFC错题库.md) |

## 必会

### NFC1

场景：安装包已经装上，扫描却像系统不把标签交给这个应用。清单里缺什么？

要说的那句：要声明 `android.permission.NFC`。希望 Play 只发给有 NFC 的设备时，再加 `android.hardware.nfc` 且 `required="true"`。功能可有可无时可以不写 uses-feature，运行时看适配器是不是 null。

参考：[NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc)

### NFC2

场景：模拟器上 `getDefaultAdapter()` 是 null，真机设置里关掉 NFC 也像不能用。是同一种情况吗？

要说的那句：null 表示没有 NFC 硬件。适配器对象还在时，再看 `isEnabled()`。true 才表示功能开着。

参考：[NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc)、[NfcAdapter.isEnabled](https://developer.android.com/reference/android/nfc/NfcAdapter#isEnabled())

### NFC3

场景：蓝牙可以用意图请用户打开无线电。NFC 想照做。

要说的那句：NFC basics 没有这种申请打开的流程。应用不要自己改系统里的 NFC 开关。

参考：[NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc)

### NFC4

场景：贴到一张标签，系统可能弹出好几个应用。这三种 Action 谁先？

要说的那句：能识别的 NDEF 优先 `ACTION_NDEF_DISCOVERED`。没有应用接，或映射不出 MIME / URI，或根本不是 NDEF，才是 `ACTION_TECH_DISCOVERED`。前两种都没人接，才是 `ACTION_TAG_DISCOVERED`。

参考：[NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc)

### NFC5

场景：标签里是一个 https 链接，Android 16 的手机没有走进你为 `ACTION_NDEF_DISCOVERED` 写的过滤器。

要说的那句：从 Android 16 起，http/https 标签改发 `ACTION_VIEW`。Android 17 起还会先出「打开链接」通知，用户点了才发这个 intent。

参考：[NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc)

### NFC6

场景：读到的文本开头多了 `en`。

要说的那句：`EXTRA_NDEF_MESSAGES` 在 `ACTION_NDEF_DISCOVERED` 上一定有。文本记录的载荷第 0 字节是状态，后面先放语言码，再才是正文。

参考：[NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc)

### NFC7

场景：应用在前台，贴卡却还是跳出别的应用。

要说的那句：`enableForegroundDispatch` 要在主线程、并且 Activity 处于前台时调用，官方示例放在 `onResume`。`onPause` 里 `disableForegroundDispatch`。标签从 `onNewIntent` 里的 `EXTRA_TAG` 拿。

参考：[Advanced NFC](https://developer.android.com/develop/connectivity/nfc/advanced-nfc)

### NFC8

场景：在界面线程里格式化一张空白标签，界面卡住。

要说的那句：`NdefFormatable.format` 是会阻塞的 I/O，不能在主线程调用。某一步失败就抛 `IOException`。已经是 NDEF 的标签才走 NDEF 读写；`Ndef.get` 为 null 表示这张还不是 NDEF。

参考：[NdefFormatable](https://developer.android.com/reference/android/nfc/tech/NdefFormatable)、[Advanced NFC](https://developer.android.com/develop/connectivity/nfc/advanced-nfc)

### NFC9

场景：门禁想用手机代替那张卡。

要说的那句：读写标签是一种模式。手机自己当卡是卡模拟，官方另有 HCE 文档。这个库不写 `HostApduService`，也不做支付。

参考：[NFC overview](https://developer.android.com/develop/connectivity/nfc)、[Host-based card emulation](https://developer.android.com/develop/connectivity/nfc/hce)

## 选做

### NFC10

场景：距离一米还想用手机读一张门禁卡。

要说的那句：NFC 通常要 4 厘米以内。这不是远距离 RFID。

参考：[NFC overview](https://developer.android.com/develop/connectivity/nfc)
