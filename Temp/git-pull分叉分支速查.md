# Git Pull 分叉分支速查

## 1. 场景与报错
当本地分支和远程分支都有新提交（分叉）时，执行 `git pull` 可能出现：

```text
hint: You have divergent branches and need to specify how to reconcile them.
```

含义：Git 需要你明确本次拉取采用 `rebase` 还是 `merge`。

## 2. 现场保护（本地有改动先做这一步）
先看状态：

```bash
git status -sb
```

二选一保护现场：

```bash
# 方案 A：先提交
git add -A
git commit -m "wip: before pull"
```

```bash
# 方案 B：先暂存
git stash push -m "before pull"
```

## 3. 两种拉取方案（并列）
### 方案 A：Rebase（线性历史）
适用：本地提交未共享，想保持提交历史更整洁。

```bash
git pull --rebase
```

### 方案 B：Merge（保留合并点）
适用：不想改写本地提交历史，或团队更偏向合并记录。

```bash
git pull --no-rebase
```

## 4. 冲突处理（通用）
处理冲突文件后继续：

```bash
git add -A
```

Rebase 流程继续/放弃：

```bash
git rebase --continue
# 放弃本次 rebase
git rebase --abort
```

Merge 流程完成/放弃：

```bash
git commit -m "merge: resolve conflicts"
# 放弃本次 merge
git merge --abort
```

如果第 2 步使用了 `stash`，完成后恢复：

```bash
git stash pop
```

## 5. 一次性配置与按次覆盖
仓库内默认使用 rebase：

```bash
git config pull.rebase true
```

仓库内默认使用 merge：

```bash
git config pull.rebase false
```

全局默认（所有仓库）：

```bash
git config --global pull.rebase true
# 或
git config --global pull.rebase false
```

按次覆盖默认策略：

```bash
git pull --rebase
git pull --no-rebase
git pull --ff-only
```

## 6. 复盘清单
- 当前分支是否正确：`git branch --show-current`
- 工作区是否干净：`git status -sb`
- 本地提交是否已推送：`git log --oneline --decorate -n 5`
- 远程是否同步：`git fetch --all --prune`
- 若曾 `stash`，是否已恢复：`git stash list`
