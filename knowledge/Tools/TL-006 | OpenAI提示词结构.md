# OpenAI提示词结构

**问：**
按 OpenAI 官方文档，一条 developer 消息该按哪几段写、按什么顺序？

**答：**
官方给了四个小节，通常按这个顺序：**Identity（你是谁、什么风格）→ Instructions（该做什么、绝不该做什么）→ Examples（输入与期望输出成对）→ Context（模型本来不知道的资料）**。Context 放最后，因为它每次请求都可能换，放尾部才方便替换。缓存要的是反过来：反复复用的内容放最前。实现上它对应两个位置：`instructions` 参数（或 `role: "developer"` 的消息）承载这四段，`input` / `role: "user"` 只给本次的具体输入。优先级是 developer 高于 user——文档的比喻是：developer 消息像函数定义，user 消息像传进来的参数。分段用 Markdown 标题（`# Identity`、`# Instructions`）配 XML 标签（`<user_query>`、`<assistant_response>`、`<product_review id="example-1">`）划边界。

一句话：Identity → Instructions → Examples → Context，四段写进 developer，user 只放这一次的输入。

**例 / 类比：**
- 例子（官方代码生成 prompt，只用了三段）：

  ```
  # Identity
  You are coding assistant that helps enforce the use of snake case
  variables in JavaScript code, and writing code that will run in
  Internet Explorer version 6.

  # Instructions
  * When defining variables, use snake case names (e.g. my_variable)
    instead of camel case names (e.g. myVariable).
  * To support old browsers, declare variables using the older
    "var" keyword.
  * Do not give responses with Markdown formatting, just return
    the code as requested.

  # Examples
  <user_query>How do I declare a string variable for a first name?</user_query>
  <assistant_response>var first_name = "Anna";</assistant_response>
  ```

- 类比：developer 消息是「岗位说明书」，user 消息是「今天这张工单」。说明书一次写好反复用，工单天天换。

**易错：**
- 以为四段顺序可随便调。Context 放前面会挡住后面真正会变的指令，也让 prompt 缓存失效。
- 把规则写进 user 消息，指望它压过 developer。优先级是反的，user 覆盖不了 developer。
- 只写 Instructions 不给 Examples。官方把 few-shot 当成和指令并列的一等手段，格式类任务里例子收益远大于形容词。
- Identity 只写「你是专家」。要写到「什么身份 + 什么风格 + 管什么范围」，否则它只影响语气、不影响行为。
- 把要复用的大段内容放在 prompt 中间。要复用的放最前（instructions 开头），才吃到 prompt caching 的便宜。

**相关：**
- [TL-005 | Anthropic提示词结构](<./TL-005 | Anthropic提示词结构.md>) — 同样按「角色 + 指令 + 例子 + 上下文」分块，但用 system + XML，且没有给这套结构命名

**参考：**
- [OpenAI — Prompt engineering](https://platform.openai.com/docs/guides/prompt-engineering) — 四段结构、few-shot、developer/user 优先级、XML 与 Markdown 混用都在这一页
