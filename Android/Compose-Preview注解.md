<details open>
  <summary>卡片内容</summary>
  
  - 核心问题
    <p>@Preview注解有哪些参数，分别的作用是什么？</p>
    
  - 核心答案
    ```kotlin
    annotation class Preview(
        // 预览名称与组
        val name: String = "",
        val group: String = "",
        // 尺寸
        @IntRange(from = 1) val apiLevel: Int = -1,
        val widthDp: Int = -1,
        val heightDp: Int = -1,
        val locale: String = "",                            // 语言区域配置
        @FloatRange(from = 0.01) val fontScale: Float = 1f, // 文本大小
        val showSystemUi: Boolean = false,                  // 显示系统界面
        val showBackground: Boolean = false,
        val backgroundColor: Long = 0,
        @UiMode val uiMode: Int = 0,                        // 主题模式（深色浅色）
        @Device val device: String = Devices.DEFAULT,       // 运行设备配置
        @Wallpaper val wallpaper: Int = Wallpapers.NONE,    // 配置预览，必须将showBackground置为true
    )
     ```
</details>

<details>
  <summary>关联卡片</summary>

</details>

<details>
  <summary>参考文献</summary>
  [google官方文档(工具>设计>预览您的界面)](https://developer.android.com/develop/ui/compose/tooling/previews?hl=zh-cn#ui-mode)

</details>
