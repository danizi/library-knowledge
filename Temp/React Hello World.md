使用VSCode编写React Hello World
React支持`JavaScript（JS）`和`TypeScript（TS）`两种主流开发方式，下面是VS code的完成执行步骤

环境搭建，涵盖传统的`Create React App`和现代`Vite`（更快推荐新手使用）两种构建工具。

前置准备
- 1 安装`VSCode`:前往[VS Code 官网](https://code.visualstudio.com/)
- 2 安装`Node.js`:前往[Node.js 官网](https://nodejs.org/zh-cn)下载 LTS 版本（建议 16.x 及以上）
  - 按照验证打开终端，输入`node -v`和`npm -v`，能显示版本说明成功
- 3 安装React插件
  - 左侧扩展商店（方块图标）-> 搜索如下插件
    - ES7+ React/Redux/React-Native snippets：提供 React 代码片段（如 rfc 快速生成函数组件）。
    - ESLint：代码语法检查。
    - Prettier：代码格式化（可选）。
    - TypeScript React code snippets（TS 版本需安装）：TS + React 代码片段。

<details>
  <summary> 搭建方式一:Vite+React(javaScript版本，推荐)</summary>
  
  - **步骤1：创建 Vite + React 项目**
    - (1) 打开VS Code终端，进入项目目录，例如桌面
      ```bash
      cd Desktop
      ```
    - (2) 执行创建命令，例如创建项目名称为react-hello-world-js
      ```bash
      nmp create react-hello-world-js -- template react
      ```
    - (3) 进入项目目录并安装依赖：
      ```
      cd react-hello-world-js
      npm install
      ```
  - **步骤2：打开项目并编写react代码**
    - (1) 在VS Code中打开create react-hello-world-js
      ```bash
      点击VsCode -> 文件-> 打开文件夹 -> 选择create react-hello-world-js
      ```
    - (2) 修改核心代码文件,找到src/App.jsx文件，替换原来代码为最简单的Hello World
      ```JavaScript
      // src/App.jsx（JavaScript 版本）
      function App() {
        return (
          <div className="App">
            <h1>Hello World! 🚀</h1>
            <p>这是 React + JavaScript 的第一个 Hello World</p>
          </div>
        );
      }
      export default App;
      ```
  - **步骤3：运行项目**<br>
    > **注意**：
    > 1 cd到工程目录
    > 2 vscode 右边若报错请定位到+的下拉图标找到Command Prompt，依次按照上面步骤执行。
    - (1) 在VS Code终端执行，点击`Local` 地址，跳转到浏览器。
      ```bash
      npm run dev
  
      若成功返回如下内容，直接找Local复制链接即可访问
      > react-hello-world-js@0.0.0 dev
      > vite
  
  
      VITE v7.3.0  ready in 1547 ms
    
      ➜  Local:   http://localhost:5173/
      ➜  Network: use --host to expose
      ➜  press h + enter to show help
      ```
</details>

<details>
  <summary>搭建方式二:Vite+React(TypeScript版本，工业级别推荐)，后续补充)</summary>

</details>

<details>
  <summary>搭建方式三:Create React App（CRA）+ React（经典方式，略慢暂不推荐），后续补充</summary>
</details>
