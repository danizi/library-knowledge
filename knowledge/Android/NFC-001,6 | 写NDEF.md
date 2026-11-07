# 写NDEF

**问：**
空白标签和已经是 NDEF 的标签，写入时差在哪？

**答：**
从 Intent 取出 `Tag`。`Ndef.get` 不是 null，才是已经格式化成 NDEF 的标签，可以按 NDEF 去写。返回 null 时，如果 `NdefFormatable.get` 不是 null，用 `format` 写成第一条消息。`format` 会阻塞，某一步失败就抛 `IOException`，不能在主线程调用。没拿到 Tag，就停在「还没贴到标签」，不要造一条假记录。

一句话：已是 NDEF 就写消息，还不是就 format；两步都在后台线程，没贴到就停。

**例 / 类比：**
- 例子：nfc-d6-write 贴着标签时写一条文本，再读回来；没贴到就只显示提示句。
- 类比：本子已经有横线就直接写；空白塑料片要先压出格式，再写下第一句。

**易错：**
- 在主线程调用 `format`。界面会卡住。
- 写完就调用 `makeReadOnly`。那是单向的，这张卡的 Demo 不锁卡。

**相关：**
- [NFC-001,4 | 读NDEF](<./NFC-001,4 | 读NDEF.md>) — 写完要按文本记录的布局再读
- [NFC-001,5 | 前台分发](<./NFC-001,5 | 前台分发.md>) — Tag 从当次 Intent 来

**参考：**
- [NdefFormatable](https://developer.android.com/reference/android/nfc/tech/NdefFormatable) — format 阻塞且不能在主线程
- [Advanced NFC](https://developer.android.com/develop/connectivity/nfc/advanced-nfc) — 从 Intent 取 Tag，再读写 NDEF
