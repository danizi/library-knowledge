# Cursor Canvas是什么

**问：** Cursor Canvas 和一篇 Markdown 差在哪里？

> **一句话：** Canvas 是可以点击和切换的说明页。Markdown 是正文。打开源码不等于打开预览。

**例：** 费曼学习法的图可以在「四步」「对照」「准则」之间切换。正文仍是 `.cursor/docs/费曼学习法.md`。

<details>
<summary>答</summary>

Canvas 是在聊天旁边打开的一块实时页面。
源文件是 `.canvas.tsx`。
它适合要反复点击、对照和切换的说明。
这些说明包括步骤、好坏例子和准则。
Markdown 适合来回修改的正文。
仓库里可以存放源码。
在编辑器里打开源码时，看到的是代码，不是那块页面。

</details>

<details>
<summary>类比</summary>

菜谱是 Markdown。把步骤摊成可以翻页的展板，是 Canvas。

</details>

<details>
<summary>易错</summary>

- 不要在 `.cursor/docs/` 里点开 `.canvas.tsx`，就认为它会渲染成页面。
- 不要用 Canvas 代替准则正文。两份文章以后会对不齐。正文留在 Markdown。图只负责摊开展示。

</details>

<details>
<summary>相关</summary>

- [TL-004,1 | cursor怎么打开Canvas预览](<./TL-004,1 | cursor怎么打开Canvas预览.md>) — 预览文件不在仓库目录里
- [费曼学习法](../../.cursor/docs/费曼学习法.md) — 这套库里第一张配套图的正文

</details>

<details>
<summary>参考</summary>

- 预览实例：[费曼学习法图](/Users/a1234/.cursor/projects/Users-a1234-xiaomin-library-knowledge/canvases/feynman-method.canvas.tsx)

</details>
