# VS Code 跑 Python

**问：** 在 VS Code 里点 ▶ 能跑 `.py`，最少要备齐哪两样？

> **一句话：** 本机要有 `python3`。编辑器要装 Python 扩展并选中解释器。

**例：** 终端执行 `python3 --version` 出现 `Python 3.`。扩展市场安装 Microsoft 的 Python。用命令面板选中 `/opt/homebrew/bin/python3.12` 或 `/usr/bin/python3`。打开 `hello.py` 点 ▶，终端打印脚本输出。

> 配图：只用本机 VS Code 实拍。见过程卡 [TL-008,1](<./TL-008,1 | 配置 VS Code Python.md>)（截图待补）。

<details>
<summary>答</summary>

VS Code 自己不会执行 Python。
本机解释器负责跑代码。
Python 扩展负责找解释器、提供 ▶ 按钮和语法提示。
选中的解释器路径会出现在状态栏。
点 ▶ 时，扩展会拼出一条带绝对路径的命令。
那一行很长是正常现象。新手看命令下面的打印结果即可。
macOS 上常用命令是 `python3`，不一定有 `python`。

</details>

<details>
<summary>类比</summary>

本机 Python 是发动机。扩展是仪表盘上的启动按钮。没选解释器就像没挂上发动机。

</details>

<details>
<summary>易错</summary>

- 不要只装扩展却不选解释器。状态栏应能看到 Python 版本。
- 不要因为终端里出现很长的绝对路径就以为配置失败。
- 不要在终端里坚持打 `python`。本机没有该命令时改打 `python3`。

</details>

<details>
<summary>相关</summary>

- [TL-008,1 | 配置 VS Code Python](<./TL-008,1 | 配置 VS Code Python.md>) — 冷启动步骤（含本机截图）
- [PY-001 | Python](<../Python/PY-001 | Python.md>) — 语言入门总览
- [PY-001,5 | 模块与venv](<../Python/PY-001,5 | 模块与venv.md>) — 项目虚拟环境

</details>

<details>
<summary>参考</summary>

- [Python in Visual Studio Code](https://code.visualstudio.com/docs/languages/python) — 官方入门
- [Select a Python interpreter](https://code.visualstudio.com/docs/python/environments#_select-and-activate-an-environment) — 选解释器

</details>
