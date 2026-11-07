# BLE扫描

**问：**
BLE 扫描为什么必须限时，并且找到目标就停？

**答：**
调用 `BluetoothLeScanner.startScan()`，设备从 `ScanCallback.onScanResult` 送来。蓝牙没有打开时，`getBluetoothLeScanner()` 返回 null，没有扫描器可用。扫描耗电：找到要的设备就 `stopScan()`，还要定一个时间上限，不要循环扫。同一次也不能又扫 BLE 又扫经典蓝牙。

一句话：限时扫描，找到就停；蓝牙关着就没有 scanner，也不能和经典扫描同时进行。

**例 / 类比：**
- 例子：bt-d3 按下后扫描约 10 秒，到点调用 `stopScan()`，屏幕上只积累设备地址。
- 类比：用手电找钥匙，照到就关掉，不要整晚照着院子。

**易错：**
- 蓝牙关着仍去 `startScan()`。这时扫描器是 null。
- 想在一次扫描里同时拿到经典设备和 BLE 设备。文档写明两种扫描不能同时做。

**相关：**
- [BT-001,1 | 蓝牙权限](<./BT-001,1 | 蓝牙权限.md>) — 没有 BLUETOOTH_SCAN 不能扫
- [BT-001,3 | GATT读写](<./BT-001,3 | GATT读写.md>) — 扫描只拿到地址，读数要再连接

**参考：**
- [Find BLE devices](https://developer.android.com/develop/connectivity/bluetooth/ble/find-ble-devices) — startScan、10 秒上限、scanner 为 null、不能同时扫两种
