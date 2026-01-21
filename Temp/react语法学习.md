[react项目目录结构说明](https://www.runoob.com/react/react-project-intro.html)

- **react jsx**(javascript xml)
  -  JavaScript 的语法扩展，替代常规javascript来编写UI组件，用类html的方式描述组件结构行为。
  -  元素是构成 React 应用的最小单位，JSX 就是用来声明 React 当中的元素。
  - 优势有哪些
    - 执行高效：编译为javascript代码后经过优化，速度更快。
    - 类型安全：编译阶段可发下错误配置TypeScript效果更佳。
    - 编写便捷：UI结构清晰，嵌入javascript表达式更灵活。
    - 本质特性：非字符串/html,会被Babel(vite内置)编译为React.createElement调用。

- **jsx语法规则**
  - 必须闭合标签
    ```
    HTML 可以 <br>，但 JSX 必须 <br />（自闭合）。
    所有标签都要闭合：<div></div>。
    ```
  - class → className
    ```
    因为 class 是 JavaScript 关键字，用 className 代替。
    <div className="container">内容</div>
    ```
  - htmlFor（标签内）
    ```
    <label for="id"> → <label htmlFor="id">
    ```
  - 大写开头组件
    ```
    <Card />  {/* 正确，自定义组件 */}
    <card />  {/* 错误，会被当成 <card> HTML 标签 */}
    ```
  - 注释:用 {/* 注释内容 */}。
  - 根元素：return只能有一个根节点
  - 表达式嵌入：任何有效的 JS 表达式都可以放进 {}：
  - 条件渲染：用三元运算符或 &&：
  - 列表渲染：用 map() 渲染数组，必须给每个元素加 key 属性
    ```
    const items = [
      { id: 1, name: '苹果' },
      { id: 2, name: '香蕉' },
      { id: 3, name: '橙子' },
    ];
    
    return (
      <ul>
        {items.map(item => (
          <li key={item.id}>  {/* key 必须唯一且稳定！ */}
            {item.name}
          </li>
        ))}
      </ul>
    );
    ```
- 样式
  - 内联样式
    ```
    <h2 style={{ color: 'blue', marginBottom: '10px' }}>
      内联样式示例
    </h2>
    ```
  - Css Modules
    ```
    Vite 默认支持 CSS Modules，避免全局污染。
    创建 src/App.css（已存在）或新文件 src/styles/Card.module.css：
    
    ```


    https://www.runoob.com/react/react-tutorial.html
    
