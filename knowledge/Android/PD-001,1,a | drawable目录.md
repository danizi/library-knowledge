# drawable 目录

**问：**
`res/drawable` 里都能放啥？日常最常用哪几类？

**答：**
可放位图、点九、图层/状态/级别列表、形状、裁剪缩放插入等多种 Drawable。  
真正高频：**位图、.9.png、状态列表（selector）、形状（shape/渐变）**。

一句话：drawable = 各种「可画到屏幕上的皮」；先精通图、selector、shape。

**例 / 类比：**
- 例子：按钮背景用 `selector` 切按下色；圆角渐变用 `shape`+`gradient`。
- 类比：化妆箱——粉饼（位图）、按场景换色盘（selector）、自制膏体（shape）。

**易错：**
- 大图乱塞不压缩。
- 状态列表 item 顺序反了导致永远匹配不到按下态。

**相关：**
- [PD-001,1,a,1 | 状态列表的使用](./PD-001,1,a,1%20%7C%20状态列表的使用.md)
- [PD-001,1,a,2 | View状态常量](./PD-001,1,a,2%20%7C%20View状态常量.md)
- [PD-001,1,a,3 | 形状可绘制对象](./PD-001,1,a,3%20%7C%20形状可绘制对象.md)

**参考：**
- [Drawable 资源](https://developer.android.com/guide/topics/resources/drawable-resource?hl=zh-cn)
