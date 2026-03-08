/**
 * 一 什么是异步，解释同步/异步
 * 同步：从上至下一步一步执行。
 * 异步：登记任务，等待后再执行。
 *
 *
 * QA
 * 1 上述代码的输出顺序是什么？
 * 2 为什么是这个顺序呢？
 */
const demo1 = () => {
    console.log("A")
    setTimeout(() => {
        console.log("B")
    }, 10);
    console.log("C");
}

/**
 * 二 什么是地狱回调
 * 当多个异步操作依赖前一个结果时，会用回调层层嵌套，变得难以维护。
 * 
 * QA
 * 1 这段代码会带来哪些麻烦？
 */
const demo2 = () => {
    setTimeout(() => {
        console.log("A");
        setTimeout(() => {
            console.log("B");
            setTimeout(() => {
                console.log("C");
            }, 300)
        }, 300)
    }, 300)
}

/**
 * 三 Promise的使用
 */
const getUser = () =>
    new Promise((resolve) => setTimeout(() => { resolve("用户") }, 300))

const getOrder = () =>
    new Promise((resolve) => setTimeout(() => { resolve("订单") }, 300))

const getPay = () =>
    new Promise((resolve) => setTimeout(() => { resolve("支付结果") }, 300))

const demo3 = () => {
    getUser()
        .then((user) => {
            console.log(user)
            return getOrder();
        })
        .then((order) => {
            console.log(order)
            return getPay();
        })
        .then((pay) => {
            console.log(pay)
        })
}

/**
 * 四 async/await
 * await会暂停当前函数，等Promise完成再继续。
 * 
 * QA
 * 1 Promise,them链相比async/await
 */
const demo4 = () => {
    async function run() {
        const user = await getUser();
        console.log(user);

        const order = await getOrder();
        console.log(order);

        const pay = await getPay();
        console.log(pay);
    }
    run();
}

/**
 * 五 async/await,try/catch的错误处理
 */
const demo5 = () => {
    const getUser = () => {
        return new Promise((resolve, reject) => {
            setTimeout(() => { reject(new Error("用户加载失败")) }, 300)
        });
    }

    async function run() {
        try {
            const user = await getUser();
            console.log(user)
        } catch (error) {
            console.log("出错了：", error.message)
        }
    }
    run();
}

/**
 * 六 fetch实战
 */
const demo6 = () => {
    async function loadPosts() {
        console.log("开始加载。。。");
        const res = await fetch("https://jsonplaceholder.typicode.com/posts/1");
        const data = await res.json();
        console.log("标题", data.title);
    }

    loadPosts();
}

/**
 * 七 promise链+错误传播
 * 
 * QA 
 * 1 为何"2: never"不执行。
 */
const demo7 = () => {
    const task = () => new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve("OK")
        }, 300)
    })

    task()
        .then((res) => {
            console.log("1", res)
            return Promise.reject(new Error("Boom"));
        })
        .then(() => {
            console.log("2: never");
        })
        .catch((error) => {
            console.log("3:", error.message)
            return "revovered"
        })
        .then((res) => {
            console.log("4", res)
        });
}
// demo1()
// demo2()
// demo3()
// demo4()
// demo5()
// demo6()
demo7()


