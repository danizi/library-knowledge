# 读NDEF

**问：**
`ACTION_NDEF_DISCOVERED` 进来之后，文本记录的载荷为什么不能整段当成字符串？

**答：**
这个 Action 上一定带 `EXTRA_NDEF_MESSAGES`。一条消息里可以有多条记录，第一条记录决定怎么解读整条消息。文本记录是 `TNF_WELL_KNOWN` 加 `RTD_TEXT`：载荷第 0 字节是状态，后面先是语言码，然后才是正文。把整段 payload 当文本，语言码会混进正文。

一句话：先拿 EXTRA_NDEF_MESSAGES，文本记录要跳过状态字节和语言码。

**例 / 类比：**
- 例子：nfc-d4-read 没贴到标签时只显示「还没贴到标签」；贴到文本记录时去掉语言码再显示。
- 类比：信封上先写了「en」，信纸才是你要读的那句。

**易错：**
- 以为第一条记录就是全部内容。一条消息可以有多条记录。
- 没贴到标签时自己造一条 NDEF 来假装读成功。

**相关：**
- [NFC-001,2 | 三种Action](<./NFC-001,2 | 三种Action.md>) — 只有 NDEF 这一路才保证有这份 extra
- [NFC-001,6 | 写NDEF](<./NFC-001,6 | 写NDEF.md>) — 写回去是另一天

**参考：**
- [NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc) — EXTRA_NDEF_MESSAGES 与 RTD_TEXT 的载荷布局
