# D7 Demo · 2026-09-05

工程：`knowledge/Android/demos/fragment/fg-d7-fragment-args/`

`DetailFragment.newInstance("book-42")`：空构造 + `arguments`。打开 Detail 后旋转：实例 hash 变了，`onCreate restored=true`，`arguments id` 仍是 `book-42`。
