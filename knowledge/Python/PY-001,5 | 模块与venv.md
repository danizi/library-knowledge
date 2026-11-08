# 模块与venv

**问：** Python 的模块和虚拟环境各管什么？

> **一句话：** 一个 `.py` 文件是一个模块。venv 给项目单独装依赖。

**例：** `from util import score` 引入同目录函数。项目根执行 `python3 -m venv .venv` 后激活再 `pip install`。

<details>
<summary>答</summary>

`import x` 加载模块。
`from x import y` 导入名字。
直接运行文件时 `__name__` 为 `"__main__"`。
入口习惯写 `if __name__ == "__main__":`。
venv 隔离不同项目的包版本。
依赖可写在 `requirements.txt`。

</details>

<details>
<summary>易错</summary>

- 不要把所有包装进系统 Python。
- 不要在错误的工作目录里找模块。

</details>

<details>
<summary>相关</summary>

- [PY-001,6 | 标准库入门](<./PY-001,6 | 标准库入门.md>)
- [PY-001 | Python](<./PY-001 | Python.md>)

</details>

<details>
<summary>参考</summary>

- [模块](https://docs.python.org/zh-cn/3/tutorial/modules.html)
- [venv](https://docs.python.org/zh-cn/3/library/venv.html)

</details>
