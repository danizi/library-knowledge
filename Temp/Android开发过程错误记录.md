- 改变窗口配置例如(修改语言、主题...)，fragment进行数据刷新
  ```
  在onHiddenChanged方法中,操作数据更新，onResume 刷新不行。
  ```

- 图片组件加载图片相关
  - 图片加载不失真处理
    - 图片控件宽或高固定值情况下。
      ```
      XML 中修改属性状态 android:scaleType="fitCenter"
      代码中 修改transform(FitCenter())
      ```
    - 图片控件自适应情况下。
    
