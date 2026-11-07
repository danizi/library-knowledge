# VS Code Git

**问：** 在 VS Code 里，一次日常 Git 操作按什么顺序做完？

> **一句话：** 先看改动。再暂存。再提交。然后拉取或推送。冲突在文件里选定之后再提交。

**例：** 用 `File → Open Folder` 打开仓库。在 Source Control 里暂存，填写消息，按 `Cmd+Enter` 或 `Ctrl+Enter` 提交。再用命令 `Git: Pull` 或 `Git: Push`。

<details>
<summary>答</summary>

左侧 Source Control 显示改动。
点 `+` 暂存。
写说明，然后提交。
用状态栏箭头或命令面板拉取或推送。
点状态栏上的分支名可以切换分支。
冲突在编辑器里选择 Accept。
然后再暂存并提交。
有本地改动、又要切换分支时，可以使用 Stash。

</details>

<details>
<summary>类比</summary>

暂存是把包裹放进格口。提交是封箱。推送是把箱子装上车。

</details>

<details>
<summary>易错</summary>

- 没有配置 `user.name` 和 `user.email` 时，提交会被拒绝。
- 冲突还没有解决时，不要反复点击同步。

</details>

<details>
<summary>相关</summary>

- [TL-001,1 | git pull 分叉速查](<./TL-001,1 | git pull 分叉速查.md>)

</details>

<details>
<summary>参考</summary>

- [VS Code Git 文档](https://code.visualstudio.com/docs/sourcecontrol/overview)

</details>
