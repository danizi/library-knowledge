# 类与dataclass

**问：** Python 的实例方法和 Kotlin data class，在写法上怎么对齐？

> **一句话：** 实例方法第一个参数是 `self`。装数据的类型优先用 `@dataclass`。

**例：** `@dataclass` 声明 `name: str` 与 `score: int`。再把对象放进 `list` 按 `score` 排序。

<details>
<summary>答</summary>

类用 `class Foo:` 声明。
构造逻辑写在 `__init__`。
调用方法时解释器传入实例，调用方不写 `self`。
没有 `public`/`private` 关键字。
单下划线表示约定上的内部属性。
`@dataclass` 按字段生成初始化等常用行为。

</details>

<details>
<summary>易错</summary>

- 不要在方法里漏写 `self`。
- 不要把接口思维硬套进来。先用鸭子类型。

</details>

<details>
<summary>相关</summary>

- [PY-001,4 | with与异常](<./PY-001,4 | with与异常.md>)
- [PY-001,2 | 集合与推导式](<./PY-001,2 | 集合与推导式.md>)

</details>

<details>
<summary>参考</summary>

- [类](https://docs.python.org/zh-cn/3/tutorial/classes.html)
- [dataclasses](https://docs.python.org/zh-cn/3/library/dataclasses.html)

</details>
