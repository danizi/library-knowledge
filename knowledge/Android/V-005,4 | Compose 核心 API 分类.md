compose 中核心api分类
- **状态管理类**       ：驱动compose重新刷新，api有remember/mutableStateOf/rememberLazyGridState/collectAsStateWithLifecycle/mutableStateOf/mutableStateListOf/rememberSaveable。
- **资源类**           ：加载字符串、颜色、位图等本地资源，api有stringResource/painterResource
- **布局类**           ：构建布局嵌入原生布局，api有AndroidView/Column/Row/Box/LazyColumn/LazyRow/Modifier
- **组件类**           ：Text/Button/OutlinedButton/OutlinedTextField/Image
- **携程与生命周期类** ：安全调度携程，api有rememberCoroutineScope/DisposableEffect
- **动画与副作用类**   ：布局动画和副作用处理，api有AnimatedVisibility/LaunchedEffect
- **系统窗口适配类**   ：适配系统UI避免遮挡，api有WindowInsets.ime.asPaddingValues/WindowInsets.ime
