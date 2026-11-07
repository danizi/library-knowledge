# Anthropic提示词结构

**问：**
按 Anthropic 官方文档，写 Claude 提示词该按什么结构组织？

**答：**
用「system 定角色 + 正文用 XML 标签切块」。system 放角色，也可以放稳定的行为规则（例：你是专注 Python 的编程助手）；正文用 `<instructions>`、`<context>`、`<examples>` 这类自成对的标签把不同类型的内容隔开，让模型分清哪段是指令、哪段是资料、哪段是范例。例子要给 3～5 个，并用标签包住，否则模型会把它当正文续写。如果贴长文档（2 万 token 以上），文档放**最前**、问题和指令放**最后**，多份文档各自包一层 `<document>`；并要求它先引用原文再作答。最后一条：每条禁令后面补一句「为什么」，Claude 会从原因里泛化，比只写「不要做 X」管用。

一句话：角色放 system，其余用 XML 标签分块，例子 3～5 个，长文档在前、问题在后。

**例 / 类比：**
- 例子（官方多文档结构）：

  ```xml
  <documents>
    <document index="1">
      <source>annual_report_2023.pdf</source>
      <document_content>{{ANNUAL_REPORT}}</document_content>
    </document>
  </documents>

  对照年报与竞品分析，找出战略优势，给出 Q3 的三条重点。
  ```

- 类比：像给新同事的交接文件——先摆全部资料，最后写「你现在要做什么」，中间用文件夹标签隔开，例子就是「上次别人是这么做的」。

**易错：**
- 以为 Anthropic 有一套官方命名的「四段式」。它只给了 best practices（clear and direct / add context / use examples / XML tags / role / long context），没有取名字的模板。别把社区起的名字安到官方头上。
- 把大段业务数据塞进 system。system 适合角色和稳定规则；每次都不一样的数据放 user 消息里的 `<context>` 标签。
- 例子只给一个。官方建议 3～5 个，且要**多样**，否则模型会学到你没想要的规律。
- 只写「NEVER use ellipses」，不写理由。补上「这段会被 TTS 朗读，它读不出省略号」，模型才知道边界在哪。
- 标签名每次换（这次 `<docs>` 下次 `<document>`）。同名同义，别让模型猜。

**相关：**
- [TL-006 | OpenAI提示词结构](<./TL-006 | OpenAI提示词结构.md>) — 另一家的官方结构，同样是「分块 + 例子」，但命名和优先级规则不同

**参考：**
- [Anthropic — Prompting best practices](https://docs.claude.com/en/docs/build-with-claude/prompt-engineering/overview) — 权威出处，随模型版本更新
- [Anthropic — Use XML tags](https://docs.claude.com/en/docs/build-with-claude/prompt-engineering/use-xml-tags) — 标签用法与多文档示例
