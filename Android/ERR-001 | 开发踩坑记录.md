<details>
  <summary> 改变窗口配置例如(修改语言、主题...)，fragment进行数据刷新 </summary>

  ```
  在onHiddenChanged方法中,操作数据更新，onResume 刷新不行。
  ```
</details>



<details>
  <summary>图片组件加载图片相关</summary>
  
  - 图片加载不失真处理
    - 图片控件宽或高固定值情况下。
      ```
      XML 中修改属性状态 android:scaleType="fitCenter"
      代码中 修改transform(FitCenter())
      ```
    - 图片控件自适应情况下。
</details>


<details>
  <summary>字符串资源加载崩溃</summary>

  - **背景**<br>
    项目采用模块化开发，新增了`模块B`，`模块A`**依赖**`模块B`，某场景下`模块B`出现资源找不到情况。<br>
    ```
      No static field move of type I in class Lcom/wondershare/pdfelement/pdftool/R$string; or its superclasses (declaration of 'com.wondershare.pdfelement.pdftool.R$string' appears in /data/app/~~mqyFGbdVOALTMPdWmlxQ_A==/com.wondershare.pdfelement-sckWyVQbFYIVmie8uC8Mfg==/base.apk!classes13.dex)
    ```
  - **原因**<br>
    `模块B`gradle文件中的namespace 与`模块A`一致导致此问题。<br>
    ```
    模块A中的Gradle
    anrdoid{
        namespace 'aaa.aaa.aaa'
    }
    ....
    
    模块B中的Gradle
    anrdoid{
        namespace 'aaa.aaa.aaa'
    }
    ....
    ```
  - **解决**
    ```
    修正模块B中的namespace
    anrdoid{
        namespace 'bbb.bbb.bbb'   
    }
    ```
</details>


<details>
  <summary>findViewById查找相同id的规则</summary>

  - **背景**<br>
    在一个视图中，有多种状态视图，其中A，B状态视图需要引入同一个布局，A隐藏B显示控件查找时优先获取A中控件导致B状态视图状态显示错误。<br>
  - **原因**<br>
    这是findviewById的规则若有两个相同的id优先选第一个。
  - **解决**<br>
    分别为两个相同视图引入include控件添加id，再查找这个id的view查找控件。<br>
    按照下面规则写法<br>
    ```
    // A.xml
    <include
       android:id="@+id/layout_a"
       layout="@layout/layout_usage_count" />
    // A.xml
    private val layoutA: View? = rootView.findViewById(R.id.layout_a)
    private val xx = layoutA.findViewById(R.id.xx)
    
    // B.xml
    <include
       android:id="@+id/layout_b"
       layout="@layout/layout_usage_count" />
    private val layoutB: View? = rootView.findViewById(R.id.layout_b)
    private val xx = layoutB.findViewById(R.id.xx)
    ```
</details>





