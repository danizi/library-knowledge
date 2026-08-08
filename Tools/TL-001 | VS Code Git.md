# VS Code Git 使用教程（图形界面为主）

面向已安装 Git 与 VS Code 的开发者，快速上手常见 Git 操作；以 VS Code Source Control 面板为主，附必要命令速查。快捷键 macOS 写 Cmd，Windows/Linux 写 Ctrl。

## 1. 简介与前置
- 确认 Git 可用：`git --version`（终端）
- 确认 VS Code 已启用 Git：左侧活动栏有「Source Control」图标（分支样式），没有则检查扩展是否禁用。
- 推荐先配置全局用户名邮箱（终端）：  
  ```bash
  git config --global user.name "Your Name"
  git config --global user.email "you@example.com"
  ```

## 2. 快速上手 5 步
1) 打开/克隆仓库  
   - 现有仓库：`File → Open Folder` 选中仓库目录。  
   - 新克隆：`Cmd/Ctrl+Shift+P` → 输入 `Git: Clone` → 粘贴仓库 URL → 选择本地路径。
2) 查看改动  
   - 点击左侧 Source Control，Changes 区可点文件查看行内 diff。
3) 暂存与提交  
   - 暂存单文件：文件旁 `+`；全部：面板顶部 `Stage All Changes`。  
   - 提交：在消息框写说明，`Cmd/Ctrl+Enter` 或点对勾。
4) 拉取/推送  
   - 状态栏上下箭头或命令面板：`Git: Pull` / `Git: Push`。
5) 切分支  
   - 状态栏分支名 → 选择 `Create new branch...` / `Checkout to...` 切换。

## 3. 详细操作指南
### 3.1 初始化 / 克隆
- 初始化空目录：终端 `git init`，或 Source Control 面板点击 `Initialize Repository`。  
- `.gitignore`：在仓库根新建/编辑，常用模板可在 `Cmd/Ctrl+Shift+P` → `Add gitignore`（需扩展）或手动写。

### 3.2 查看改动 & 暂存/提交
- Changes（未暂存） vs Staged Changes（已暂存）。  
- 暂存/撤销暂存：`+` / `-`；全部暂存/撤销用面板顶部按钮。  
- 提交：填写信息 → `Cmd/Ctrl+Enter`。如需签名/模板可在设置或 Git config 里调整。

### 3.3 分支与远程
- 新建/切换/删除：状态栏分支名 → 选择操作。  
- 设置上游：命令面板 `Git: Set Upstream`（或首次推送时选择远程分支）。  
- 拉取/推送：箭头按钮或 `Git: Pull` / `Git: Push`。  
- 查看图形分支：可安装 Git Graph 插件（可选）。

### 3.4 冲突解决
- 冲突文件在 Changes 中标红；打开文件，点击内联按钮：`Accept Current` / `Accept Incoming` / `Accept Both` / `Compare Changes`。  
- 解决后保存 → Stage 冲突文件 → 提交。

### 3.5 Stash（暂存工作区）
- 保存：`Cmd/Ctrl+Shift+P` → `Git: Stash`（可输入描述）。  
- 恢复：`Git: Stash Pop` / `Apply Latest Stash`。  
- 多 stash 管理：`Git: Stash...` 查看列表。

### 3.6 回滚与撤销
- 单文件丢弃改动：在 Changes 右键 `Discard Changes`（慎用）。  
- 选区回滚：在 diff 视图右键 `Restore Selection`。  
- 已暂存想撤销：右键 `Unstage`。  
- 单文件回到 HEAD：命令面板 `Git: Checkout HEAD^`（或终端 `git checkout -- <file>`）。

### 3.7 历史与 Blame
- 时间线视图：打开文件 → 右侧 Timeline 查看提交历史。  
- Blame/作者信息：安装 GitLens 可在行尾/侧边显示；内置可在文件标题区点时光机图标查看。

### 3.8 常用命令速查（内置终端执行）
```bash
git status
git add .                    # 暂存全部
git commit -m "message"
git pull
git push
git switch -c feature/x      # 新分支
git switch main              # 切换分支
git log --oneline --graph -10
git stash / git stash pop
```

## 4. 常见问题（现象→原因→解决）
- **Git not found / 未检测到 Git**：未安装或 PATH 缺失 → 安装 Git，重启 VS Code 或在设置里指定 Git 路径。  
- **推送被拒**：未设置上游 / 落后远程 / 保护分支 → 先 `Git: Pull` 或 `git pull --rebase`，确认分支权限，再 `Push`。  
- **反复冲突**：每次推前先拉；必要时用 `rebase` 保持线性（命令面板 `Git: Rebase`）。  
- **权限/凭证问题**：首选 SSH Key（`ssh-keygen` + 平台添加公钥）；HTTP 用 Git Credential Manager。  
- **提交了大文件**：用 `.gitignore` 排除，已提交的可用 `git rm --cached file`，需 LFS 时安装 Git LFS。  
- **想撤销误删/误改**：优先用 diff 视图的 `Restore Selection`，避免盲目 `Discard All`。

## 5. 快速复盘卡片（GUI + 命令）
1) 打开仓库 / `Git: Clone`  
2) 看改动（Source Control）  
3) 暂存 `+` / 全部 `Stage All`  
4) 提交消息 → `Cmd/Ctrl+Enter`  
5) 拉取 `Git: Pull` → 推送 `Git: Push`  
6) 分支：状态栏分支名 → `Create / Checkout`  
7) 冲突：Accept Current/Incoming → Stage → Commit  
8) Stash：`Git: Stash` / `Stash Pop`  
9) 回滚：`Discard`（慎用）或 `Restore Selection`  
10) 常用命令：`status` `add` `commit` `pull` `push` `switch` `log --oneline`

## 6. 附录
- 快捷键：  
  - 命令面板 `Cmd/Ctrl+Shift+P`  
  - Source Control 面板 `Cmd/Ctrl+Shift+G`  
  - 内置终端 `Ctrl+``  
- 推荐插件：GitLens（历史/作者/对比）、Git Graph（分支图）、GitHub Pull Requests and Issues（管理 PR/Issue）。


问题
国内访问github速度很慢处理
```
git config --global http.proxy http://127.0.0.1:7890
git config --global https.proxy http://127.0.0.1:7890

检查是否生效：
git config --global --get http.proxy
git config --global --get https.proxy
git ls-remote https://github.com  # 能列出 refs 说明已通

# 取消时：
git config --global --unset http.proxy
git config --global --unset https.proxy
```
