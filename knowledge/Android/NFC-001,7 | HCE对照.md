# HCE对照

**问：**
读写一张被动标签，和让手机自己被读卡器当成一张卡，是同一种模式吗？

**答：**
不是。官方把 NFC 分成读写模式和卡模拟。读写模式碰的是被动标签。卡模拟是手机被外面的读卡器访问，HCE 用的是服务，可以不靠安全元件。这个库的实战停在读写 NDEF。nfc-d7-hce 不注册 `HostApduService`，也不做支付或门禁报文。

一句话：手机当卡是另一种模式；本路线只对照，不实现 HCE。

**例 / 类比：**
- 例子：nfc-d7-hce 的界面只说明没有注册卡模拟服务。
- 类比：读别人的工牌，和把自己变成一张工牌，方向相反。

**易错：**
- 用 NDEF 读写去回答「手机能不能代替门禁卡」。那是卡模拟问题。
- 以为所有手机都能模拟 MIFARE。Advanced NFC 把 MIFARE 列为可选技术。

**相关：**
- [NFC-001 | NFC](<./NFC-001 | NFC.md>) — 先有适配器，才谈模式
- [NFC-001,4 | 读NDEF](<./NFC-001,4 | 读NDEF.md>) — 本路线真正做的是读标签

**参考：**
- [NFC overview](https://developer.android.com/develop/connectivity/nfc) — 读写模式和卡模拟
- [Host-based card emulation](https://developer.android.com/develop/connectivity/nfc/hce) — HCE 用服务对读卡器说话
- [Advanced NFC](https://developer.android.com/develop/connectivity/nfc/advanced-nfc) — MIFARE 不是每台设备都有
