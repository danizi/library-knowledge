# 三种 Action

**问：**
系统把一张 NFC 标签交给应用时，三种 Action 按什么顺序试？

**答：**
能识别类型的 NDEF，先发 `ACTION_NDEF_DISCOVERED`。没有应用接，或者 NDEF 映射不出 MIME 类型或 URI，或者标签里根本没有 NDEF，就发 `ACTION_TECH_DISCOVERED`。这两种都没人处理，才发 `ACTION_TAG_DISCOVERED`。只要可能，就用 NDEF 这一种，它最具体。

一句话：NDEF 优先，技术类型其次，TAG 是最后的兜底。

**例 / 类比：**
- 例子：nfc-d2-dispatch 没贴到标签时只显示「还没贴到标签」；贴到了只显示这次的 Action，不解析正文。
- 类比：先找对口科室，没有再找专科门诊，最后才是大厅问询。

**易错：**
- 把 `ACTION_TAG_DISCOVERED` 当成最优先。它是最后才用的。
- 过滤写得太宽，和别的应用打成平手，系统就弹出选择框。

**相关：**
- [NFC-001,3 | 网址标签](<./NFC-001,3 | 网址标签.md>) — http/https 从 Android 16 起改走另一条
- [NFC-001,4 | 读NDEF](<./NFC-001,4 | 读NDEF.md>) — 确认是 NDEF 之后才读记录

**参考：**
- [NFC basics](https://developer.android.com/develop/connectivity/nfc/nfc) — 三种 intent 的优先级
