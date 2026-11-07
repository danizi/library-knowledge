# OpenAI提示词结构

**问：** 按 OpenAI 的文档，一条 developer 消息按什么顺序写？

> **一句话：** 顺序是 Identity、Instructions、Examples、Context。这四段写在 developer 消息里。user 消息只放这一次的输入。

**例：** 官方的代码生成示例只用了前三段。Identity 规定身份。Instructions 规定变量名和 `var`。Examples 给出一对输入和输出。

```text
# Identity
You are coding assistant that helps enforce the use of snake case variables in JavaScript code, and writing code that will run in Internet Explorer version 6.

# Instructions
When defining variables, use snake case names (e.g. my_variable) instead of camel case names (e.g. myVariable).
To support old browsers, declare variables using the older "var" keyword.
Do not give responses with Markdown formatting, just return the code as requested.

# Examples
<user_query>How do I declare a string variable for a first name?</user_query>
<assistant_response>var first_name = "Anna";</assistant_response>
```

<details>
<summary>答</summary>

官方给出四个小节。
通常的顺序是 Identity、Instructions、Examples、Context。
Identity 写你是谁，以及使用什么风格。
Instructions 写该做什么，以及绝不该做什么。
Examples 把输入和期望输出配成一对。
Context 放模型本来不知道的资料。
Context 放在最后。它每次请求都可能更换，放在尾部便于替换。
反复复用的内容放在最前面。这样才利于缓存。
这四段放在 `instructions` 参数里，或者放在 `role: "developer"` 的消息里。
`input` 或 `role: "user"` 只放这一次的具体输入。
developer 消息的优先级高于 user 消息。
分段使用 Markdown 标题，例如 `# Identity` 和 `# Instructions`。
再用 XML 标签划定边界，例如 `user_query`、`assistant_response` 和 `product_review`。

</details>

<details>
<summary>类比</summary>

developer 消息是岗位说明书。user 消息是今天这张工单。说明书写一次，反复使用。工单每天更换。
文档把 developer 消息比作函数定义，把 user 消息比作传进来的参数。

</details>

<details>
<summary>易错</summary>

- 不要随意调换四段的顺序。Context 放在前面会挡住后面才会变化的指令。反复复用的内容不在最前面时，prompt 缓存用不上。
- 不要把规则写进 user 消息，并指望它压过 developer。优先级相反。user 覆盖不了 developer。
- 不要只写 Instructions 而不给 Examples。官方把 few-shot 和指令并列。格式类任务里，例子的收益大于形容词。
- 不要把 Identity 只写成「你是专家」。要写身份、风格和负责的范围。否则它主要影响语气，不影响行为。
- 不要把要复用的大段内容放在提示词中间。要复用的内容放在 instructions 的开头，才能用上 prompt caching。

</details>

<details>
<summary>相关</summary>

- [TL-005 | Anthropic提示词结构](<./TL-005 | Anthropic提示词结构.md>) — 同样分成角色、指令、例子和上下文。它使用 system 加 XML，并且没有给这套结构起名

</details>

<details>
<summary>参考</summary>

- [OpenAI — Prompt engineering](https://platform.openai.com/docs/guides/prompt-engineering) — 四段结构、few-shot、developer 和 user 的优先级、XML 与 Markdown 混用都在这一页

</details>
