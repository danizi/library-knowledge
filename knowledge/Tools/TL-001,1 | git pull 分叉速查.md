# git pull 分叉速查

**问：**
`git pull` 提示 divergent branches、要选 rebase 还是 merge——该怎么处理？

**答：**
说明本地和远程都有对方没有的提交，Git 要你选定合并策略。  
先保护现场（提交或 `stash`），再选：想线性历史用 `git pull --rebase`；想保留合并点用 `git pull --no-rebase`。冲突解决后 rebase 用 `git rebase --continue`，merge 则直接再提交。

一句话：先护住本地改动，再显式选 rebase 或 merge 拉完。

**例 / 类比：**
```bash
git status -sb
git stash push -m "before pull"   # 或先 commit
git pull --rebase                 # 或 --no-rebase
```
- 类比：两条岔路汇合——选「改道接上主路」(rebase) 还是「修立交桥」(merge)。

**易错：**
- 有未提交改动硬 pull。
- rebase 到一半冲突慌着 `rebase --abort` 却不知道自己想要啥。

**相关：**
- [TL-001 | VS Code Git](./TL-001%20%7C%20VS%20Code%20Git.md)

**参考：**
- [git pull 文档](https://git-scm.com/docs/git-pull)
