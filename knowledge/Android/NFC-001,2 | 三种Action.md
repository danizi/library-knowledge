# 三种 Action

**问：** 系统把 NFC 标签交给应用时，按什么顺序使用这三种 Action？

> **一句话：** 先用 `ACTION_NDEF_DISCOVERED`。然后用 `ACTION_TECH_DISCOVERED`。最后用 `ACTION_TAG_DISCOVERED`。

**例：** `nfc-d2-dispatch` 没有标签时显示「还没贴到标签」。有标签时只显示本次 Action。这个 Demo 不读取记录正文。

<details>
<summary>答</summary>

标签包含系统能识别的 NDEF 数据。系统先发送 `ACTION_NDEF_DISCOVERED`。
没有应用接收这个 Action。系统发送 `ACTION_TECH_DISCOVERED`。
NDEF 数据不能映射为 MIME 类型或 URI。系统发送 `ACTION_TECH_DISCOVERED`。
标签不包含 NDEF 数据。系统发送 `ACTION_TECH_DISCOVERED`。
没有应用接收前两种 Action。系统发送 `ACTION_TAG_DISCOVERED`。
优先使用 `ACTION_NDEF_DISCOVERED`。这个 Action 最具体。

</details>

<details>
<summary>类比</summary>

先找对口科室。没有对口科室，再找专科门诊。最后才到大厅问询。

</details>

<details>
<summary>易错</summary>

- 不要把 `ACTION_TAG_DISCOVERED` 当作第一选择。它是最后使用的 Action。
- 不要把过滤器写得过宽。过滤器和另一个应用同样匹配时，系统显示选择框。

</details>

<details>
<summary>相关</summary>

- [NFC-001,3 | 网址标签](<./NFC-001,3 | 网址标签.md>) — http 和 https 标签从 Android 16 起使用另一条路径
- [NFC-001,4 | 读NDEF](<./NFC-001,4 | 读NDEF.md>) — 确认 Action 之后再读记录

</details>

<details>
<summary>参考</summary>

- [NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc) — 三种 Action 的顺序

</details>
