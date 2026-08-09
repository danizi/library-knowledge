# values 目录

**问：**
`res/values` 里通常放什么？和布局/图片资源怎么分工？

**答：**
放「常量型」资源：`strings`、`colors`、`dimens`、`styles`、`themes`、有时 `attrs`/`ids`。  
布局描述结构，drawable 描述皮，values 描述可复用的名与值——改一处，引用处一起变。

一句话：values = 给颜色/字串/尺寸/样式起名的地方。

**例 / 类比：**
- 例子：`@string/app_name`、`@color/primary`、`@dimen/page_padding`。
- 类比：设计令牌柜——别在每个房间墙上直接写死色号。

**易错：**
- 硬编码颜色/字符串进布局，后期改不动（见主题重构卡）。
- styles（控件样式）和 themes（窗口/主题）职责搅混。

**相关：**
- [PD-001,1,b,1 | 记一次主题样式重构](./PD-001,1,b,1%20%7C%20记一次主题样式重构.md)
- [PD-001,1 | res资源文件夹](./PD-001,1%20%7C%20res资源文件夹.md)

**参考：**
- [资源类型概览](https://developer.android.com/guide/topics/resources/available-resources)
