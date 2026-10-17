# 跑起 Vite React

> 类型：过程卡 · 目标：**冷启动可复现**  
> 状态：**已晋升 · 仍待审查**（未在本机冷启动验收）  
> 用法：为何选 Vite、环境「通」的标准，见 [R-001,a | 环境搭建](<./R-001,a | 环境搭建.md>)

## 元信息

| 项 | 填写 |
|----|------|
| 结果定义 | 浏览器打开本地地址能看到页面；改 `src/App.jsx` 的字，页面自己更新 |
| 耗时预估 | 15–30 分钟 |
| 环境前提 | 已安装 Node.js LTS（含 npm）；VS Code；网络能访问 npm registry |
| 关联概念卡 | R-001,a、R-001,c（链接在文首与文末） |
| 产物路径 | 任选空目录下的 `react-hello/`（不要放进本知识库） |
| 审查 | 仍待冷启动验收。脚手架参数以 [Vite 指南](https://vitejs.dev/guide/) 当时命令为准 |

---

<details open>
  <summary>1. 准备</summary>

  - [ ] Node 与 npm 有版本号
  - [ ] 工作目录：一个空文件夹，不要选 `library-knowledge`
  - 验证命令：

    ```bash
    node -v && npm -v
    ```

  - 两条都没有输出：先到 [Node.js](https://nodejs.org/zh-cn) 安装 LTS，重开终端再测。本卡不负责装 Node。

</details>

<details open>
  <summary>2. 步骤（按顺序执行）</summary>

  ### 步骤 1 · 用 Vite 的 React 模板建项目

  - 操作：在空目录执行。目录名用 `react-hello`。
  - 关键 / 命令：

    ```bash
    npm create vite@latest react-hello -- --template react
    cd react-hello
    npm install
    ```

  - 预期现象：出现 `react-hello/package.json` 和 `src/App.jsx`，`npm install` 结束且无 `ENOENT`。
  - 若 `create` 仍追问框架：选 React，再选 JavaScript。

  ### 步骤 2 · 跑起来并改一个字

  - 操作：启动开发服务器。看终端里的本地 URL（常见 `http://localhost:5173/`）。
  - 关键 / 命令：

    ```bash
    npm run dev
    ```

  - 用 VS Code 打开 `react-hello`，改 `src/App.jsx` 里任意可见文字，保存。
  - 预期现象：浏览器不用手点刷新，文字跟着变。终端不要关。

</details>

<details>
  <summary>3. 验收</summary>

  - [ ] `node -v` 与 `npm -v` 都有输出
  - [ ] 当前目录是 `react-hello`，不是它的上一级
  - [ ] `npm run dev` 打印本地 URL，浏览器打得开
  - [ ] 保存 `src/App.jsx` 后页面文字变化

</details>

<details>
  <summary>4. 故障排查</summary>

  | 现象 | 原因 | 处理 |
  |------|------|------|
  | `nmp: command not found` 或类似 | 把 `npm` 打成了 `nmp` | 改回 `npm` |
  | `npm install` 找不到 `package.json` | 还在上一级目录 | `cd react-hello` 后再装 |
  | 创建或安装一直超时 | registry 连不上 | 换可用的 npm 镜像后再 `npm install`；模板参数仍以 Vite 文档为准 |
  | 页面在，改了字不动 | 改的不是 `src/App.jsx`，或 dev 进程已停 | 对照 [R-001,c](<./R-001,c | 项目使用及说明.md>) 认入口；重启 `npm run dev` |

</details>

<details>
  <summary>5. 关联与参考</summary>

  - 概念卡：[R-001,a | 环境搭建](<./R-001,a | 环境搭建.md>) — 最小准备与为何用 Vite
  - 概念卡：[R-001,c | 项目使用及说明](<./R-001,c | 项目使用及说明.md>) — 目录各自干什么
  - 外部文档：[Vite 指南](https://vitejs.dev/guide/)
  - 外部文档：[Node.js](https://nodejs.org/zh-cn)

</details>
