# 配置 VS Code Python

> 类型：过程卡 · 目标：**冷启动可复现**  
> 状态：**仍待审查**（本机实拍截图待补）  
> 用法：为什么要扩展和解释器，见 [TL-008 | VS Code 跑 Python](<./TL-008 | VS Code 跑 Python.md>)  
> Cursor 与 VS Code 步骤相同：扩展市场、命令面板、右上角 ▶。  
> **配图约定：只用本机 VS Code 实拍。不使用生成图。** 图放 `./assets/TL-008/`。

## 元信息

| 项 | 填写 |
|----|------|
| 结果定义 | 打开任意 `.py`，点 ▶，终端出现脚本打印；状态栏能看到 Python 版本 |
| 耗时预估 | 10–20 分钟 |
| 环境前提 | macOS；已安装 VS Code 或 Cursor；终端能开 |
| 关联概念卡 | TL-008 |
| 产物路径 | 练习：`knowledge/Python/demos/py-d0-hello/`；截图：`./assets/TL-008/` |

---

<details open>
  <summary>1. 准备</summary>

  - [ ] 本机已有 `python3`
  - [ ] 工作目录：任意；验收时用 `knowledge/Python/demos/py-d0-hello/`
  - 验证命令：

    ```bash
    python3 --version
    which python3
    ```

  - 预期：打印 `Python 3.x.x`。没有版本号就先安装：https://www.python.org/downloads/macos/ 或 `brew install python@3.12`。
  - 说明：`python` 命令找不到时不要慌。后面一律用 `python3`。

</details>

<details open>
  <summary>2. 步骤（按顺序执行）</summary>

  ### 步骤 1 · 安装 Python 扩展

  - 操作：打开扩展面板。搜索 `Python`。安装发布者为 **Microsoft** 的扩展。建议同时安装 Pylance。
  - 快捷键：`Cmd+Shift+X`
  - 预期现象：扩展列表里 Python 显示已启用（或按钮变成 Disable / Uninstall）。
  - 实拍：`./assets/TL-008/tl008-step1-extension.png`（待补）

  <!-- 实拍就位后取消下一行注释
  ![扩展市场中的 Microsoft Python](./assets/TL-008/tl008-step1-extension.png)
  -->

  ### 步骤 2 · 选择解释器

  - 操作：打开命令面板，运行 `Python: Select Interpreter`。选一个本机真实路径。
  - 快捷键：`Cmd+Shift+P`，输入 `Python: Select Interpreter`
  - 优先顺序：
    1. Homebrew：`/opt/homebrew/bin/python3.12`（有则优先）
    2. 系统：`/usr/bin/python3`
  - 预期现象：窗口底部状态栏出现 `Python 3.x.x`。
  - 实拍：`./assets/TL-008/tl008-step2-interpreter.png`（待补）

  <!-- 实拍就位后取消下一行注释
  ![命令面板选择解释器](./assets/TL-008/tl008-step2-interpreter.png)
  -->

  ### 步骤 3 · 用 ▶ 跑通第一个脚本

  - 操作：用「打开文件夹」打开仓库根目录或 Demo 目录。打开 `hello.py`。点右上角 ▶（Run Python File）。
  - 路径示例：`knowledge/Python/demos/py-d0-hello/hello.py`
  - 预期现象：
    - 下方终端先出现一行很长的命令（带绝对路径）。这是扩展自动拼的，**正常**。
    - 下一行出现 `hello python` 或脚本自己的打印。
    - 状态栏能看到 Python 版本。
  - 实拍：`./assets/TL-008/tl008-step3-run.png`（待补）

  <!-- 实拍就位后取消下一行注释
  ![点 ▶ 后终端长命令下方才是输出](./assets/TL-008/tl008-step3-run.png)
  -->

  ### 步骤 4 ·（可选）让终端先进入脚本目录

  - 操作：打开设置，搜索 `python.terminal.executeInFileDir`，勾选为 true。
  - 预期现象：再点 ▶ 时，扩展会先 `cd` 到脚本所在目录，再运行。相对路径读文件更不容易迷路。

  ### 步骤 5 ·（可选）固定默认解释器

  - 操作：在用户 `settings.json` 写入一行，避免每次重选。

    ```json
    "python.defaultInterpreterPath": "/opt/homebrew/bin/python3.12"
    ```

  - 没有 3.12 时改成步骤 2 里你选中的那个路径。
  - 预期现象：新开窗口状态栏仍是同一版本。

</details>

<details open>
  <summary>3. 验收清单</summary>

  - [ ] `python3 --version` 有输出
  - [ ] 扩展 Python（Microsoft）已启用
  - [ ] 状态栏能看到 Python 版本
  - [ ] `py-d0-hello` 点 ▶ 能打印一行字
  - [ ] 能向别人说明：终端里超长命令是扩展启动方式，不是配错了
  - [ ] `./assets/TL-008/` 下三张实拍 png 已就位

</details>

<details>
  <summary>4. 常见卡点</summary>

  | 现象 | 先查 |
  |------|------|
  | 没有 ▶ 或不识别 `.py` | 扩展没装，或装成了别的同名扩展 |
  | 提示 Select Interpreter | 步骤 2 未完成 |
  | `command not found: python` | 改用 `python3`，或给 shell 加 alias |
  | 点 ▶ 没输出 | 看终端面板是否切到最新那一个；右侧可能叠了多个终端 |
  | 跑旧路径报错 | Demo 已迁到 `knowledge/Python/demos/`，不要再用 `knowledge/Tools/demos/python/` |

</details>

<details>
  <summary>5. 本机实拍（给代理或自己）</summary>

  代理侧截屏需要 macOS **屏幕录制**权限；点按 VS Code 还需要 **辅助功能**权限。未授权时不要用生成图顶替。

  你也可以在本机终端跑引导脚本（每步调好画面后回车截屏）：

  ```bash
  zsh knowledge/Tools/assets/TL-008/capture-real.sh
  ```

</details>
