**flow hello world**<br>
```kotlin
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
```kotlin
map、filter、flatMapConcat 、flatMapMerge 、transform、debounce、distinctUntilChanged

map转换数据类型示例
// 将 Int 转为 String
flowOf(1,2,3)
    .map { it.toString() }
    .collect { println("转换后：$it") } // 1 → "1"



filter过滤数据示例
// 只保留偶数
flowOf(1,2,3,4)
    .filter { it % 2 == 0 }
    .collect { println("偶数：$it") } // 2、4



flatMapConcat串联多个 Flow（顺序执行）示例
// 先获取用户，再获取用户的订单（顺序）
fetchUserFlow()
    .flatMapConcat { user -> fetchOrderFlow(user.id) }
    .collect { order -> println("用户订单：$order") }



flatMapMerge并行合并多个 Flow（并发执行）示例
// 并行获取多个用户的订单
fetchUserFlow()
    .flatMapMerge { user -> fetchOrderFlow(user.id) }
    .collect { order -> println("并行订单：$order") }



transform自定义变换（可发射多个值/跳过）示例
flowOf(1,2,3)
    .transform { value ->
        emit("原始值：$value")
        if (value > 1) emit("大于1的值：$value")
    }
    .collect { println(it) } // 输出：原始值1、原始值2、大于1的值2...


debounce防抖（Android 搜索框高频场景）示例
// 搜索框输入防抖（500ms 无输入才发射）
searchEditText.textChangesFlow()
    .debounce(500)
    .filter { it.isNotEmpty() }
    .collect { keyword -> searchApi(keyword) }

distinctUntilChanged去重（仅发射与前一个不同的值）
// 避免重复数据（如 UI 重复刷新）
flowOf(1,1,2,2,3)
    .distinctUntilChanged()
    .collect { println(it) } // 1、2、3
```

**flow末端操作符**<br>
```kotlin
collect、collectLatest、first、single、toList、toSet、launchIn

collect收集所有数据（最常用）示例
lifecycleScope.launch {
    fetchUserFlow().collect { user ->
        updateUI(user) // 更新 UI
    }
}

collectLatest只处理最新数据（取消旧任务）示例
searchFlow.collectLatest { keyword ->
    searchApi(keyword) // 若新关键词到来，取消旧请求
}

first获取第一个数据（终止 Flow）示例
val firstUser = fetchUserFlow().first()

single获取单个数据（Flow 只能发射一个值）示例
val singleUser = fetchUserFlow().single()

toLis、toSet转为集合示例
val userList  = fetchUserFlow().toList()
val userSet  = fetchUserFlow().toSet()

launchIn 绑定生命周期
fetchUserFlow()
    .onEach { user -> updateUI(user) }
    .launchIn(lifecycleScope)
```

**flow异常处理**<br>
```
Flow 提供多层异常处理方案，核心是 `catch` 操作符（捕获生产者/中间操作的异常）：
（1）基础异常处理（`catch`）
（2）捕获 collect 中的异常（消费者异常）
（3）onCompletion：最终执行（无论是否异常）

（1）示例
fetchUserFlow()
  .map { it.name } // 中间操作可能抛异常
  .catch { e -> // 捕获上游所有异常
      println("异常：${e.message}")
      emit("默认用户名") // 发射默认值，不终止 Flow
  }
  .collect { name -> updateUserName(name) }

（2）示例
lifecycleScope.launch {
    try {
        fetchUserFlow().collect { user ->
            // 消费者逻辑（可能抛异常）
            if (user.id < 0) throw IllegalArgumentException("ID 非法")
            updateUI(user)
        }
    } catch (e: Exception) {
        println("消费异常：${e.message}")
    }
}
（3）示例
fetchUserFlow()
    .catch { e -> emit(defaultUser) }
    .onCompletion { e -> // e 为 null 表示正常完成
        if (e != null) println("Flow 异常结束：${e.message}")
        else println("Flow 正常完成")
    }
    .collect { user -> updateUI(user) }

```


**flow的取消**<br>
```kotlin
Flow 依赖协程的取消机制，协程取消 → Flow 自动取消，核心方式：
（1）通过协程作用域取消（Android 最常用）
（2）手动取消协程
（3）Flow 内部响应取消（ensureActive/isActive）
（1）示例
// 绑定生命周期：页面销毁 → lifecycleScope 取消 → Flow 自动取消
fetchUserFlow()
    .onEach { user -> updateUI(user) }
    .launchIn(lifecycleScope) // 页面销毁，Flow 停止发射

（2）示例
val job = lifecycleScope.launch {
    fetchUserFlow().collect { user -> updateUI(user) }
}

// 手动取消（如点击按钮）
btnCancel.setOnClickListener {
    job.cancel() // 协程取消，Flow 停止
}

（3）示例
flow<Int> {
    for (i in 1..10) {
        ensureActive() // 检查协程是否活跃，取消则抛异常
        // 或手动检查：if (!isActive) return@flow
        emit(i)
        delay(1000)
    }
}.collect { println(it) }
```


**分离flow的消费和触发**<br>
```kotlin
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
```kotlin
背压是「生产者发射速度 > 消费者处理速度」时的流量控制方案，Flow 内置背压处理，核心方式：
（1）内置背压策略（buffer/conflate/collectLatest）
（2）手动控制背压（onBufferOverflow）

（1）示例
// 生产者：每秒发射 10 个数据（快）
val fastFlow = flow<Int> {
    repeat(100) {
        emit(it)
        delay(100) // 100ms 发射一个，每秒 10 个
    }
}

// 消费者：每秒处理 1 个数据（慢）
lifecycleScope.launch {
    fastFlow
        // .buffer() // 缓存（默认64，超出会挂起生产者）
        // .conflate() // 只保留最新数据
        .collectLatest { value -> // 取消旧任务，处理最新
            delay(1000) // 1 秒处理一个
            println("处理数据：$value")
        }
}

（2）示例
fastFlow
    .buffer(
        capacity = 10, // 缓存容量
        onBufferOverflow = BufferOverflow.DROP_OLDEST // 溢出时丢弃最旧数据
    )
    .collect { println(it) }

```

**Android中提供的扩展**<br>
**1. 生命周期绑定Flow**<br>
```
（1）repeatOnLifecycle（推荐，替代 launchWhenStarted）
（2）flowWithLifecycle

（1）示例
// 仅在 Lifecycle 处于 STARTED 状态时收集，STOPPED 时暂停，DESTROYED 时取消
lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED) {
        fetchUserFlow().collect { user ->
            updateUI(user)
        }
    }
}

（2）示例
fetchUserFlow()
    .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
    .onEach { user -> updateUI(user) }
    .launchIn(lifecycleScope)
```

**2. UI 相关 Flow 扩展**<br>
```
（1）StateFlow/SharedFlow（热流，Android 状态管理核心）
StateFlow：持有单个状态的热流（如 UI 状态），始终有初始值，仅发射最新值；
SharedFlow：可发射多个值的热流（如事件通知），无初始值。

（2）View 相关 Flow（如 EditText 文本变化）

（1）示例
class UserViewModel : ViewModel() {
    // 私有可变 StateFlow
    private val _uiState = MutableStateFlow(UserUiState.Loading)
    // 公开不可变 StateFlow
    val uiState: StateFlow<UserUiState> = _uiState

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading
            try {
                val user = api.getUser()
                _uiState.value = UserUiState.Success(user)
            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(e.message)
            }
        }
    }
}

// Activity 中收集
lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState.collect { state ->
            when (state) {
                is UserUiState.Loading -> showLoading()
                is UserUiState.Success -> showUser(state.user)
                is UserUiState.Error -> showError(state.msg)
            }
        }
    }
}

（2）示例
// 依赖：implementation 'androidx.core:core-ktx:1.12.0'
editText.textChangesFlow() // 文本变化 Flow
    .debounce(500)
    .filter { it.isNotEmpty() }
    .collect { keyword -> search(keyword) }

// 点击事件 Flow
button.clicksFlow()
    .throttleFirst(1000) // 防重复点击（1 秒内只响应一次）
    .collect { onButtonClick() }
```

**3. Room 数据库 Flow 支持**<br>
```
// Dao 层
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>> // 数据库变化自动发射
}

// 收集数据（自动响应数据库变化）
lifecycleScope.launch {
    userDao.getAllUsers()
        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        .collect { users -> updateUserList(users) }
}
```


