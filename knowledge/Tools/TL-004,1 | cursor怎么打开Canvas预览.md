# cursor怎么打开Canvas预览

**问：** 怎样打开 Canvas 的预览，而不是打开一屏代码？

> **一句话：** 按 `Cmd+P`，打开 canvases 目录里的那份文件。`.cursor/docs/` 里的是源码。

**例：** 源码是 `.cursor/docs/feynman-method.canvas.tsx`。预览是 canvases 目录里的同名文件。修改源码不会自动修改预览。要再同步过去。

<details>
<summary>答</summary>

预览只认 Cursor 的画布目录。
预览不认仓库里的副本。
本仓库的预览文件在：

`~/.cursor/projects/Users-a1234-xiaomin-library-knowledge/canvases/`

按 `Cmd+P`。
粘贴该目录下 `名字.canvas.tsx` 的绝对路径。
按回车。
也可以点击聊天里给出的这条绝对路径。
打开后应是可以切换的页面。
满屏 `import` 表示开错了。开成了仓库源码。

</details>

<details>
<summary>类比</summary>

剧本在书架上。演出在剧场里。翻剧本看不到灯光。

</details>

<details>
<summary>易错</summary>

- 不要只打开仓库里的 `.canvas.tsx`。
- 不要用符号链接指到仓库。预览有时不编译。画布目录里要是一份真实文件。

</details>

<details>
<summary>相关</summary>

- [TL-004 | Cursor Canvas是什么](<./TL-004 | Cursor Canvas是什么.md>) — 先分清正文和图

</details>

<details>
<summary>参考</summary>

- [费曼学习法预览](/Users/a1234/.cursor/projects/Users-a1234-xiaomin-library-knowledge/canvases/feynman-method.canvas.tsx)

</details>
