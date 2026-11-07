# Anthropic提示词结构

**问：** 按 Anthropic 的文档，Claude 的提示词怎样分块？

> **一句话：** 角色放在 system。其余内容用 XML 标签分开。例子给 3 到 5 个。长文档放在前面，问题放在后面。

**例：** 多份文档各自包在 `document` 里。问题写在文档后面。

```xml
<documents>
  <document index="1">
    <source>annual_report_2023.pdf</source>
    <document_content>{{ANNUAL_REPORT}}</document_content>
  </document>
</documents>

对照年报与竞品分析，找出战略优势，给出 Q3 的三条重点。
```

<details>
<summary>答</summary>

system 放角色。
system 也可以放稳定的行为规则。
例如：你是专注 Python 的编程助手。
正文用成对的 XML 标签把不同类型的内容隔开。
这些标签包括 `instructions`、`context` 和 `examples`。
标签让模型分清哪一段是指令，哪一段是资料，哪一段是范例。
例子给 3 到 5 个。
用标签包住例子。
不包住时，模型会把例子当成正文继续写。
长文档在 2 万 token 以上时，把文档放在最前面。
把问题和指令放在最后。
多份文档各自包一层 `document`。
要求模型先引用原文，再作答。
每条禁令后面补一句原因。
Claude 会从原因里推广到相近情况。
只写「不要做某事」，效果比补上原因差。

</details>

<details>
<summary>类比</summary>

交接文件先摆出全部资料。最后写「你现在要做什么」。中间用文件夹标签隔开。例子是「上次别人是这样做的」。

</details>

<details>
<summary>易错</summary>

- 不要认为 Anthropic 有一套官方命名的「四段式」。文档给的是 best practices：clear and direct、add context、use examples、XML tags、role、long context。文档没有给这套做法起模板名。不要把社区起的名字安到官方头上。
- 不要把大段业务数据放进 system。system 适合角色和稳定规则。每次都不同的数据放在 user 消息的 `context` 标签里。
- 不要只给一个例子。官方建议给 3 到 5 个，而且要多样。例子太相似时，模型会学到你不想要的规律。
- 不要只写 “NEVER use ellipses” 而不写理由。补上「这段会被 TTS 朗读，它读不出省略号」之后，模型才知道边界在哪里。
- 不要每次更换标签名。这次用 `docs`，下次用 `document`，模型要猜测它们是不是同一件事。同一个意思使用同一个标签名。

</details>

<details>
<summary>相关</summary>

- [TL-006 | OpenAI提示词结构](<./TL-006 | OpenAI提示词结构.md>) — 另一家也分块并给例子。名称和优先级规则不同

</details>

<details>
<summary>参考</summary>

- [Anthropic — Prompting best practices](https://docs.claude.com/en/docs/build-with-claude/prompt-engineering/overview) — 出处。内容随模型版本更新
- [Anthropic — Use XML tags](https://docs.claude.com/en/docs/build-with-claude/prompt-engineering/use-xml-tags) — 标签用法和多文档示例

</details>
