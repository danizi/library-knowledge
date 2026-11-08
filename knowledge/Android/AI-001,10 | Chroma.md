# Chroma

**问：** Chroma 比数重合的字多做了哪一步？

> **一句话：** Chroma 把每篇笔记收进集合。查询时按接近程度取出一篇。

**例：** `ai-d7-chroma` 查询「冷启动是什么」。取出的文件名是 `cold.md`。目录里出现 `chroma_data`。

<details>
<summary>答</summary>

程序读取 `docs` 里的每篇 Markdown。
程序把文件名当作编号。程序把正文当作文档。
这些文档进入名为 `notes` 的集合。
集合写在目录 `chroma_data`。程序结束后集合还在。
查询时，Chroma 比较问题和下一条文档的接近程度。
它返回最接近的一篇。这一篇的编号是 `cold.md`。
D6 的查找发生在一次运行的内存里。Chroma 把文档留在磁盘上的集合里。

</details>

<details>
<summary>易错</summary>

- 不要把内存列表里的查找说成 Chroma。目录里要有 `chroma_data`。

</details>

<details>
<summary>相关</summary>

- [AI-001,2 | 检索](<./AI-001,2 | 检索.md>) — 找不到仍要回答「不知道」
- [AI练习册](./AI练习册.md) — D7

</details>

<details>
<summary>参考</summary>

- [慧灵科技启事](https://www.zhaopin.com/jobdetail/CC409009630J40849644801.htm) — 启事把 Chroma 写成向量库

</details>
