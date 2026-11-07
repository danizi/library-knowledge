# git pull 分叉速查

**问：** `git pull` 提示 divergent branches 时，先做什么？

> **一句话：** 先保住本地改动。再明确选择 `rebase` 或 `merge`，然后拉取。

**例：** 先看状态。有未提交的改动时先 stash，或者先 commit。然后选择 `git pull --rebase` 或 `git pull --no-rebase`。

```bash
git status -sb
git stash push -m "before pull"
git pull --rebase
```

<details>
<summary>答</summary>

这个提示表示本地和远程都有对方没有的提交。
Git 要求你选定合并策略。
先保护现场。可以先提交，也可以先 `stash`。
想要线性历史时，使用 `git pull --rebase`。
想保留合并点时，使用 `git pull --no-rebase`。
`rebase` 遇到冲突并解决之后，执行 `git rebase --continue`。
`merge` 遇到冲突并解决之后，再做一次提交。

</details>

<details>
<summary>类比</summary>

两条路汇合时，rebase 是改道接上主路。merge 是修一座立交桥。

</details>

<details>
<summary>易错</summary>

- 不要在还有未提交改动时直接 pull。
- `rebase` 停在冲突中时，先决定要继续还是中止。执行 `git rebase --abort` 之前，先确认自己要放弃这次 rebase。

</details>

<details>
<summary>相关</summary>

- [TL-001 | VS Code Git](<./TL-001 | VS Code Git.md>)

</details>

<details>
<summary>参考</summary>

- [git pull 文档](https://git-scm.com/docs/git-pull)

</details>
