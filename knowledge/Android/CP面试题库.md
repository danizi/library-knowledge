# ContentProvider 面试题库

> 和 [CP学习计划](CP学习计划.md) 配合。日程 **D0–D4**，题号 **P1–P8**，卡片 **CP-001,x**。  
> 栽过的进 [CP错题库](CP错题库.md)。

### 按天刷题

| 天 | 必刷 |
|----|------|
| D1 | P1 P2 |
| D2 | P3 P4 |
| D3 | P5 P6 |
| D4 | P1–P6 全真；P7 P8 口头 |

---

## 0. 还考吗？

| 态度 | 说明 |
|------|------|
| **还考** | URI、CRUD、权限/exported；FileProvider 分享文件。 |
| **考法** | 「怎么安全把文件给别的 App」「Cursor 忘关会怎样」。 |

---

## 1. 真题

| # | 真题 | 要答到的点 | 落点 |
|---|------|------------|------|
| P1 | ContentProvider 是啥？ | 跨应用数据访问组件；`content://` | `CP-001` |
| P2 | authority / URI 怎么理解？ | Manifest 唯一权威名；路径定资源 | 同上 · cp-d1 |
| P3 | 要实装哪些方法？ | query/insert/update/delete（+getType） | `CP-001,1` · cp-d2 |
| P4 | Cursor 注意啥？ | 用完关闭；别漏在主线程狂扫大表 | 同上 |
| P5 | 怎么做读写权限？ | `readPermission` / `writePermission`；申请与 grant | `CP-001,2` · cp-d3 |
| P6 | `exported` 乱开有啥风险？ | 数据可被外部读写；默认收紧 | 同上 |
| P7 | FileProvider 解决啥？ | `file://` 暴露不安全；用 content URI + 临时授权分享 | D4 口头 |
| P8 | 和 SQLite / Room 关系？ | Provider 是对外门面；内部仍可用 DB | 选做 |

### 降温

背 ContentResolver 每一个重载；同步适配器源码。

---

## 2. 默答清单

1. 是啥 + URI（P1 P2）  
2. CRUD + Cursor（P3 P4）  
3. 权限与导出（P5 P6）  
4. FileProvider 一句（P7）
