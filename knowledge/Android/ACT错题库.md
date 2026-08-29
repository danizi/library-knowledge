# Activity 错题库（只记你栽过的）

> 题库是「可能考什么」。这页是「你已经答歪过什么」。  
> 对话会忘；复习只遮「要说的那句」，先自己讲，再对。  
> 新栽一题：在表里加一行，或喊「记错题」。订正能流畅说两遍 → 状态改「已订正」，面试前 24 h 再扫一遍。

和题库对照：[ACT面试题库](ACT面试题库.md) · 卡片从「相关」点进去。

---

## 怎么用

1. 只看 **题** 和 **我当时的坑**，先开口。  
2. 对不上再看 **要说的那句**。  
3. 仍卡 → 复跑「相关 Demo」，不要只重读解析。

---

## 待订正（优先刷）

| 题 | 我当时的坑 | 要说的那句 | 相关 | 记于 |
|----|------------|------------|------|------|
| **A2** 返回顺序 | 模拟面先把 B `Stop/Destroy` 完再唤醒 A；练习时返回只说到 `onRestart` | 回：B Pause → A Restart/Start/Resume → **然后** B Stop/Destroy。开 B 还要在 B 亮完后 **A Stop** | [ACT-001,1](<./ACT-001,1 | Activity生命周期.md>) · stage2 `LIFE` | D6 模拟面 |
| **A4** 四种 mode | 数成「五种」；场景没举 | 就四种：`standard` 每次新建 / `singleTop` 栈顶复用（通知防叠）/ `singleTask` 栈内复用并清上面（回首页）/ `singleInstance` 独占一栈，几乎不用 | [ACT-001,4](<./ACT-001,4 | 启动模式与任务栈.md>) | D6 |
| **A5** singleTask 场景 | 只会说 singleTop 防重叠，说不清栈内复用 | `首页→列表→详情`，点回首页用 singleTask，上面两层清掉 | 同上 · stage3 是 singleTop 不是 Task | D6 |
| **A7** Bundle | 把 Bundle 说成「进程挂了存磁盘」 | Bundle / Saveable 扛**轻量 UI**（旋转也能活）；磁盘才是 DataStore/DB；ViewModel 不抗杀进程 | [ACT-001,3](<./ACT-001,3 | 配置变更与状态.md>) · stage4 | D6 |
| **A9** 仍要多 Activity | 只说泄漏、复用、好管；门外入口不会讲 | 里面换页单 Activity。分享 / 复杂外链 / 支付相机是系统砸大门，大门只能是 Activity | [FG-001,5](<./FG-001,5 | Fragment与Activity关联.md>) | D5–D6 |
| **A10** Manifest | 只谈 `exported` 开关 | 先登记 `<activity>`，忘了 → `ActivityNotFoundException`。桌面：MAIN+LAUNCHER 且 `exported=true`。第二扇窗不要 LAUNCHER，通常 `exported=false` | [PD-001,3](<./PD-001,3 | AndroidManifest清单文件.md>) · stage1 | D6 |
| **B2**（未答，D7 要补） | 还没开口 | `singleTask` **不一定**新栈，`taskAffinity` 不同才常新栈 | [ACT-001,4](<./ACT-001,4 | 启动模式与任务栈.md>) | D7 预告 |

---

## 已开口、只需防回潮

| 题 | 记住别缩水 | 记于 |
|----|------------|------|
| A1 | 主路径补到 Pause/Stop/Destroy；来电先 Pause，Pause 里不做重活 | D6 |
| A3 | 透明盖上来常只 Pause，不到 Stop | D6 刷题 |
| A6 | 复用才 `onNewIntent`；要 `setIntent`，否则 `getIntent()` 旧 Extra | D6 |
| A8 | 旋转 ViewModel 常还在；杀进程它也死 | D6 |

---

## 模拟面未答（D6 停在第 5 题）

下次「模拟面」从这里接，不必重开前 5 题（除非要计时全场）：

- 第 6 题：配置变更 vs 杀进程，ViewModel / Bundle / 磁盘  
- 第 7 题：单 Activity + 何时仍多 Activity  
- 第 8 题：Manifest 登记 + exported  
- 追问 1：透明 Activity 会不会 onStop  
- 追问 2：singleTask 是否一定新栈  

---

## 空模板（新栽复制）

```text
| **题号** | 一句话写歪在哪 | 订正后要说的那句 | 卡 / Demo | 日期 |
```
