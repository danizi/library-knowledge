# 蓝牙面试题库

> 用途：只覆盖本库这一条实战。权限与开关、BLE 扫描、一次 GATT 读，外加经典蓝牙对照。  
> 原则：场景题。写不出官方文档出处的题不收。  
> 题号 **BT1–**。不要和 ACT 面试题库的 B 档混用。

和本库学习闭环配合：[BT学习计划](BT学习计划.md)。  
自己栽过的题进 [BT错题库](BT错题库.md)。那一页现在是空的。

### 按天刷题

| 天 | 必刷题号 |
|----|----------|
| D0 | BT16 |
| D1 | BT1 BT2 |
| D2 | BT3 BT4 |
| D3 | BT5 BT6 |
| D4 | BT11 |
| D5 | BT12 |
| D6 | BT7 BT8 |
| D7 | BT13 |
| D8 | BT14 |
| D9 | BT9 |
| D10 | BT1–BT14、BT16 |
| D11 | BT10 BT15（选做）+ [错题库](BT错题库.md) |

---

## 必会

### BT1

场景：产品要求在没有蓝牙硬件的机器上隐藏入口。你怎么判断？

要说的那句：用 `BluetoothManager.getAdapter()`。返回 null，就是这台设备不支持蓝牙。

参考：[Set up Bluetooth](https://developer.android.com/develop/connectivity/bluetooth/setup)

### BT2

场景：用户手机支持蓝牙，但无线电关着。应用能不能自己打开？

要说的那句：先 `isEnabled()`。false 就发 `ACTION_REQUEST_ENABLE`，让系统弹框。用户同意，结果是 `RESULT_OK`；拒绝或失败，是 `RESULT_CANCELED`。

参考：[Set up Bluetooth](https://developer.android.com/develop/connectivity/bluetooth/setup)

### BT3

场景：应用 target 为 Android 12 或更高，要搜索附近的 BLE 设备，还要连已经配对的设备。清单里的权限够不够？

要说的那句：搜索声明 `BLUETOOTH_SCAN`，连接已配对设备声明 `BLUETOOTH_CONNECT`。这两个是运行时权限，必须再向用户请求，系统才会弹出附近设备的授权。

参考：[Bluetooth permissions](https://developer.android.com/develop/connectivity/bluetooth/bt-permissions)

### BT4

场景：扫描只为了连接设备，不用扫描结果推算用户位置。清单怎么写？

要说的那句：给 `BLUETOOTH_SCAN` 加上 `neverForLocation`。位置没有别的用途时，把 `ACCESS_FINE_LOCATION` 的 `maxSdkVersion` 设为 30。旧的 `BLUETOOTH` 和 `BLUETOOTH_ADMIN` 也设 `maxSdkVersion="30"`。`neverForLocation` 会让一部分信标不出现在结果里。

参考：[Bluetooth permissions](https://developer.android.com/develop/connectivity/bluetooth/bt-permissions)

### BT5

场景：扫描页一直转，直到用户离开。面试官问耗电。

要说的那句：`startScan()` 的结果从 `ScanCallback` 来。找到目标就 `stopScan()`，并且要设时间上限。不要循环扫描。

参考：[Find BLE devices](https://developer.android.com/develop/connectivity/bluetooth/ble/find-ble-devices)

### BT6

场景：蓝牙关着，或者你想同一时刻既扫 BLE 又扫经典设备。

要说的那句：蓝牙没开时，`getBluetoothLeScanner()` 返回 null。一次只能扫 BLE，或只能扫经典，不能同时扫两种。

参考：[Find BLE devices](https://developer.android.com/develop/connectivity/bluetooth/ble/find-ble-devices)

### BT7

场景：已经拿到外围设备的地址。读数目前面要做什么？

要说的那句：`connectGatt(context, false, callback)`。false 表示现在就连，不是等设备以后出现再自动连。`onConnectionStateChange` 到 `STATE_CONNECTED` 后调用 `discoverServices()`。`onServicesDiscovered` 的 status 为 `GATT_SUCCESS` 才能 `getServices()`。地址非法时 `getRemoteDevice()` 抛 `IllegalArgumentException`。不用了调用 `close()`。

参考：[Connect to a GATT server](https://developer.android.com/develop/connectivity/bluetooth/ble/connect-gatt-server)、[Transfer BLE data](https://developer.android.com/develop/connectivity/bluetooth/ble/transfer-ble-data)

### BT8

场景：`readCharacteristic()` 下一行就去读返回值，界面是空的。

要说的那句：`readCharacteristic()` 是异步的。字节在 `onCharacteristicRead` 里，而且 status 要是 `GATT_SUCCESS`。

参考：[Transfer BLE data](https://developer.android.com/develop/connectivity/bluetooth/ble/transfer-ble-data)

---

### BT11

场景：房间里 BLE 设备很多，你这次只想看某个广播名，并且希望扫描更积极。

要说的那句：用 `startScan(filters, settings, callback)`。`ScanFilter` 限制设备，`ScanSettings` 的 `SCAN_MODE_LOW_LATENCY` 是低延迟模式。到时或找到后仍然 `stopScan()`。这还是 BLE 扫描，不会同时扫经典蓝牙。

参考：[Find BLE devices](https://developer.android.com/develop/connectivity/bluetooth/ble/find-ble-devices)、[ScanSettings.SCAN_MODE_LOW_LATENCY](https://developer.android.com/reference/android/bluetooth/le/ScanSettings#SCAN_MODE_LOW_LATENCY)

### BT12

场景：`connectGatt` 一返回就去读特征，界面没有服务。

要说的那句：返回值只是 `BluetoothGatt` 对象。`newState` 变成 `STATE_CONNECTED` 之后调用 `discoverServices()`。`onServicesDiscovered` 的 status 为 `GATT_SUCCESS` 才能 `getServices()`。不用了 `close()`。

参考：[Connect to a GATT server](https://developer.android.com/develop/connectivity/bluetooth/ble/connect-gatt-server)、[Transfer BLE data](https://developer.android.com/develop/connectivity/bluetooth/ble/transfer-ble-data)

### BT13

场景：写了一个字节，方法返回了，就告诉用户「设备已保存」。

要说的那句：API 33 起调用 `writeCharacteristic(characteristic, value, writeType)`。`WRITE_TYPE_DEFAULT` 的结果在 `onCharacteristicWrite`，status 为 `GATT_SUCCESS` 才算完成。这个调用需要 `BLUETOOTH_CONNECT`。方法返回值只表示请求有没有交出去。

参考：[BluetoothGatt.writeCharacteristic](https://developer.android.com/reference/android/bluetooth/BluetoothGatt#writeCharacteristic(android.bluetooth.BluetoothGattCharacteristic,%20byte[],%20int))

### BT14

场景：心率带会自己更新数值，你不想每秒读一次。

要说的那句：`setCharacteristicNotification(characteristic, true)` 之后，还要把 Client Characteristic Configuration 描述符写成 `ENABLE_NOTIFICATION_VALUE`。变化从 `onCharacteristicChanged` 来。

参考：[Transfer BLE data](https://developer.android.com/develop/connectivity/bluetooth/ble/transfer-ble-data)

### BT16

场景：清单里已经有 `BLUETOOTH_SCAN`，安装完直接扫描，结果被系统拒绝。

要说的那句：清单是声明。Android 12 起 `BLUETOOTH_SCAN` 和 `BLUETOOTH_CONNECT` 还要运行时请求。`bluetooth_le` 的 `required="true"` 还会让 Play 对没有该硬件的设备隐藏应用。

参考：[Bluetooth permissions](https://developer.android.com/develop/connectivity/bluetooth/bt-permissions)

---

### BT9

场景：有人要求用经典蓝牙传文件。这条学习路线做不做？

要说的那句：经典蓝牙客户端用 `createRfcommSocketToServiceRecord(uuid)` 拿到 `BluetoothSocket`，再 `connect()`。`connect()` 会阻塞，不能放在界面线程。本库不写这条传输。BLE 的实战停在 GATT 的读、写和通知。

参考：[Connect Bluetooth devices](https://developer.android.com/develop/connectivity/bluetooth/connect-bluetooth-devices)、[BluetoothSocket](https://developer.android.com/reference/android/bluetooth/BluetoothSocket)

---

## 选做

### BT10

场景：把 `android.hardware.bluetooth_le` 的 `required` 写成 true，上架后有人说搜不到应用。

要说的那句：`required="true"` 时，Play 会把应用对没有该硬件的设备隐藏。能缺蓝牙仍可运行的应用应写 `required="false"`，再用 `PackageManager.hasSystemFeature()` 检查。

参考：[Bluetooth permissions](https://developer.android.com/develop/connectivity/bluetooth/bt-permissions)

### BT15

场景：发现服务成功后，不等回调就连续读、再写。

要说的那句：读、写、描述符写入都是异步的。Punch Through 的实战建议是上一次 `onCharacteristicRead` 或 `onCharacteristicWrite` 回来之前，不要发下一次。

参考：[Punch Through：Don’t Flood the Pipeline](https://punchthrough.com/android-ble-guide/)、[Transfer BLE data](https://developer.android.com/develop/connectivity/bluetooth/ble/transfer-ble-data)
