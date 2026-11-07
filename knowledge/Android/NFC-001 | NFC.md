# NFC

**问：** `getDefaultAdapter()` 返回 null，和 `isEnabled()` 返回 false，有什么差别？

> **一句话：** null 表示没有 NFC 硬件。false 表示有 NFC 硬件，但 NFC 功能未打开。

**例：** `nfc-d1-adapter` 只显示这两项结果。这个 Demo 没有「打开 NFC」按钮。

<details>
<summary>答</summary>

`getDefaultAdapter()` 返回 null。设备没有 NFC 硬件。
`getDefaultAdapter()` 返回一个适配器。设备有 NFC 硬件。
适配器的 `isEnabled()` 返回 true。NFC 功能已打开。
适配器的 `isEnabled()` 返回 false。NFC 功能未打开。
应用不能用代码打开 NFC 功能。用户在系统设置里打开 NFC 功能。

</details>

<details>
<summary>类比</summary>

没有门，和门在但锁着，是两件不同的事。

</details>

<details>
<summary>易错</summary>

- 不要把模拟器上的 null 说成「用户关闭了 NFC」。模拟器通常没有 NFC 硬件。
- 不要发送一个意图去打开 NFC。NFC 基础文档没有这个步骤。

</details>

<details>
<summary>相关</summary>

- [NFC-001,1 | 清单声明](<./NFC-001,1 | 清单声明.md>) — 使用适配器之前，先在清单里声明
- [NFC-001,7 | HCE对照](<./NFC-001,7 | HCE对照.md>) — 卡模拟是另一种模式

</details>

<details>
<summary>参考</summary>

- [NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc) — `getDefaultAdapter()` 为 null
- [NfcAdapter.isEnabled](https://developer.android.com/reference/android/nfc/NfcAdapter#isEnabled()) — NFC 功能是否打开

</details>
