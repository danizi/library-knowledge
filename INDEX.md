# 卡片索引

按领域浏览；新建或移动卡片后请同步更新本页。  
知识根目录：`knowledge/` · 概念卡模板（费曼：问/答/一句话/例或类比/易错）：`[.cursor/templates/card.md](.cursor/templates/card.md)` · 编排：`[.cursor/README.md](.cursor/README.md)`

含 `|` 的卡片文件名，链接写成 `[标题](<knowledge/Android/编码 | 标题.md>)`，用 `<>` 包真实路径；不要写成 `%20%7C`，Cursor 会点不开。

> `knowledge/Android|Web|Tools` 下正式卡均已统一为费曼格式（问/答/一句话/例或类比/易错）。  
> `JavascriptDemo/` 仍是练习场，不成概念卡。

---

## Android

### ACT · Activity

- [ACT 学习计划 · 7 天稳妥](knowledge/Android/ACT学习计划.md)
- [ACT 面试题库](knowledge/Android/ACT面试题库.md)
- [ACT 错题库](knowledge/Android/ACT错题库.md) — 只记你答歪过的，复习优先于题库全表
- Demo（命名：`act-d{天}-主题` / `fg-d{天}-主题`，两线各算各的）
  - [act-d0-hello](knowledge/Android/demos/activity/act-d0-hello/)（D0）
  - [act-d1-two-screens](knowledge/Android/demos/activity/act-d1-two-screens/)（D1）
  - [act-d2-lifecycle-log](knowledge/Android/demos/activity/act-d2-lifecycle-log/)（D2）
  - [act-d3-intent-extra](knowledge/Android/demos/activity/act-d3-intent-extra/)（D3）
  - [act-d4-survive-rotate](knowledge/Android/demos/activity/act-d4-survive-rotate/)（D4）
  - [act-d5-host-fragment](knowledge/Android/demos/activity/act-d5-host-fragment/)（D5）
- [ACT-001 | Activity](<knowledge/Android/ACT-001 | Activity.md>)
  - [ACT-001,1 | Activity生命周期](<knowledge/Android/ACT-001,1 | Activity生命周期.md>)
  - [ACT-001,2 | Intent与传参](<knowledge/Android/ACT-001,2 | Intent与传参.md>)
  - [ACT-001,3 | 配置变更与状态](<knowledge/Android/ACT-001,3 | 配置变更与状态.md>)
  - [ACT-001,4 | 启动模式与任务栈](<knowledge/Android/ACT-001,4 | 启动模式与任务栈.md>)



### FG · Fragment

- [FG 学习计划 · 11 天稳妥](knowledge/Android/FG学习计划.md)
- [FG 面试题库](knowledge/Android/FG面试题库.md)
- [FG 错题库](knowledge/Android/FG错题库.md)
- Demo（交界工程在 ACT 目录；FG 自有 `fg-d*`）
  - [act-d5-host-fragment](knowledge/Android/demos/activity/act-d5-host-fragment/)（D0 · 交界，工程仍在 ACT 目录）
  - [fg-d1-backstack](knowledge/Android/demos/fragment/fg-d1-backstack/)（D1）
  - [fg-d2-dialog-fragment](knowledge/Android/demos/fragment/fg-d2-dialog-fragment/)（D2）
  - [fg-d3-fragment-transaction](knowledge/Android/demos/fragment/fg-d3-fragment-transaction/)（D3）
  - [fg-d4-view-lifecycle](knowledge/Android/demos/fragment/fg-d4-view-lifecycle/)（D4）
  - [fg-d5-fragment-result](knowledge/Android/demos/fragment/fg-d5-fragment-result/)（D5）
  - [fg-d6-nested-fragment](knowledge/Android/demos/fragment/fg-d6-nested-fragment/)（D6）
  - [fg-d7-fragment-args](knowledge/Android/demos/fragment/fg-d7-fragment-args/)（D7）
- [FG-001 | Fragment](<knowledge/Android/FG-001 | Fragment.md>)
  - [FG-001,1 | Fragment生命周期](<knowledge/Android/FG-001,1 | Fragment生命周期.md>)
  - [FG-001,2 | DialogFragment](<knowledge/Android/FG-001,2 | DialogFragment.md>)
  - [FG-001,3 | Fragment回退栈](<knowledge/Android/FG-001,3 | Fragment回退栈.md>)
  - [FG-001,4 | Fragment通信](<knowledge/Android/FG-001,4 | Fragment通信.md>)
  - [FG-001,5 | Fragment与Activity关联](<knowledge/Android/FG-001,5 | Fragment与Activity关联.md>)
  - [FG-001,6 | Fragment事务](<knowledge/Android/FG-001,6 | Fragment事务.md>)
  - [FG-001,7 | viewLifecycleOwner](<knowledge/Android/FG-001,7 | viewLifecycleOwner.md>)
  - [FG-001,8 | 嵌套Fragment](<knowledge/Android/FG-001,8 | 嵌套Fragment.md>)
  - [FG-001,9 | Fragment传参与状态](<knowledge/Android/FG-001,9 | Fragment传参与状态.md>)
  - FG-001,10 Navigation（待建）
  - FG-001,11 ViewPager2（待建）



### V · View / UI / Compose

- [V-001 | View的定义](<knowledge/Android/V-001 | View的定义.md>)
- [V-002 | 盒子模型](<knowledge/Android/V-002 | 盒子模型.md>)
- [V-003 | 坐标系](<knowledge/Android/V-003 | 坐标系.md>)
- [V-004 | Widget](<knowledge/Android/V-004 | Widget.md>)
- [V-005 | Jetpack Compose](<knowledge/Android/V-005 | Jetpack Compose.md>)
  - [V-005,1 | Preview注解](<knowledge/Android/V-005,1 | Preview注解.md>)
  - [V-005,2 | 声明式与命令式对比](<knowledge/Android/V-005,2 | 声明式与命令式对比.md>)
  - [V-005,4 | Compose 核心 API 分类](<knowledge/Android/V-005,4 | Compose 核心 API 分类.md>)
    - [V-005,4,a | Modifier](<knowledge/Android/V-005,4,a | Modifier.md>)



### PD · 工程与资源

- [PD-001 | 工程文件目录](<knowledge/Android/PD-001 | 工程文件目录.md>)
  - [PD-001,1 | res资源文件夹](<knowledge/Android/PD-001,1 | res资源文件夹.md>)
    - [PD-001,1,a | drawable目录](<knowledge/Android/PD-001,1,a | drawable目录.md>)
      - [PD-001,1,a,1 | 状态列表的使用](<knowledge/Android/PD-001,1,a,1 | 状态列表的使用.md>)
      - [PD-001,1,a,2 | View状态常量](<knowledge/Android/PD-001,1,a,2 | View状态常量.md>)
      - [PD-001,1,a,3 | 形状可绘制对象](<knowledge/Android/PD-001,1,a,3 | 形状可绘制对象.md>)
    - [PD-001,1,b | values目录](<knowledge/Android/PD-001,1,b | values目录.md>)
      - [PD-001,1,b,1 | 记一次主题样式重构](<knowledge/Android/PD-001,1,b,1 | 记一次主题样式重构.md>)
  - [PD-001,2 | res资源文件夹下命名规范](<knowledge/Android/PD-001,2 | res资源文件夹下命名规范.md>)
  - [PD-001,3 | AndroidManifest清单文件](<knowledge/Android/PD-001,3 | AndroidManifest清单文件.md>)



### KT · Kotlin

- [KT-001 | Kotlin](<knowledge/Android/KT-001 | Kotlin.md>)
  - [KT-001,1 | 基本概念](<knowledge/Android/KT-001,1 | 基本概念.md>)
  - [KT-001,2 | Flow 的使用](<knowledge/Android/KT-001,2 | Flow 的使用.md>)



### AAP · 架构 / DP · 设计模式 / ERR · 踩坑

- [AAP-001 | Android 架构模式](<knowledge/Android/AAP-001 | Android 架构模式.md>)
- [DP-001 | 设计模式](<knowledge/Android/DP-001 | 设计模式.md>)
- [ERR-001 | 开发踩坑记录](<knowledge/Android/ERR-001 | 开发踩坑记录.md>)



### 待建（原 INDEX 规划）

- 系统控件：RecyclerView / TabLayout
- 第三方库：AOP / Router

---



## Web



### R · React

- [R-001 | React](<knowledge/Web/R-001 | React.md>)
  - [R-001,a | 环境搭建](<knowledge/Web/R-001,a | 环境搭建.md>)
  - [R-001,b | 基本语法](<knowledge/Web/R-001,b | 基本语法.md>)
  - [R-001,c | 项目使用及说明](<knowledge/Web/R-001,c | 项目使用及说明.md>)



### 练习场（非卡片）

- [JavascriptDemo · JS 学习计划](knowledge/Web/JavascriptDemo/JS学习计划.md)

---



## Tools

- [TL-001 | VS Code Git](<knowledge/Tools/TL-001 | VS Code Git.md>)
  - [TL-001,1 | git pull 分叉速查](<knowledge/Tools/TL-001,1 | git pull 分叉速查.md>)
- [TL-002 | macOS 安装 Homebrew](<knowledge/Tools/TL-002 | macOS 安装 Homebrew.md>)
- [TL-003 | 修改 Hosts](<knowledge/Tools/TL-003 | 修改 Hosts.md>)

---



## memory · 未审核

- [react语法学习](.cursor/memory/react语法学习.md) → 拆入 `R-001`（喊「整理 memory」）
- [D0-act-d0-hello](.cursor/memory/D0-act-d0-hello.md) → Open 必须点到 Gradle 根目录
- [D2-lifecycle-log](.cursor/memory/D2-lifecycle-log.md) → 四段 LIFE 顺序
- [D3-intent-launchmode](.cursor/memory/D3-intent-launchmode.md) → Extra / 回传 / onNewIntent
- [D4-survive-rotate](.cursor/memory/D4-survive-rotate.md) → ViewModel 旋转还在，remember 归零
- [D5-host-fragment](.cursor/memory/D5-host-fragment.md) → ACT D5：单 Activity 宿主；没入栈返回会退出
- [F1-fragment-backstack](.cursor/memory/F1-fragment-backstack.md) → D1：根 Home 不入栈；`runOnCommit` 不能配 `addToBackStack`
- [F2-dialog-fragment](.cursor/memory/F2-dialog-fragment.md) → D2：DialogFragment 旋转 `restored=true`，裸 Dialog 会丢
- [F3-fragment-transaction](.cursor/memory/F3-fragment-transaction.md) → D3：replace 拆 view；hide 只关灯；commit 立刻 find 可能仍是旧的
- [D4-fg-view-lifecycle](.cursor/memory/D4-fg-view-lifecycle.md) → D4：hide 看 HiddenChanged；入栈拆 view；观察用 viewLifecycleOwner
- [D5-fragment-result](.cursor/memory/D5-fragment-result.md) → D5：Result API 一次性回传，不互握 Fragment
- [D6-nested-fragment](.cursor/memory/D6-nested-fragment.md) → D6：嵌套用 childFM；primaryNavigation 让系统返回先弹子栈
- [D7-fragment-args](.cursor/memory/D7-fragment-args.md) → D7：空构造 + arguments；旋转实例换了 id 还在



## skills

- [android-studio-autosetup](.cursor/skills/android-studio-autosetup/SKILL.md)
- `kb-card-review` / `kb-code-review` / `kb-coach` / `kb-promote` / `kb-verify` / `kb-tutor`

