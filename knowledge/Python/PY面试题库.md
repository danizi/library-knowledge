# Python 面试题库（有 Java/Kotlin）

按 [PY学习计划](PY学习计划.md) 分段刷。先开口，再对参考。  
每题要能用「和 Java/Kotlin 比，Python 这样写」说清楚。

---

## 必会

### PY1 · True / False / None（D1）

场景：从 Kotlin 过来，随手写了 `true` / `null`。

要说的那句：布尔字面量是 `True` 和 `False`。空值是 `None`。

参考：[内置常量](https://docs.python.org/zh-cn/3/library/constants.html)

### PY2 · `is` 与 `==`（D1）

场景：判断变量是不是空。

要说的那句：`==` 比内容。`is` 比是否同一对象。判断空值写 `x is None`。

参考：[值比较](https://docs.python.org/zh-cn/3/reference/expressions.html#value-comparisons) · [is 运算符](https://docs.python.org/zh-cn/3/reference/expressions.html#is-not)

### PY3 · list 与 dict（D2）

场景：对照 `ArrayList` / `HashMap` 选型。

要说的那句：有序列表用 `list`。键值映射用 `dict`。去重集合用 `set`。不可变小记录用 `tuple`。

参考：[列表](https://docs.python.org/zh-cn/3/tutorial/datastructures.html#more-on-lists) · [字典](https://docs.python.org/zh-cn/3/tutorial/datastructures.html#dictionaries)

### PY4 · 推导式（D2）

场景：Kotlin 里 `map`/`filter` 链，Python 怎么写短。

要说的那句：列表推导式在一个括号里完成映射和过滤。例如 `[x * 2 for x in xs if x > 0]`。

参考：[列表推导式](https://docs.python.org/zh-cn/3/tutorial/datastructures.html#list-comprehensions)

### PY5 · 切片（D2）

场景：取子串或倒序，不手下标循环。

要说的那句：`a[start:stop]` 不含 stop。`a[-1]` 是末项。`a[::-1]` 倒序。

参考：[序列类型](https://docs.python.org/zh-cn/3/library/stdtypes.html#common-sequence-operations)

### PY6 · `self` 与 `__init__`（D3）

场景：对照 Java 构造方法。

要说的那句：实例方法第一个参数是 `self`。构造逻辑写在 `__init__`。调用时不用手动传 `self`。

参考：[类简介](https://docs.python.org/zh-cn/3/tutorial/classes.html#a-first-look-at-classes)

### PY7 · dataclass（D3）

场景：对照 Kotlin `data class`。

要说的那句：`@dataclass` 按字段生成初始化等方法。适合装数据的类型。

参考：[dataclasses](https://docs.python.org/zh-cn/3/library/dataclasses.html)

### PY8 · `with` 关资源（D3）

场景：对照 Kotlin `use` 或 Java try-with-resources。

要说的那句：`with open(path) as f:` 在离开块时关闭文件。不要只靠手动 `close`。

参考：[with 语句](https://docs.python.org/zh-cn/3/reference/compound_stmts.html#with) · [读文件](https://docs.python.org/zh-cn/3/tutorial/inputoutput.html#reading-and-writing-files)

### PY9 · 模块与入口（D4）

场景：一个文件既被 import 又能直接跑。

要说的那句：一个 `.py` 文件是一个模块。直接运行时 `__name__` 等于 `"__main__"`。入口判断写 `if __name__ == "__main__":`。

参考：[模块](https://docs.python.org/zh-cn/3/tutorial/modules.html)

### PY10 · venv（D4）

场景：项目依赖和全局包搅在一起。

要说的那句：用 venv 给项目单独装包。激活后再 `pip install`。依赖列表可写进 `requirements.txt`。

参考：[venv](https://docs.python.org/zh-cn/3/library/venv.html)

### PY11 · Path 与 json（D5）

场景：读文本、写出结构化结果。

要说的那句：路径优先用 `pathlib.Path`。结构化数据用 `json` 模块读写。

参考：[pathlib](https://docs.python.org/zh-cn/3/library/pathlib.html) · [json](https://docs.python.org/zh-cn/3/library/json.html)

---

## 选做

### PY12 · `match`（D1+）

场景：对照 `when` / `switch`。

要说的那句：3.10 起可用 `match`。旧版本用 `if/elif` 或字典映射。

参考：[match 语句](https://docs.python.org/zh-cn/3/reference/compound_stmts.html#match)

### PY13 · 空容器真假（D2）

场景：`if items` 与 `if items is not None` 混用。

要说的那句：空 `list`/`dict`/`str` 在布尔上下文里是假。需要区分「没有对象」和「空容器」时，显式判断 `None`。

参考：[真值检测](https://docs.python.org/zh-cn/3/library/stdtypes.html#truth-value-testing)
