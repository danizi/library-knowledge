# 写NDEF

**问：** 空白标签和已有 NDEF 数据的标签，写入步骤有什么差别？

> **一句话：** 已有 NDEF 格式就写入消息。没有 NDEF 格式就调用 `format`。两种写入都在后台线程执行。没有标签就停止。

**例：** `nfc-d6-write` 在标签还在字段内时写入一条文本记录，然后读回该记录。没有标签时只显示提示句。

<details>
<summary>答</summary>

从 Intent 读取 `Tag`。
没有 `Tag`。停止。显示「还没贴到标签」。不要编写假的 NDEF 记录。
`Ndef.get` 不返回 null。这个标签已有 NDEF 格式。向这个标签写入 NDEF 消息。
`Ndef.get` 返回 null，并且 `NdefFormatable.get` 不返回 null。调用 `format`。`format` 写入第一条 NDEF 消息。
`format` 会阻塞当前线程。不要在主线程调用 `format`。
`format` 的任一步骤失败。方法抛出 `IOException`。

</details>

<details>
<summary>类比</summary>

纸上已有横线，就直接写。空白卡片要先做出格式，再写第一句。

</details>

<details>
<summary>易错</summary>

- 不要在主线程调用 `format`。界面会停止响应。
- 不要在写入后调用 `makeReadOnly`。这个操作不能撤销。这个 Demo 不锁定标签。

</details>

<details>
<summary>相关</summary>

- [NFC-001,4 | 读NDEF](<./NFC-001,4 | 读NDEF.md>) — 写完后按文本记录的布局再读
- [NFC-001,5 | 前台分发](<./NFC-001,5 | 前台分发.md>) — 从当次 Intent 读取 Tag

</details>

<details>
<summary>参考</summary>

- [NdefFormatable](https://developer.android.com/reference/android/nfc/tech/NdefFormatable) — `format` 阻塞，且不能在主线程调用
- [Advanced NFC](https://developer.android.com/develop/connectivity/nfc/advanced-nfc) — 从 Intent 读取 Tag，然后读写 NDEF

</details>
