# HCE对照

**问：** 读写被动标签，和让手机被外部读卡器当作一张卡，是同一种模式吗？

> **一句话：** 卡模拟是另一种模式。本路线不实现 HCE。

**例：** `nfc-d7-hce` 的界面只说明本应用没有注册卡模拟服务。

<details>
<summary>答</summary>

不是同一种模式。
读写模式访问被动标签。
卡模拟模式让手机充当一张卡。外部读卡器访问这张卡。
HCE 用服务实现卡模拟。HCE 可以不使用安全元件。
本路线只读写 NDEF 数据。
`nfc-d7-hce` 不注册 `HostApduService`。
`nfc-d7-hce` 不处理支付数据。
`nfc-d7-hce` 不处理门禁报文。

</details>

<details>
<summary>类比</summary>

读取另一张工牌，和把自己变成一张工牌，方向相反。

</details>

<details>
<summary>易错</summary>

- 不要用 NDEF 读写说明「手机能否代替门禁卡」。那是卡模拟。
- 不要认为每台设备都能模拟 MIFARE。Advanced NFC 把 MIFARE 列为可选技术。

</details>

<details>
<summary>相关</summary>

- [NFC-001 | NFC](<./NFC-001 | NFC.md>) — 先确认适配器，再选择模式
- [NFC-001,4 | 读NDEF](<./NFC-001,4 | 读NDEF.md>) — 本路线执行的是读取标签

</details>

<details>
<summary>参考</summary>

- [NFC overview](https://developer.android.com/develop/connectivity/nfc) — 读写模式和卡模拟模式
- [Host-based card emulation](https://developer.android.com/develop/connectivity/nfc/hce) — HCE 用服务和读卡器通信
- [Advanced NFC](https://developer.android.com/develop/connectivity/nfc/advanced-nfc) — MIFARE 不是每台设备都支持

</details>
