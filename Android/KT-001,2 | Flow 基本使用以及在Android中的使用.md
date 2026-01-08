**flow hello world**<br>
```
Flow是一个处理冷数据流的工具,冷流是只有消费者开始收集(collect)时，数据流才会开始发射数据。<br>

核心特征：
「生产者」（flow{}）和「消费者」（collect）都在协程中执行；
冷流特性：调用 simpleFlow 不会立即执行，只有 collect 时才开始发射数据。

// 1. 创建 Flow（发射 1、2、3）
val simpleFlow = flow<Int> {
    emit(1) // 发射数据
    emit(2)
    emit(3)
}

// 2. 消费 Flow（收集数据）
lifecycleScope.launch {
    simpleFlow.collect { value -> // 末端操作符：collect
        println("收集到数据：$value")
    }
}

```

**flow的创建**<br>
             
**flow的变换操作符**<br>
```
map、filter、flatMapConcat 、flatMapMerge 、transform、debounce、distinctUntilChanged

```

**flow末端操作符**<br>
```
collect、collectLatest、first、single、toList、launchIn

```

**flow异常处理**<br>
```
Flow 提供多层异常处理方案，核心是 `catch` 操作符（捕获生产者/中间操作的异常）：
1 基础异常处理
2 捕获 collect 中的异常（消费者异常）
3 onCompletion：最终执行（无论是否异常）
```


**flow的取消**<br>
```
Flow 依赖协程的取消机制，协程取消 → Flow 自动取消，核心方式：
1 通过协程作用域取消
2 手动取消协程
3 Flow 内部响应取消
```


**分离flow的消费和触发**<br>
```
核心是「提前定义 Flow，延迟收集」，利用冷流特性实现解耦：

// 1. 定义 Flow（仅声明，不执行）
val userFlow = fetchUserFlow()
    .map { it.name }
    .catch { emit("默认名") }

// 2. 触发消费（在需要时调用）
fun startFetchUser() {
    // 消费逻辑1：更新 UI
    lifecycleScope.launch {
        userFlow.collect { name ->
            tvUserName.text = name
        }
    }

    // 消费逻辑2：打印日志（可多个消费者）
    lifecycleScope.launch {
        userFlow.collect { name ->
            Log.d("Flow", "用户名：$name")
        }
    }
}
```

**flow的背压**<br>

