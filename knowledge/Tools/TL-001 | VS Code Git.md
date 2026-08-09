# VS Code Git

**问：**
在 VS Code 里，日常 Git 最小闭环怎么走？（不用背一堆命令）

**答：**
左侧 Source Control：看改动 → 暂存（+）→ 写说明提交 → 拉取/推送（状态栏箭头或命令面板）。  
分支点状态栏分支名切换；冲突在编辑器里选 Accept 再暂存提交。有本地改动又要切分支时可用 Stash。

一句话：看改动 → 暂存 → 提交 → 拉/推；冲突在文件里点选再提交。

**例 / 类比：**
1. `File → Open Folder` 打开仓库  
2. Source Control 里 Stage → 填写消息 → `Cmd/Ctrl+Enter`  
3. `Git: Pull` / `Git: Push`  
- 类比：快递柜——暂存=放进格口，提交=封箱出站，推送=装上车。

**易错：**
- 没配 `user.name` / `user.email` 导致提交被拒。
- 冲突没解决就狂点同步。

**相关：**
- [TL-001,1 | git pull 分叉速查](./TL-001,1%20%7C%20git%20pull%20分叉速查.md)

**参考：**
- [VS Code Git 文档](https://code.visualstudio.com/docs/sourcecontrol/overview)
