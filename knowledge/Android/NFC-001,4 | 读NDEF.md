# 读NDEF

**问：** 收到 `ACTION_NDEF_DISCOVERED` 之后，为什么不能把文本记录的载荷整段当作字符串？

> **一句话：** 从 `EXTRA_NDEF_MESSAGES` 取消息。读取文本记录时，跳过状态字节和语言码。

**例：** `nfc-d4-read` 没有标签时显示「还没贴到标签」。有文本记录时，去掉语言码后显示正文。

<details>
<summary>答</summary>

`ACTION_NDEF_DISCOVERED` 包含 `EXTRA_NDEF_MESSAGES`。
一条 NDEF 消息可以包含多条 NDEF 记录。
第一条 NDEF 记录决定如何读取整条 NDEF 消息。
文本记录的 TNF 是 `TNF_WELL_KNOWN`。文本记录的类型是 `RTD_TEXT`。
载荷的第 0 字节是状态字节。
状态字节之后是语言码。
语言码之后是正文。
把整段载荷当作字符串。语言码会进入正文。

</details>

<details>
<summary>类比</summary>

信封上先写了「en」。信纸上的句子才是正文。

</details>

<details>
<summary>易错</summary>

- 不要把第一条 NDEF 记录当作整条 NDEF 消息。一条消息可以有多条记录。
- 没有标签时，不要编写一条 NDEF 记录来代替读取结果。

</details>

<details>
<summary>相关</summary>

- [NFC-001,2 | 三种Action](<./NFC-001,2 | 三种Action.md>) — 只有 `ACTION_NDEF_DISCOVERED` 保证带有这个 extra
- [NFC-001,6 | 写NDEF](<./NFC-001,6 | 写NDEF.md>) — 写入是另一步

</details>

<details>
<summary>参考</summary>

- [NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc) — `EXTRA_NDEF_MESSAGES` 和 `RTD_TEXT` 载荷

</details>
