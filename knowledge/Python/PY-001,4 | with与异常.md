# with与异常

**问：** Python 里打开文件和抛错，分别用什么结构？

> **一句话：** 文件用 `with open(...) as f:`。错误用 `try` / `except` / `raise`。

**例：** `with Path("a.txt").open() as f:` 读全文。找不到文件时 `except FileNotFoundError as e:`。

<details>
<summary>答</summary>

`with` 在离开块时关闭资源。
这与 Kotlin 的 `use` 同类。
`except` 接住指定异常类型。
`raise` 抛出异常。
自定义异常继承 `Exception`。

</details>

<details>
<summary>易错</summary>

- 不要打开文件后只写 `close` 而不用 `with`。
- 不要裸写 `except:` 吞掉所有错误。

</details>

<details>
<summary>相关</summary>

- [PY-001,3 | 类与dataclass](<./PY-001,3 | 类与dataclass.md>)
- [PY-001,6 | 标准库入门](<./PY-001,6 | 标准库入门.md>)

</details>

<details>
<summary>参考</summary>

- [读文件](https://docs.python.org/zh-cn/3/tutorial/inputoutput.html#reading-and-writing-files)
- [异常](https://docs.python.org/zh-cn/3/tutorial/errors.html)

</details>
