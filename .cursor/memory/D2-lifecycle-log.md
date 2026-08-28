# D2 Logcat · LIFE · 2026-08-27

Filter：`tag:LIFE`（Studio Logcat 搜 `LIFE`）

## 1 冷启动 Main
MainActivity onCreate → onStart → onResume

## 2 Main 打开 Second（A2）
Main onPause → Second onCreate → onStart → onResume → Main onStop

## 3 Second 返回
Second onPause → Main onRestart → onStart → onResume → Second onStop → onDestroy

## 4 Home 再回 App
Home：Main onPause → onStop（无 onDestroy）  
回 App：Main onRestart → onStart → onResume

## 旋转（配置变更）
Main onPause → onStop → onDestroy → onCreate → onStart → onResume  
（横竖来回各走一遍完整销毁重建）
