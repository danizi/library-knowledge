# Flow 的使用

**问：** 为什么说 Kotlin Flow 是冷数据流？

> **一句话：** 没有收集者时，Flow 不调用 `emit`。有人调用 `collect` 之后，Flow 才开始发射值。

**例：** 下面的 Flow 在 `collect` 之后才发射 1、2、3。常用变换是 `map`、`filter`、`debounce` 和 `distinctUntilChanged`。末端常用 `collect` 或 `collectLatest`。

```kotlin
val simpleFlow = flow {
    emit(1)
    emit(2)
    emit(3)
}
lifecycleScope.launch {
    simpleFlow.collect { println(it) }
}
```

<details>
<summary>答</summary>

Flow 在协程里发射一串值。
冷表示只定义流还不会生产值。
没有人调用 `collect` 时，Flow 不调用 `emit`。
有收集者之后，Flow 才开始生产值。

</details>

<details>
<summary>类比</summary>

图纸上的水管还没有打开水龙头。按这个约定，水厂先不送水。

</details>

<details>
<summary>易错</summary>

- 不要在主线程做耗时的 `emit` 或 `collect`。耗时工作会卡住界面。把工作放到合适的调度器。
- 不要把 Flow 当成一调用就运行的热流。`SharedFlow` 和 `StateFlow` 是另一类。

</details>

<details>
<summary>相关</summary>

- [KT-001,1 | 基本概念](<./KT-001,1 | 基本概念.md>)
- [KT-001 | Kotlin](<./KT-001 | Kotlin.md>)

</details>

<details>
<summary>参考</summary>

- [Flow 官方文档](https://kotlinlang.org/docs/flow.html)

</details>
