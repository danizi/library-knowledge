# 蓝牙学习计划 · 12 天稳妥

目标：**12 天内**走完官方 BLE 实战链路，并知道经典蓝牙为什么不在这条链路里。每天约 **3 h**，D0 为 **2 h**，到点停。  
原则：Demo 不过不写卡；卡写完必须 **15 分钟默答**。一天只有一个新行为。

日程用 **D0–D11**。题号用 **BT1–**。卡片用 **BT-001,x**。  
D0–D9 各有一个 Demo 和一张卡。D10 默答，D11 复跑，这两天不新建工程。

原先写成 8 天，是把官方文档里分开的步骤压进了同一天：Find BLE devices 里的普通扫描和 `ScanFilter` / `ScanSettings` 不是一回事；Transfer BLE data 里的发现服务、读、通知也不是一回事。Punch Through 的 Android BLE 指南还把连接、写入、以及「上一次回调回来再发下一次」分开讲。所以内容日扩到 D0–D9。

## 学习重点

必学：Android 12 的扫描和连接权限（D2）、限时扫描（D3）、连接后先发现服务（D5）、读（D6）、写并等待回调（D7）、通知要写描述符（D8）。

知道即可：扫描过滤和低延迟模式（D4）、经典蓝牙 socket 会阻塞（D9）。能讲清和 BLE 不是一条路即可。

不必深挖：抓自己的蓝牙空口日志、Android 4.3 的协议栈不稳定、UUID 的全部档案。旧系统上扫描还要定位，本批 Demo 的 minSdk 是 31，知道有过这一页即可。

## 读过的材料

- [Set up Bluetooth](https://developer.android.com/develop/connectivity/bluetooth/setup)
- [Bluetooth permissions](https://developer.android.com/develop/connectivity/bluetooth/bt-permissions)
- [Find BLE devices](https://developer.android.com/develop/connectivity/bluetooth/ble/find-ble-devices)
- [Connect to a GATT server](https://developer.android.com/develop/connectivity/bluetooth/ble/connect-gatt-server)
- [Transfer BLE data](https://developer.android.com/develop/connectivity/bluetooth/ble/transfer-ble-data)
- [Connect Bluetooth devices](https://developer.android.com/develop/connectivity/bluetooth/connect-bluetooth-devices)
- [Punch Through：Android BLE Guide](https://punchthrough.com/android-ble-guide/) — 日常实战里权限、扫描、连接、读、写、通知的顺序

## 常见坑

`[android] [bluetooth]` 按票排序的前 8 条。

| 票 | 现象 | 链接 |
|----|------|------|
| 196 | Android 4.3 上 BLE 不稳定 | [17870189](https://stackoverflow.com/questions/17870189/android-4-3-bluetooth-low-energy-unstable) |
| 125 | Android 6 扫描 BLE 还必须开定位 | [33045581](https://stackoverflow.com/questions/33045581/location-needs-to-be-enabled-for-bluetooth-low-energy-scanning-on-android-6-0) |
| 122 | 想用代码直接开关蓝牙 | [3806536](https://stackoverflow.com/questions/3806536/how-to-enable-disable-bluetooth-programmatically-in-android) |
| 116 | 经典蓝牙 `connect` 报 socket might closed | [18657427](https://stackoverflow.com/questions/18657427/ioexception-read-failed-socket-might-closed-bluetooth-on-android-4-3) |
| 97 | Android 12 要新的蓝牙权限 | [67722950](https://stackoverflow.com/questions/67722950/android-12-new-bluetooth-permissions) |
| 95 | 经典蓝牙 UUID 到底要不要自己生成 | [13964342](https://stackoverflow.com/questions/13964342/android-how-do-bluetooth-uuids-work) |
| 90 | 想抓自己手机的蓝牙空口日志 | [23877761](https://stackoverflow.com/questions/23877761/sniffing-logging-your-own-android-bluetooth-traffic) |
| 88 | 怎么判断蓝牙开没开 | [7672334](https://stackoverflow.com/questions/7672334/how-to-check-if-bluetooth-is-enabled-programmatically) |

配套：[BT面试题库](BT面试题库.md) · [BT错题库](BT错题库.md)（只记你答歪的，现在是空表）  
环境：Android Studio + Kotlin。Demo 的 minSdk 是 **31**。  
Demo 目录：`knowledge/Android/demos/bluetooth/`  
命名：`bt-d{日程天}-{主题}`。与 `act-d*` / `fg-d*` / `svc-d*` / `br-d*` / `cp-d*` 各算各的。

不做 Mesh、A2DP、Companion Device Manager，也不做经典蓝牙文件传输。

---

## 12 天总览

| 天 | 主题 | 产出 | 面试覆盖 |
|----|------|------|----------|
| D0 | 清单声明 + 空界面 | `bt-d0-hello` + `BT-001,5` | BT16 |
| D1 | 有没有蓝牙、开没开 | `BT-001` + bt-d1-adapter | BT1 BT2 |
| D2 | 运行时权限 | `BT-001,1` + bt-d2-permission | BT3 BT4 |
| D3 | BLE 限时扫描 | `BT-001,2` + bt-d3-scan | BT5 BT6 |
| D4 | 扫描模式和名称过滤 | `BT-001,6` + bt-d4-scan-settings | BT11 |
| D5 | 连接、发现服务、关闭 | `BT-001,7` + bt-d5-connect | BT12 |
| D6 | 读一个特征 | `BT-001,3` + bt-d6-read | BT7 BT8 |
| D7 | 写一个特征 | `BT-001,8` + bt-d7-write | BT13 |
| D8 | 打开通知 | `BT-001,9` + bt-d8-notify | BT14 |
| D9 | 经典蓝牙对照，不传文件 | `BT-001,4` + bt-d9-classic | BT9 |
| D10 | 题库冲刺 | 默答 BT1–BT14、BT16 | 全真 |
| D11 | 复跑 | 复跑 1 个 Demo + 看错题库 | 选做 BT10 BT15 |

---

## 每日日程（每段到点停）

> 格式：`输入 → Demo → 输出卡 → 默答`。

### D0 · 工程能跑（2 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:30 | 30 min | 打开 `bt-d0-hello/`，确认 `settings.gradle.kts` 有 `include(":app")` | 能指出 app 模块 |
| 0:30–1:10 | 40 min | Run 到 API 31+ | 屏幕出现 D0 说明 |
| 1:10–1:40 | 30 min | 在清单里找到 `BLUETOOTH_SCAN` 和 `neverForLocation` | 能指出这两处 |
| 1:40–2:00 | 20 min | 写 `BT-001,5`，默答 BT16 | 不看卡 |

**D0 验收**：`[ ]` Run OK `[ ]` 今天没有弹出授权框 `[ ]` BT-001,5 落盘

### D1 · 适配器（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 读 Set up Bluetooth | 能分开 null 和没打开 |
| 0:25–1:35 | 70 min | `bt-d1-adapter/` | 关着蓝牙时能请求打开 |
| 1:35–2:20 | 45 min | 写 `BT-001`，默答 BT1、BT2 | 不看卡 |
| 2:20–3:00 | 40 min | 若系统因缺少连接权限拒绝，记下来留给 D2 | 今天不改权限 Demo |

**D1 验收**：`[ ]` 两种失败文案不同 `[ ]` BT-001 落盘

### D2 · 运行时权限（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 读 Bluetooth permissions 的 Android 12 一节 | 能分开 SCAN 和 CONNECT |
| 0:25–1:35 | 70 min | `bt-d2-permission/` | 允许和拒绝都能看见 |
| 1:35–2:20 | 45 min | 写 `BT-001,1`，默答 BT3、BT4 | 不看卡 |
| 2:20–3:00 | 40 min | 回到 bt-d1，授权后再请求打开 | 系统框能出现 |

**D2 验收**：`[ ]` 运行时框出现 `[ ]` BT-001,1 落盘

### D3 · 限时扫描（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 读 Find BLE devices 前半 | 能说出 10 秒后要停 |
| 0:20–1:40 | 80 min | `bt-d3-scan/` | 扫描会停 |
| 1:40–2:20 | 40 min | 写 `BT-001,2`，默答 BT5、BT6 | 不看卡 |
| 2:20–3:00 | 40 min | 没有外围设备时，只验收「会停」 | 不改成经典扫描 |

**D3 验收**：`[ ]` 扫描会停 `[ ]` BT-001,2 落盘

### D4 · 扫描设置（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 读 Find BLE devices 里 `startScan(List, ScanSettings, ScanCallback)` | 能说出过滤和模式是两个参数 |
| 0:25–1:40 | 75 min | `bt-d4-scan-settings/`：低延迟模式，可按设备名过滤 | 不过滤时仍能看到地址 |
| 1:40–2:20 | 40 min | 写 `BT-001,6`，默答 BT11 | 不看卡 |
| 2:20–3:00 | 40 min | 对照 bt-d3，口头讲两者差在参数，不差在多扫一种无线电 | 不新建第二个扫描工程 |

**D4 验收**：`[ ]` 用了 ScanSettings `[ ]` BT-001,6 落盘

### D5 · 连接（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 读 Connect to a GATT server | 能按连接 → 发现服务排序 |
| 0:25–1:40 | 75 min | `bt-d5-connect/`：填地址，连上后只列出服务 UUID | 不调用 readCharacteristic |
| 1:40–2:20 | 40 min | 写 `BT-001,7`，默答 BT12 | 不看卡 |
| 2:20–3:00 | 40 min | 离开页面后确认 close | 今天不写特征 |

**D5 验收**：`[ ]` 屏幕有服务 UUID 或失败状态 `[ ]` BT-001,7 落盘

### D6 · 读（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 读 Transfer BLE data 的读特征一节 | 能说出回调才有字节 |
| 0:20–1:40 | 80 min | `bt-d6-read/` | 有十六进制或失败码 |
| 1:40–2:20 | 40 min | 写 `BT-001,3`，默答 BT7、BT8 | 不看卡 |
| 2:20–3:00 | 40 min | 离开页面后 close | 不在这次读取里打开通知 |

**D6 验收**：`[ ]` 一次读有结果 `[ ]` BT-001,3 落盘

### D7 · 写（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 读 `BluetoothGatt.writeCharacteristic` 参考页，并看 Punch Through 的 Writing 一节 | 能说出 WRITE_TYPE_DEFAULT 会等回调 |
| 0:25–1:40 | 75 min | `bt-d7-write/`：只写一个字节，等 `onCharacteristicWrite` | 不连续写第二次 |
| 1:40–2:20 | 40 min | 写 `BT-001,8`，默答 BT13 | 不看卡 |
| 2:20–3:00 | 40 min | 没有可写特征时，屏幕要写出「没有」，而不是假成功 | 不改设备固件 |

**D7 验收**：`[ ]` 写回调出现或明确没有可写特征 `[ ]` BT-001,8 落盘

### D8 · 通知（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:25 | 25 min | 读 Transfer BLE data 的 Receive GATT notifications | 能说出还要写描述符 |
| 0:25–1:40 | 75 min | `bt-d8-notify/`：打开通知，把变化打在屏幕上 | 不另写业务特征 |
| 1:40–2:20 | 40 min | 写 `BT-001,9`，默答 BT14 | 不看卡 |
| 2:20–3:00 | 40 min | 设备不推送时，验收停在描述符写完 | 不改成轮询 read |

**D8 验收**：`[ ]` 描述符写入有状态 `[ ]` BT-001,9 落盘

### D9 · 经典蓝牙对照（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:30 | 30 min | 读 Connect Bluetooth devices 的客户端步骤 | 能说出 connect() 会阻塞 |
| 0:30–1:20 | 50 min | `bt-d9-classic/` | 工程里没有 socket.connect |
| 1:20–2:00 | 40 min | 写 `BT-001,4`，默答 BT9 | 不看卡 |
| 2:00–3:00 | 60 min | 对照 D6–D8，口头讲 BLE 实战停在 GATT | 不新建传输 Demo |

**D9 验收**：`[ ]` 未打开 socket `[ ]` BT-001,4 落盘

### D10 · 默答（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–2:30 | 150 min | 遮住答案，口头过 BT1–BT14、BT16 | 每题能讲场景 |
| 2:30–3:00 | 30 min | 答歪的原句写入错题库 | 不编造没说过的坑 |

**D10 验收**：`[ ]` 必会题开口过 `[ ]` 答歪的已记下

### D11 · 复跑（3 h）

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–1:30 | 90 min | 从错题库挑 1 题复跑相关 Demo。错题库仍空则复跑 bt-d7-write | 行为与当天相同 |
| 1:30–2:10 | 40 min | 默答选做 BT10、BT15 | 不看卡 |
| 2:10–3:00 | 50 min | 仍说不顺的一句留在错题库 | 不改卡片编号 |

**D11 验收**：`[ ]` 复跑 1 个 Demo `[ ]` BT10、BT15 开口过
