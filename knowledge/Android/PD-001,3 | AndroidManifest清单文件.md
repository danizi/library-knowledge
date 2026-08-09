# AndroidManifest 清单文件

**问：**
`AndroidManifest.xml` 是干啥的？里面最少要关心哪些块？

**答：**
它是应用的「户口本」：声明包/组件、权限、图标主题、能否被谁调起等。没声明的四大组件系统不认。  
日常优先盯：`uses-permission`、`<application>` 全局属性、`activity`/`service`/`receiver`/`provider` 声明、以及需要的 `intent-filter` / `queries`（Android 11+ 包可见性）。

一句话：Manifest = 向系统登记「我有谁、我要啥权限、别人怎么找到我」。

**例 / 类比：**
- 例子：新写一个 Activity 忘了登记 → 一启动就 `ActivityNotFoundException`。
- 类比：公司门禁名单——没录入的人进不了楼。

**易错：**
- 权限只写 Manifest 不够，危险权限还要运行时申请。
- 导出组件（`exported`）乱开有安全风险。

**相关：**
- [PD-001 | 工程文件目录](./PD-001%20%7C%20工程文件目录.md)

**参考：**
- [应用清单概览](https://developer.android.com/guide/topics/manifest/manifest-intro?hl=zh-cn)
