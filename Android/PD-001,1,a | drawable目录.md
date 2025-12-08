可绘制对象文件目录可放置如下类型
- `位图`（格式包括.png/.jpg/.webp/.gif/.9.png），编译后对应数据类型是BitmapDrawable。
- `点九图`（格式.9.png），编译后对应数据类型是NinePatch。
- `图层列表`，编译后对应数据类型是LayerDrawable。
- `状态列表`，编译后对应数据类型是StateListDrawable。
- `级别列表`，编译后对应数据类型是LevelListDrawable。
- `转化可绘制对象`，TransitionDrawable。
- `插入可绘制对象`，InsetDrawable。
- `裁剪可绘制对象`，ClipDrawable。
- `缩放可绘制对象`，ScaleDrawable。
- `形状可绘制对象`，GradientDrawable。

使用多的还是位图、点九图、状态列表、形状可绘制对象。

[官方文档-drawable](https://developer.android.com/guide/topics/resources/drawable-resource?hl=zh-cn)
