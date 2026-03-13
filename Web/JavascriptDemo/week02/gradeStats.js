
/**
 * 进阶数组（30–40 分钟）：分别用 map（映射姓名/分数格式化）、filter（筛及格线，如 ≥60）、find（找到最高分/指定学号）、reduce（累计总分/平均分）。尝试链式组合。
 */
var students = [
    {
        studenName: "张三",
        score: 10,
        classId: "三年二班"
    },
    {
        studenName: "李四",
        score: 20,
        classId: "三年二班"
    },
    {
        studenName: "玄武",
        score: 30,
        classId: "三年二班"
    },
    {
        studenName: "玄武",
        score: 30,
        classId: "三年二班"
    },
    {
        studenName: "GG",
        score: 100,
        classId: "三年二班"
    },
    {
        studenName: "泫雅",
        score: 50,
        classId: "三年二班"
    },
    {
        studenName: "子涵",
        score: 60,
        classId: "三年二班"
    }
];

// 预热复盘（10 分钟）：回顾数组基本操作（push/pop/forEach）与 const 引用语义
const nums=[1,2,3,4]
nums.push(5)
console.log(`push 5 :${nums}`)
nums.pop()
console.log(`pop :${nums}`)
nums.unshift(0)
console.log(`unshift :${nums}`)


// map：写一段生成“姓名 - 分数”字符串数组的代码，console.log 结果，确认长度与原数组一致。
students.map(s => console.log(`${s.studenName}-${s.score}`));
const labels = students.map(s => `${s.studenName}-${s.score}`)
labels.map(l => console.log(l))
const lenth = students.length == labels.length
console.log(lenth)

// filter：写一个筛选及格（≥60）的新数组，打印长度和名单，确认不会修改原数组。
const filterScore60 = students.filter(s => s.score >= 60)
console.log(filterScore60)

// reduce：写累计总分与平均分（注意空数组初始值 0），打印总分/平均分（保留一位小数即可）。
const total = students.reduce((acc, s) => acc + s.score, 0);
const avg = students.reduce((acc, s, _, arr) => acc + s.score / arr.length, 0);
console.log(total + '-' + avg);

// find：分别找到最高分和指定姓名（比如“小明”）的对象；若找不到应得到 undefined，并用三元运算符输出提示。
const s = students.find((s) => s.studenName == "张三")
console.log(s)
const maxScore = Math.max(...students.map(s => s.score));
const topStudent = students.find(s => s.score === maxScore); // 再找到第一个达到最大分的同学
console.log(topStudent)

// 对象遍历与解构：用 for...of + Object.entries 遍历学生对象属性，练习解构 const { name, score } = student，打印一行摘要。
students.forEach(({ studenName: name, score }) => {
    console.log(`${name} ${score}`)
})
for (const s of students) {
    console.log(s);
}

// 模板字符串报告：组合上述统计为多行报告，使用反引号和 ${}，让输出包含：总人数、及格率、最高/最低分姓名、平均分。
const totalNum = students.length
const passCount = students.filter(s => s.score >= 60)
const passRate = passCount.length / totalNum
const totalScore = students.reduce((acc, s)=>acc + s.score, 0); 
const averageScore = totalNum ? totalScore / totalNum : 0;
console.log(`总分数${totalScore} 总人数：${totalNum} 及格率：${passRate} 平均分${averageScore}`)
