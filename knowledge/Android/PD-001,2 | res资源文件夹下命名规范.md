# res 资源命名规范

**问：**
layout / drawable / 各种资源文件，团队命名怎么定才不乱？

**答：**
关键是：**前缀表明类型 + 名字表明用途**，全小写+下划线。  
layout 常用 `activity_` / `fragment_` / `item_` / `dialog_`；drawable 常用 `ic_` / `bg_` / `selector_`。  
模块多时再加模块前缀。同一项目只选一种风格，写进团队约定。

一句话：看文件名就知道「是啥类型、干啥用」。

**例 / 类比：**
- 例子：`activity_login.xml`、`item_user.xml`、`bg_btn_primary.xml`、`selector_tab_icon.xml`。
- 类比：仓库货架标签——先写品类再写品名。

**易错：**
- 同一项目混用两套命名（类型在前 vs 模块在前）会找疯。
- 用驼峰或中文名。

**相关：**
- [PD-001,1 | res资源文件夹](./PD-001,1%20%7C%20res资源文件夹.md)

**参考：**
- （团队约定为主；官方无强制文件名规范）
