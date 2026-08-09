# Jetpack Compose

**问：**
Jetpack Compose 是啥？和传统 View 怎么对照着学？

**答：**
Compose 是 Android 的**声明式 UI**工具包：你描述「界面现在应该长什么样」，系统按状态去对齐；  
不像传统 View 那样到处找控件再命令式改。本卡是入口，细节在子卡。

一句话：Compose 用「状态 → UI」描述界面，而不是「找到控件再改」。

**例 / 类比：**
- 例子：`count` 变了，`Text("$count")` 自动更新，不必手写 `textView.text = …`。
- 类比：点菜报「要一份辣的」——厨房按菜单出菜；不是你冲进厨房一勺一勺炒。

**易错：**
- 别在 Compose 里继续「握着控件引用改」。
- 可与 XML View 共存，但先把声明式心态立住。

**相关：**
- [V-005,1 | Preview注解](./V-005,1%20%7C%20Preview注解.md)
- [V-005,2 | 声明式与命令式对比](./V-005,2%20%7C%20声明式与命令式对比.md)
- [V-005,4 | Compose 核心 API 分类](./V-005,4%20%7C%20Compose%20核心%20API%20分类.md)
- [V-005,4,a | Modifier](./V-005,4,a%20%7C%20Modifier.md)
- [V-001 | View的定义](./V-001%20%7C%20View的定义.md)

**参考：**
- [Compose 组件](https://developer.android.com/develop/ui/compose/components?hl=zh-cn)
