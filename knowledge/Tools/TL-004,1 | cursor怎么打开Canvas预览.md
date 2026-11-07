# cursor怎么打开Canvas预览

**问：**
自己怎么打开一张 Canvas 的预览，而不是一屏代码？

**答：**
预览只认 Cursor 的画布目录，不认仓库里的副本。本仓库的预览文件在：

`~/.cursor/projects/Users-a1234-xiaomin-library-knowledge/canvases/`

打开：按 `Cmd+P`，粘贴该目录下的 `名字.canvas.tsx` 绝对路径，回车。也可以点聊天里给出的这条绝对路径。打开后应是可切换的页面。满屏 `import` 就是开错了，开成了仓库源码。

一句话：`Cmd+P` 打开 canvases 目录里的那份；`.cursor/docs/` 里的是源码。

**例 / 类比：**
- 例子：源码 `.cursor/docs/feynman-method.canvas.tsx`；预览是上面 canvases 目录里的同名文件。改源码不会自动改预览，要再同步过去。
- 类比：剧本在书架上，演出在剧场。翻剧本看不到灯光。

**易错：**
- 只打开仓库里的 `.canvas.tsx`。
- 用符号链接指到仓库，预览有时不编译。画布目录里要是一份真实文件。

**相关：**
- [TL-004 | Cursor Canvas是什么](<./TL-004 | Cursor Canvas是什么.md>) — 先分清正文和图

**参考：**
- [费曼学习法预览](/Users/a1234/.cursor/projects/Users-a1234-xiaomin-library-knowledge/canvases/feynman-method.canvas.tsx)
