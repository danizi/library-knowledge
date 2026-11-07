import {
  Callout,
  Card,
  CardBody,
  CardHeader,
  Grid,
  H1,
  H2,
  Link,
  Pill,
  Row,
  Stack,
  Table,
  Text,
  useCanvasState,
} from "cursor/canvas";

type ViewId = "steps" | "contrast" | "rules" | "source" | "pages";
type StepId = "ask" | "answer" | "gap" | "line";

const VIEWS: { id: ViewId; label: string }[] = [
  { id: "steps", label: "四步" },
  { id: "contrast", label: "对照" },
  { id: "source", label: "原样" },
  { id: "pages", label: "网页" },
  { id: "rules", label: "准则" },
];

const STEPS: {
  id: StepId;
  field: string;
  action: string;
  detail: string;
  sample: string;
}[] = [
  {
    id: "ask",
    field: "问",
    action: "选定一个概念",
    detail: "标题和「问」只装一件事。这句话遮住下文，仍然能拿来默答。",
    sample:
      "Activity 生命周期主路径是哪几段？看得见和可点差在哪？",
  },
  {
    id: "answer",
    field: "答",
    action: "用大白话讲",
    detail:
      "讲给聪明的外行：先说是什么，再说它和旁边概念的差别。例子或类比用来检查这一步有没有真懂。",
    sample:
      "onStart 是看得见，onResume 才点得了。打开第二个页面时，第一个先交出焦点，然后才从屏幕上消失。",
  },
  {
    id: "gap",
    field: "易错",
    action: "抓住卡壳",
    detail:
      "讲不清的点记下来，回出处核对，再改「答」。没有卡壳就删掉这段，不要为了格式编一条。",
    sample: "以为打开 B 时 A 立刻 Destroy。其实 A 通常只到 Stop。",
  },
  {
    id: "line",
    field: "一句话",
    action: "再压缩",
    detail: "把「答」压成一句。压不动，说明里面有两个概念，拆成两张卡。",
    sample: "先丢焦点（Pause）再看不见（Stop）；Start 可见、Resume 可点。",
  },
];

export default function FeynmanMethod() {
  const [view, setView] = useCanvasState<ViewId>("feynman-view", "steps");

  return (
    <Stack gap={20}>
      <Stack gap={6}>
        <H1>费曼学习法</H1>
        <Text tone="secondary">
          懂，就是盖上材料还能讲出来。四步是 Scott Young 写的；费曼留下的是讲解习惯。准则正文仍是
          .cursor/docs/费曼学习法.md，状态仍待审查。
        </Text>
      </Stack>

      <Row gap={8} wrap>
        {VIEWS.map((item) => (
          <Pill
            key={item.id}
            active={view === item.id}
            onClick={() => setView(item.id)}
          >
            {item.label}
          </Pill>
        ))}
      </Row>

      {view === "steps" && <StepsView />}
      {view === "contrast" && <ContrastView />}
      {view === "source" && <SourceView />}
      {view === "pages" && <PagesView />}
      {view === "rules" && <RulesView />}
    </Stack>
  );
}

function StepsView() {
  const [stepId, setStepId] = useCanvasState<StepId>("feynman-step", "ask");
  const step = STEPS.find((item) => item.id === stepId) ?? STEPS[0];

  return (
    <Stack gap={16}>
      <Callout tone="info" title="精髓">
        讲给聪明的外行听。卡壳的地方就是还没懂的地方。回去改讲解，再压成一句话。反复读笔记只会让你认得句子。
      </Callout>

      <Stack gap={8}>
        <H2>四步落到卡片的四个字段</H2>
        <Text tone="secondary">
          点一个字段，下面用 Activity 生命周期演示这一步写成什么样。
        </Text>
        <Row gap={8} wrap>
          {STEPS.map((item, index) => (
            <Pill
              key={item.id}
              active={item.id === step.id}
              onClick={() => setStepId(item.id)}
            >
              {index + 1} {item.field}
            </Pill>
          ))}
        </Row>
      </Stack>

      <Card>
        <CardHeader trailing={<Text size="small">{step.field}</Text>}>
          {step.action}
        </CardHeader>
        <CardBody>
          <Stack gap={10}>
            <Text>{step.detail}</Text>
            <Text weight="semibold">这一步在生命周期卡上</Text>
            <Text>{step.sample}</Text>
          </Stack>
        </CardBody>
      </Card>

      <Text size="small" tone="tertiary">
        「例 / 类比」检查第 2 步。最小例子对上现象，生活类比抓住差别。来源：费曼学习法.md，例子取自 ACT-001,1。
      </Text>
    </Stack>
  );
}

function ContrastView() {
  return (
    <Stack gap={12}>
      <H2>同一题的两种写法</H2>
      <Text tone="secondary">题目都是 Activity 生命周期。差别在盖上材料之后还能不能讲。</Text>
      <Grid columns={2} gap={16}>
        <Card>
          <CardHeader>名词齐了</CardHeader>
          <CardBody>
            <Stack gap={8}>
              <Text>
                生命周期见官方文档：onCreate、onStart、onResume、onPause、onStop、onDestroy。
              </Text>
              <Text tone="secondary">
                盖上之后，讲不出「看得见」和「点得了」的差别，也讲不出打开第二个页面时，第一个会不会被销毁。
              </Text>
            </Stack>
          </CardBody>
        </Card>
        <Card>
          <CardHeader>讲出来了</CardHeader>
          <CardBody>
            <Stack gap={8}>
              <Text weight="semibold">
                先丢焦点（Pause）再看不见（Stop）；Start 可见、Resume 可点。
              </Text>
              <Text>
                类比：开会时有人进门，你先停嘴，对方站定，你才离开会场。
              </Text>
              <Text tone="secondary">
                易错写真会讲错的点：以为打开 B 时 A 立刻 Destroy，其实 A 通常只到 Stop。
              </Text>
            </Stack>
          </CardBody>
        </Card>
      </Grid>
    </Stack>
  );
}

function SourceView() {
  return (
    <Stack gap={16}>
      <Callout tone="warning" title="两个人不要合成一个">
        费曼没有发表过「问 / 答 / 易错 / 一句话」这种学习卡。四步写在纸上的人是 Scott Young。他说这个结构只是松散地受到费曼传记的启发，费曼当时更接近把一段材料细读懂。
      </Callout>
      <H2>两张原样，各自讲一件事</H2>
      <Grid columns={2} gap={16}>
        <Card>
          <CardHeader>费曼转述父亲讲鸟</CardHeader>
          <CardBody>
            <Stack gap={8}>
              <Text weight="semibold">知道名字，和知道这只鸟，差在哪？</Text>
              <Text>
                你可以背出它在德语、中文里的称呼。那只说明人们怎么叫它。要算知道，得讲出它怎么叫、怎么教幼鸟飞、夏天飞多远、怎么认路。
              </Text>
              <Text tone="secondary">
                一句话：背得出称呼，还不等于讲得出它怎么生活。这就是本库「名词齐了」和「讲出来了」的差别。出自自传 Surely You're Joking, Mr. Feynman!，Farnam Street 引用了这段。
              </Text>
            </Stack>
          </CardBody>
        </Card>
        <Card>
          <CardHeader>Young 讲力矩</CardHeader>
          <CardBody>
            <Stack gap={8}>
              <Text weight="semibold">纸顶写 Torque。力矩是什么？</Text>
              <Text>
                他把术语收成扭转。方向类比成开瓶器：向右拧是拧紧，向左拧是拧松。学 MIT 物理时力矩题做错，就停下来把力矩本身讲清楚。
              </Text>
              <Text tone="secondary">
                手写原页在 MIT 8.01 笔记扫描里，不抄进本库。转录里他自己点出了这两步：语言改简单，再加类比。
              </Text>
            </Stack>
          </CardBody>
        </Card>
      </Grid>
    </Stack>
  );
}

const PAGES: { title: string; href: string; shows: string }[] = [
  {
    title: "Scott Young：四步原文",
    href: "https://www.scotthyoung.com/learnonsteroids/learnfast.html",
    shows: "选题、写成给初学者的讲稿、卡住就回课本、啰嗦处改简单或加类比",
  },
  {
    title: "讲解转录",
    href: "https://www.scotthyoung.com/learnonsteroids/grab/TranscriptFeynman.pdf",
    shows: "力矩收成扭转；开瓶器向右拧紧、向左拧松",
  },
  {
    title: "MIT 8.01 笔记扫描",
    href: "https://www.scotthyoung.com/mit/801-notes.pdf",
    shows: "他在真课上写下的纸，不是后人改写的摘要",
  },
  {
    title: "用好这套方法的五条",
    href: "https://www.scotthyoung.com/blog/2024/03/26/5-keys-feynman-technique/",
    shows: "他写明只是松散借鉴费曼；从卡住的那一道题写起",
  },
  {
    title: "现场演示",
    href: "https://www.youtube.com/watch?v=FrNqSLPaZLc",
    shows: "同一套步骤的口述",
  },
  {
    title: "Farnam Street",
    href: "https://fs.blog/feynman-learning-technique/",
    shows: "改成讲给六年级，引用了鸟的那段，并多了一步讲给另一个人",
  },
  {
    title: "步骤是不是费曼写的",
    href: "https://psychology.stackexchange.com/questions/30137/what-is-the-source-for-feynman-describes-the-learning-technique-named-by-his-nam/30138",
    shows: "Young 自己的说明：结构是他的",
  },
];

function PagesView() {
  return (
    <Stack gap={12}>
      <H2>可核对的网页</H2>
      <Text tone="secondary">
        每条打开的是原文页面。本库不把这些页抄进来当答案。
      </Text>
      <Table
        headers={["页面", "打开后能看到"]}
        rows={PAGES.map((page) => [
          <Link key={page.href} href={page.href}>
            {page.title}
          </Link>,
          page.shows,
        ])}
        striped
      />
      <Text size="small" tone="tertiary">
        与 .cursor/docs/费曼学习法.md 的「原作者的样例」「可参考的网页」同一份清单。仍待审查。
      </Text>
    </Stack>
  );
}

function RulesView() {
  return (
    <Stack gap={16}>
      <H2>写卡、复习、边界</H2>
      <Table
        headers={["场合", "怎么做"]}
        rows={[
          ["写问", "先写问再写答。问能单独拿来默答。"],
          ["写答", "自己的话，大约 3 到 8 行。链接、大段源码、见官方文档不能当答案。"],
          ["写例子", "至少有一个最小例子，或一个生活类比。优先用跑过的 demo。"],
          ["写易错", "只记真卡壳的点，然后改答。没有就删掉这段。"],
          ["写一句话", "压不成一句就拆卡。一张卡只装一个概念。"],
          ["写步骤", "命令、点击、截图进过程卡。概念卡只留为什么。"],
          ["复习", "只看问，讲完再对答。整卡重读几遍不算复习。"],
          ["摘抄", "教程原文留在 memory。能默答的一句才拆成正式卡。"],
          ["时长", "一张概念卡大约 30 分钟。超时多半是概念没拆开。"],
          ["打开这篇", "入门读完。之后只在忘记下一步写哪一段时打开。"],
        ]}
        striped
      />
      <Text size="small" tone="tertiary">
        来源：.cursor/docs/费曼学习法.md · 仍待审查
      </Text>
    </Stack>
  );
}
