# res 资源命名规范

**问：** 资源文件名怎样同时表明类型和用途？

> **一句话：** 文件名使用全小写和下划线。前缀表明类型。后面的词表明用途。

**例：** 布局文件可以叫 `activity_login.xml` 或 `item_user.xml`。drawable 可以叫 `bg_btn_primary.xml` 或 `selector_tab_icon.xml`。

<details>
<summary>答</summary>

文件名使用全小写。
单词之间使用下划线。
前缀表明类型。
前缀后面的词表明用途。
layout 常用前缀有 `activity_`、`fragment_`、`item_` 和 `dialog_`。
drawable 常用前缀有 `ic_`、`bg_` 和 `selector_`。
模块变多时，再加模块前缀。
同一个项目只选一种风格。
把这种风格写进团队约定。

</details>

<details>
<summary>类比</summary>

货架标签先写品类，再写品名。

</details>

<details>
<summary>易错</summary>

- 不要在同一个项目里混用两套命名。一套把类型放在前面。另一套把模块放在前面。混用之后难以查找。
- 不要使用驼峰。不要使用中文文件名。

</details>

<details>
<summary>相关</summary>

- [PD-001,1 | res资源文件夹](<./PD-001,1 | res资源文件夹.md>)

</details>

<details>
<summary>参考</summary>

- 命名以团队约定为准。官方没有强制的文件名规范。

</details>
