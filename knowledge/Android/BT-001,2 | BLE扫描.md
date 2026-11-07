# BLE扫描

**问：** 为什么 BLE 扫描必须有时间上限，并且找到目标设备后必须停止？

> **一句话：** 为 BLE 扫描设置时间上限。找到目标设备后停止扫描。

**例：** `bt-d3-scan` 扫描约 10 秒。到时后调用 `stopScan()`。屏幕只列出设备地址。

<details>
<summary>答</summary>

调用 `BluetoothLeScanner.startScan()`。
扫描结果从 `ScanCallback.onScanResult` 到达。
蓝牙功能未打开时，`getBluetoothLeScanner()` 返回 null。
返回 null 时，没有扫描器。
扫描消耗电池电量。
找到目标设备后，调用 `stopScan()`。
为扫描设置一个时间上限。
不要循环调用 `startScan()`。
一次扫描只能扫描 BLE 设备。
一次扫描不能同时扫描经典蓝牙设备。

</details>

<details>
<summary>类比</summary>

用手电找到钥匙后，关闭手电。

</details>

<details>
<summary>易错</summary>

- 蓝牙功能未打开时，不要调用 `startScan()`。此时扫描器是 null。
- 不要在一次扫描中同时获取经典蓝牙设备和 BLE 设备。

</details>

<details>
<summary>相关</summary>

- [BT-001,1 | 蓝牙权限](<./BT-001,1 | 蓝牙权限.md>) — 没有 `BLUETOOTH_SCAN` 不能扫描
- [BT-001,6 | 扫描设置](<./BT-001,6 | 扫描设置.md>) — 过滤器和扫描模式是另两个参数

</details>

<details>
<summary>参考</summary>

- [Find BLE devices](https://developer.android.com/develop/connectivity/bluetooth/ble/find-ble-devices) — `startScan`、时间上限、扫描器为 null

</details>
