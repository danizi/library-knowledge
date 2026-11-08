#!/bin/zsh
# 本机真实截图：先给「终端」或运行本脚本的 App 打开「屏幕录制」权限。
# 用法：按提示操作 VS Code 后，回车抓当前屏。
set -e
DIR="$(cd "$(dirname "$0")" && pwd)"
CODE="/Applications/Visual Studio Code.app/Contents/Resources/app/bin/code"
ROOT="/Users/a1234/xiaomin/library-knowledge"

shot() {
  local name="$1"
  local msg="$2"
  echo ""
  echo ">>> $msg"
  echo "    调好 VS Code 画面后按回车截屏…"
  read -r _
  osascript -e 'tell application "Visual Studio Code" to activate' >/dev/null 2>&1 || true
  sleep 0.4
  screencapture -x "$DIR/$name"
  echo "    已保存 $DIR/$name"
}

open "vscode:extension/ms-python.python"
shot "tl008-step1-extension.png" "步骤1：扩展页应显示 Microsoft Python（已安装也可）"

"$CODE" "$ROOT/knowledge/Python/demos/py-d0-hello/hello.py"
echo "请在 VS Code 按 Cmd+Shift+P，输入 Python: Select Interpreter，让列表露出来。"
shot "tl008-step2-interpreter.png" "步骤2：解释器列表可见"

echo "请打开 hello.py，让右上角 ▶ 和底部终端可见（可先点一次 ▶）。"
shot "tl008-step3-run.png" "步骤3：编辑器 + ▶ + 终端输出"

echo "完成。把三张 png 留给卡片引用。"
ls -la "$DIR"/tl008-step*.png
