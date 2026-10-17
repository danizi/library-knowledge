# 已吸收

这些笔记的结论已经写进正式卡或错题库，**不再算未审核**。不要把这里的句子再抄回 `.cursor/memory/`。

吸收日：2026-10-17。

| 文件 | 落在 |
|------|------|
| `D0-act-d0-hello.md` | [PD-001](<../../../knowledge/Android/PD-001 | 工程文件目录.md>) 易错：Open 带 `settings.gradle` / `gradlew` 的工程根。`MAIN` / `LAUNCHER` 在 ACT-001 |
| `D2-lifecycle-log.md` | ACT-001,1 例（冷启动、A 开 B、返回、Home） |
| `D3-intent-launchmode.md` | ACT-001,2 例（Extra / 回传）；ACT-001,4 例（singleTop / `onNewIntent`） |
| `D4-survive-rotate.md` | ACT-001,3 例（ViewModel 还在，`remember` 归零） |
| `D5-host-fragment.md` | FG-001,5 例；没入栈就退出见 FG-001,3 |
| `F1-fragment-backstack.md` | FG-001,3 例与易错；`runOnCommit` 见 FG-001,6 |
| `F2-dialog-fragment.md` | FG-001,2 例（`restored=true`，裸 Dialog 会丢） |
| `F3-fragment-transaction.md` | FG-001,6 例（commit 立刻 find 仍是旧的；hide 只关灯） |
| `D4-fg-view-lifecycle.md` | FG-001,1 例；FG-001,7 例。文末 ViewBinding 改了哪些 Demo 是工程记录，不升概念卡 |
| `D5-fragment-result.md` | FG-001,4 例（Result 一次性，不互握） |
| `D6-nested-fragment.md` | FG-001,8 例（childFM + primaryNavigation） |
| `D7-fragment-args.md` | FG-001,9 例（空构造 + arguments，旋转后 id 还在） |
| `D8-navigation.md` | FG-001,10 例（NavHost；返回由 NavController pop） |
| `D9-viewpager2.md` | FG-001,11 例（FragmentStateAdapter；离屏可换新实例） |

仍留在未审核区的只有 `../react语法学习.md`（摘抄，不整篇晋升）。
