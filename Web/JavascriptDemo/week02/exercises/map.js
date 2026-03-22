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
// 返回字符串数组
const babels = students.map(s => `${s.studenName} - ${s.score}`);
// 返回数字数组
const scores = students.map(s => s.score);
// 返回带新字段对象的数组，注意对象是要加{}的
const pass = students.map(s => ({ ...s, pass: s.score >= 60 }));

console.log("babels", babels)
console.log("scores", scores)
console.log("pass", pass)

// 链式组合组合
const passNames = students
    .filter(s => s.score >= 60)
    .map(s => s.studenName)
console.log("passNames:", passNames)