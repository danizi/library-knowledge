# 语法速查
## 解构知识点
获取对象中的值的一种语法，可以解构对象和数组。对象解构通过键名匹配，数组按位置匹配，未匹配得到undefined，如下是基本使用的说明
### 语法
  - 对象
  ```javascript
  // value1，value2是obj中的成员变量
   const {value1,value2,...} = obj；
  ```
  - 数组
  ```javascript
   const [index1，index] = arr； 
  ```
### 一句话总结
- `对象解构`：键名匹配，修改别名使用key:alias，剩余字段使用...rest,计算属性名，赋值需要括号的规则。
- `数组解构`：位置匹配，剩余字段使用...rest,变量互换，嵌套解构。

### 案例讲解
  #### 对象
  - **键名匹配**
```javascript
const obj = {
    value1: "111",
    value2: "222",
    value3: "333"
}
// 对象按“键名”匹配,若输入不存在的键名，不匹配返回undefined
const { value1, value2 } = obj;
console.log(`对象解构：${value1} - ${value2}`) // 对象解构：111 - 222
```

 - **静态键改别名`key:alias`**
```javascript
const obj = {
value1: "111",
value2: "222",
value3: "333"
}
const { value1:newvalue1, value2 } = obj;
console.log(`对象解构修改键别名：${newvalue1} - ${value2}`) // 对象解构修改键别名：111 - 222
```
 
 - **剩余字段使用`...rest`**
```javascript
const props = {a:1,b:2,c:3};
const {a,...rest} = props
console.log(rest); // { b: 2, c: 3 }
```
 
 - **计算属性名`[dynamicKey]:value`**
```javascript
ps：需要关注下静态键该别名 vs 计算属性名的区别，一句话总结：计算属性名解决“键名是谁”，改名解决“变量叫什么”。

const key = "score";
const obj = { score: 95 };

// 计算属性名：表达式求出键名
const { [key]: val } = obj;   // 等同于 const val = obj[key];

// 改名：键名固定，变量名变了
const { score: val2 } = obj;  // 等同于 const val2 = obj.score;
```
  - **解构赋值**
```javascript
let x,y;
{x,y} = obj{x:1,y:2}
console.log(x,y) // 12
```

  #### 数组
  - **位置匹配**
```javascript
const arr = [111, 222]
// 数组按“位置”匹配
const [index1, index2] = arr; 
// const [,index2]= arr; // 跳位占位
console.log(`数组解构：${index1} - ${index2}`) // 数组解构：111 - 222

``` 
  - **函数多返回**
```javascript
const divmod = (a, b) => [Math.floor(a/b), a % b];
const [q, r] = divmod(7, 3);
console.log(q, r); // 2 1
```
  #### 惯用法/注意点示例
  - Map/Set 不用对象解构，改用迭代
```javascript
const m = new Map([["x", 1]]);
for (const [key, value] of m) {
  console.log(key, value);
}
```
