# Android调用

**问：** Android 业务问答自己做检索吗？

> **一句话：** Android 只发送一次 `POST /ask`。检索和模型调用留在 `ai-d8-ask`。

**例：** `ai-d11-android` 点「提问」。界面显示回答、`cold.md` 和毫秒数。

<details>
<summary>答</summary>

`ai-d8-ask` 先在电脑上开着。
模拟器里的 `10.0.2.2` 指向这台电脑。
按钮启动一次后台请求。请求方法是 POST。
请求体只有问题。请求体里没有笔记。
服务返回 JSON。里面有 `answer`、`filename`、`elapsed_ms`。
界面在主线程上把这三项显示出来。
清单声明 `INTERNET`。没有这行声明，系统拒绝这次连接。

</details>

<details>
<summary>易错</summary>

- 不要在 Android 工程里再写一套查笔记的代码。

</details>

<details>
<summary>相关</summary>

- [AI-001,11 | 三项返回](<./AI-001,11 | 三项返回.md>) — 界面显示的就是这三项
- [AI练习册](./AI练习册.md) — D11

</details>

<details>
<summary>参考</summary>

- [连接到网络](https://developer.android.com/training/basics/network-ops/connecting) — 应用访问网络

</details>
