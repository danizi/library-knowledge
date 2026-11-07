# AndroidManifest 清单文件

**问：** `AndroidManifest.xml` 向系统登记什么？

> **一句话：** 清单登记组件、权限，以及别人怎样启动这些组件。没有登记的组件，系统不认。

**例：** 新建的 Activity 忘记登记。一启动就抛出 `ActivityNotFoundException`。

<details>
<summary>答</summary>

清单声明包名和组件。
清单声明权限、图标和主题。
清单声明谁可以启动这些组件。
没有在清单里声明的 Activity，系统不认。
没有在清单里声明的 Service，系统不认。
没有在清单里声明的 BroadcastReceiver，系统不认。
没有在清单里声明的 ContentProvider，系统不认。
日常先看 `uses-permission`。
再看 `application` 的全局属性。
再看 `activity`、`service`、`receiver` 和 `provider` 的声明。
需要时再看 `intent-filter`。
从 Android 11 起，包可见性还要看 `queries`。

</details>

<details>
<summary>类比</summary>

门禁名单没有录入的人，进不了这栋楼。

</details>

<details>
<summary>易错</summary>

- 只在清单里声明危险权限是不够的。危险权限还要在运行时申请。
- 不要随意把 `exported` 设为 true。开放导出的组件会带来安全风险。

</details>

<details>
<summary>相关</summary>

- [PD-001 | 工程文件目录](<./PD-001 | 工程文件目录.md>)

</details>

<details>
<summary>参考</summary>

- [应用清单概览](https://developer.android.com/guide/topics/manifest/manifest-intro?hl=zh-cn)

</details>
