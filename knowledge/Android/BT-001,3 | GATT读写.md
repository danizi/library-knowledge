# GATT读写

**问：**
拿到 BLE 设备地址之后，读一个特征之前必须经过哪几步？

**答：**
外围设备是 GATT 服务器，应用是客户端。`connectGatt(context, false, callback)` 里的 false 表示现在就连，不是等它以后出现再自动连。`onConnectionStateChange` 变成 `STATE_CONNECTED` 之后调用 `discoverServices()`。`onServicesDiscovered` 的 status 是 `GATT_SUCCESS`，才能 `getServices()`，再对支持读取的特征调用 `readCharacteristic()`。这次调用是异步的，成功的数据在 `onCharacteristicRead` 里，而且 status 也要是 `GATT_SUCCESS`。地址不合法时 `getRemoteDevice()` 抛 `IllegalArgumentException`。不用时对 `BluetoothGatt` 调用 `close()`。

一句话：先连接，再发现服务，然后才读；读是异步的，用完要 close。

**例 / 类比：**
- 例子：bt-d6-read 把扫描看到的地址填进去，连上后只读一个带读取属性的特征，离开页面时 close。
- 类比：先接通总机，问清有哪些分机，再打给某一部分机要一份资料。

**易错：**
- `readCharacteristic()` 的下一行就显示结果。结果要等回调。
- 还没 `discoverServices()` 就读。服务列表是发现成功之后才有的。

**相关：**
- [BT-001,7 | 连接与关闭](<./BT-001,7 | 连接与关闭.md>) — 读之前的连接和发现服务
- [BT-001,8 | 写特征](<./BT-001,8 | 写特征.md>) — 写入是下一天，不和这次读绑在一起

**参考：**
- [Connect to a GATT server](https://developer.android.com/develop/connectivity/bluetooth/ble/connect-gatt-server) — connectGatt、STATE_CONNECTED、close
- [Transfer BLE data](https://developer.android.com/develop/connectivity/bluetooth/ble/transfer-ble-data) — discoverServices、readCharacteristic、onCharacteristicRead
