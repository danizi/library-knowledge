# GATT读写

**问：** 已有 BLE 设备地址时，读取一个特征之前必须完成哪些步骤？

> **一句话：** 先连接。再发现服务。然后读取特征。读取完成后关闭连接。

**例：** `bt-d6-read` 使用扫描得到的地址。它只读取一个带读取属性的特征。离开页面时调用 `close()`。

<details>
<summary>答</summary>

外围设备是 GATT 服务器。
应用是 GATT 客户端。
调用 `connectGatt(context, false, callback)`。
参数 false 表示现在连接。
参数 false 不表示等待设备以后出现再连接。
`onConnectionStateChange` 的新状态是 `STATE_CONNECTED` 时，调用 `discoverServices()`。
`onServicesDiscovered` 的 status 是 `GATT_SUCCESS` 时，调用 `getServices()`。
对支持读取的特征调用 `readCharacteristic()`。
`readCharacteristic()` 是异步调用。
读取结果在 `onCharacteristicRead` 中。
`onCharacteristicRead` 的 status 是 `GATT_SUCCESS` 时，读取成功。
地址不合法时，`getRemoteDevice()` 抛出 `IllegalArgumentException`。
不再使用连接时，对 `BluetoothGatt` 调用 `close()`。

</details>

<details>
<summary>类比</summary>

先接通总机。再询问分机列表。然后向一个分机取数据。

</details>

<details>
<summary>易错</summary>

- 不要在 `readCharacteristic()` 的下一行显示结果。结果在回调中。
- 不要在 `discoverServices()` 之前读取特征。服务列表在发现成功之后才存在。

</details>

<details>
<summary>相关</summary>

- [BT-001,7 | 连接与关闭](<./BT-001,7 | 连接与关闭.md>) — 连接和发现服务
- [BT-001,8 | 写特征](<./BT-001,8 | 写特征.md>) — 写入是另一步

</details>

<details>
<summary>参考</summary>

- [Connect to a GATT server](https://developer.android.com/develop/connectivity/bluetooth/ble/connect-gatt-server) — `connectGatt`、`STATE_CONNECTED`、`close`
- [Transfer BLE data](https://developer.android.com/develop/connectivity/bluetooth/ble/transfer-ble-data) — `discoverServices`、`readCharacteristic`、`onCharacteristicRead`

</details>
