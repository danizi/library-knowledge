# Cursor Canvas是什么

**问：**
Cursor Canvas 是什么？它和一篇 Markdown 差在哪？

**答：**
Canvas 是聊天旁边打开的一块实时页面，源文件是 `.canvas.tsx`。适合要反复点、对照、切换的说明（步骤、好坏例子、准则）。Markdown 适合来回改的正文。仓库里可以存源码，但在编辑器里打开源码，看到的是代码，不是那块页面。

一句话：Canvas 是能点的说明页，Markdown 是正文；打开源码不等于打开预览。

**例 / 类比：**
- 例子：费曼学习法的图可以在「四步 / 对照 / 准则」之间切换，正文仍是 `.cursor/docs/费曼学习法.md`。
- 类比：菜谱是 Markdown，把步骤摊成能翻页的展板是 Canvas。

**易错：**
- 在 `.cursor/docs/` 里点开 `.canvas.tsx`，以为会渲染出页面。
- 用 Canvas 代替准则正文，结果两份文章以后对不齐。正文留 Markdown，图只是摊开展示。

**相关：**
- [TL-004,1 | cursor怎么打开Canvas预览](<./TL-004,1 | cursor怎么打开Canvas预览.md>) — 预览文件不在仓库目录里
- [费曼学习法](../../.cursor/docs/费曼学习法.md) — 这套库里第一张配套图的正文

**参考：**
- 预览实例：[费曼学习法图](/Users/a1234/.cursor/projects/Users-a1234-xiaomin-library-knowledge/canvases/feynman-method.canvas.tsx)
