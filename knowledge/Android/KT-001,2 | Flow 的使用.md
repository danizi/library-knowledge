# Flow 的使用

**问：**
Kotlin Flow 是啥？为啥说它是「冷」数据流？

**答：**
Flow 用来在协程里发射一串值。  
**冷**的意思：你只是定义了流，**没有人 collect，就不会开始 emit**；一有收集者，才开始生产。

一句话：Flow = 冷管道——有人接管子（collect）才出水（emit）。

**例 / 类比：**
```kotlin
val simpleFlow = flow {
    emit(1); emit(2); emit(3)
}
lifecycleScope.launch {
    simpleFlow.collect { println(it) }
}
```
- 常用变换先混个脸熟：`map` / `filter` / `debounce` / `distinctUntilChanged`；末端常用 `collect` / `collectLatest`。
- 类比：自来水管道图纸——没打开水龙头，水厂按这个约定先不送水。

**易错：**
- 在主线程做重活 emit/collect 会卡 UI——注意调度器。
- 把 Flow 当「一调用就跑」的热流（那是另一类，如 SharedFlow/StateFlow 场景）。

**相关：**
- [KT-001,1 | 基本概念](./KT-001,1%20%7C%20基本概念.md)
- [KT-001 | Kotlin](./KT-001%20%7C%20Kotlin.md)

**参考：**
- [Flow 官方文档](https://kotlinlang.org/docs/flow.html)
