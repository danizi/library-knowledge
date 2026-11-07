# NFC

**问：**
`getDefaultAdapter()` 返回 null，和适配器还在但 `isEnabled()` 为 false，差在哪？

**答：**
null 表示这台设备没有 NFC 硬件。对象还在时，`isEnabled()` 为 true 才表示功能开着。NFC 的基础文档没有蓝牙那种申请打开的弹窗，应用不要自己改系统开关。模拟器上经常是 null，贴卡要换真机。

一句话：null 是没有芯片，false 是有芯片但没开；开不开由系统设置决定。

**例 / 类比：**
- 例子：nfc-d1-adapter 只显示这两句判断，没有「打开 NFC」按钮。
- 类比：房子没有门，和门在但插销扣着，不是同一件事。

**易错：**
- 把模拟器上的 null 说成「用户关了 NFC」。
- 仿照蓝牙去发一个打开无线电的意图。基础文档没有这一步。

**相关：**
- [NFC-001,1 | 清单声明](<./NFC-001,1 | 清单声明.md>) — 有硬件之前，清单要先声明
- [NFC-001,7 | HCE对照](<./NFC-001,7 | HCE对照.md>) — 手机当卡是另一种模式

**参考：**
- [NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc) — getDefaultAdapter() 为 null
- [NfcAdapter.isEnabled](https://developer.android.com/reference/android/nfc/NfcAdapter#isEnabled()) — 功能是否打开
