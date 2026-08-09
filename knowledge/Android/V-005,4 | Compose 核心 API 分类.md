# Compose 核心 API 分类

**问：** Compose API 那么多，怎么先分桶，免得记成一锅粥？

**答：**  
先按「干什么用」分 7 桶（记桶名 + 每桶 2～3 个代表即可）：  
1. **状态**：`remember` / `mutableStateOf` / `rememberSaveable` / `collectAsStateWithLifecycle`  
2. **资源**：`stringResource` / `painterResource`  
3. **布局**：`Column` / `Row` / `Box` / `LazyColumn` / `AndroidView`  
4. **组件**：`Text` / `Button` / `OutlinedTextField` / `Image`  
5. **协程与生命周期**：`rememberCoroutineScope` / `DisposableEffect`  
6. **动画与副作用**：`AnimatedVisibility` / `LaunchedEffect`  
7. **窗口适配**：`WindowInsets`（如 IME 避让）  

**例：** 列表页 ≈ 状态桶 + `LazyColumn`（布局）+ `Text`/`Image`（组件）+ 必要时 `LaunchedEffect` 拉数。

**易错：**
- `Modifier` 横切很多桶，单独见子卡，别塞进「组件」里糊弄过去。
- 副作用（`LaunchedEffect`）别当普通业务函数随处乱调。

**相关：**
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md) — 总入口
- [V-005,4,a | Modifier](./V-005,4,a%20%7C%20Modifier.md) — 修饰链专卡

**参考：**
- [Compose 文档总览](https://developer.android.com/develop/ui/compose?hl=zh-cn)
