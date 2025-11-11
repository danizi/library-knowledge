<details open>
  <summary>卡片内容</summary>
  
  - 核心问题<br>
    1 Android中的资源文件定义是？<br>
    2 系统默认创建了资源文件，由于android设备碎片化严重那么系统又是怎么来做适配的？
    
  - 问题回答<br>
    定义是res/目录下的文件或目录，在res/目录下支持的文件和子目录请查看[官方文档-表 1. 项目 res/ 目录中支持的资源目录。](https://developer.android.com/guide/topics/resources/providing-resources?hl=zh-cn)。<br>

    碎片化严重系统通过文件限定符，然后根据情况选中对应的资源，若不存在则使用默认资源。[官方文档- 表 2. 配置限定符名称。](https://developer.android.com/guide/topics/resources/providing-resources?hl=zh-cn)
   
</details>

<details>
  <summary>关联卡片</summary>
</details>

<details>
  <summary>参考文件</summary>

  [官方文档 - App resources](https://developer.android.com/guide/topics/resources/providing-resources?hl=zh-cn)
</details>




