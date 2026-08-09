# View 状态常量

**问：**
写 selector / 代码 addState 时，常见 View 状态常量各自表示什么？

**答：**
常用：`state_pressed` 按下、`state_focused` 焦点、`state_selected` 选中、`state_enabled` 启用、`state_checked` 勾选、`state_activated` 激活、`state_window_focused` 窗口焦点。  
在代码里对「假」状态常在属性前加负号（如 `-state_enabled` 表示禁用）。

一句话：状态常量 = selector 用来「对暗号」的开关名。

**例 / 类比：**
- 例子：按钮按下用 `state_pressed`；CheckBox 勾选用 `state_checked`。
- 类比：门禁卡上的权限位——对上哪一位就走哪条门。

**易错：**
- `selected` 和 `activated` 场景不同，别混用。
- XML 里写 `false` 与代码里负号属性，写法不一样。

**相关：**
- [PD-001,1,a,1 | 状态列表的使用](./PD-001,1,a,1%20%7C%20状态列表的使用.md)

**参考：**
- [StateList 文档](https://developer.android.com/guide/topics/resources/drawable-resource?hl=zh-cn#StateList)
