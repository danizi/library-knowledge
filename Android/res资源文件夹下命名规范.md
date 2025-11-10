
## 资源文件 
<details>
  <summary>layout/资源</summary>
 
  - 前缀名称说明
    ```
    R.layout.activity_xxx.xml // activity布局
    R.layout.fragment_xxx.xml // fragment布局
    R.layout.dialog_xxx.xml   // dialog布局
    R.layout.layout_xxx.xml   // 共用布局
    R.layout.menu_xxx.xml     // 菜单
    R.layout.item_xxx.xml     // 列表item
    ```
  - 命名方式一(类型+功能)
    ```
    R.layout.activity_share.xml
    R.layout.fragment_share.xml
    R.layout.dialog_share.xml
    R.layout.layout_share.xml
    R.layout.menu_share.xml 
    R.layout.item_share.xml
    ```
    
  - 命名方式二(模块+功能+类型)
    ```
    R.layout.home_activity
    R.layout.home_fragment
    R.layout.home_dialog
    R.layout.home_share_activity
    R.layout.home_share_dialog
    R.layout.home_share_item_dialog
    R.layout.home_share_item_list_dialog
    R.layout.home_share_item_grid_dialog
    ```
</details>

<details>
  <summary>drawable/资源</summary>
  
  - 前缀名称说明
    ```
     R.drawable.ic_xx                   // 图标
     R.drawable.ic_xx_disabled          // 图标不可用
     R.drawable.ic_xx_pressed           // 图标按下
     R.drawable.bg_fill_xx              // 背景填充满无圆角
     R.drawable.bg_fill_xx_radius_x     // 背景填充满有圆角
     R.drawable.bg_outline_xx           // 背景填充边框无圆角
     R.drawable.bg_outline_xx_radius_x  // 背景填充边框有圆角
     R.drawable.selector_xxx            // 选择器
    ```
  - 命名方式一(类型+功能)
    ```
    R.drawable.ic_share_more
    R.drawable.ic_share_more_disabled
    R.drawable.ic_share_more_pressed
    R.drawable.bg_fill_share_dialog
    R.drawable.bg_fill_share_dialog_radius_4
    R.drawable.bg_outline_share_dialog
    R.drawable.bg_outline_share_dialog_radius_4
    R.drawable.selector_xxx            // 选择器
    ```
</details>

<details>
  <summary>values/资源</summary>
  
  - colors.xml
    - 前缀名称说明
      ```
      R.Color.color_xxx        // 基础色
      R.Color.color_accent_xxx // 强调色
      R.Color.background_xxx   // 背景色
      R.Color.border_xxx       // 边框色
      R.Color.text_xxx         // 文本色
      R.Color.fill_xxx         // 填充色
      R.Color.outline_xxx      // 边框色
      ```
    
  - dimens.xml
    - 前缀名称说明
      ```
      R.dimens.margin_数字    // 控件间距
      R.dimens.padding_数字   // 内边距
      R.dimens.border_数字    // 圆角
      R.dimens.widget_数字    // 控件大小
      R.dimens.text_数字      // 文字大小
      R.dimens.text_line_数字 // 文字行高
      ```
  
  - styles.xml(以 Theme 命名结尾)
    - 前缀名称说明
      ```
      <style name="xxxTheme" parent="xxx">
          .....
      </style>
      ```
    
  - themes.xml(以 Style 命名结尾)
    - 前缀名称说明
      ```
      <style name="xxxStyle" parent="xxx">
        .....
      </style>
      ```
      
  - 动画
</details>


https://github.com/getActivity/AndroidCodeStandard
