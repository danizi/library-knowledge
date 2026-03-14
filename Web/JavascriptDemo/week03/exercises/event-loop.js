
// 同步 → 微任务(Promise) → 宏任务(setTimeout)
const eventloop0 = () => {
    console.log('sync start')
    setTimeout(() => { console.log('timeout'),0})
    Promise.resolve().then(() => { console.log('primise them') })
    console.log('sync end')
    queueMicrotask(() => console.log('microtask'))
}

// 执行顺序 同步 → 逐个微任务（Promise then、queueMicrotask）→ 宏任务回调 → 宏任务内的微任务。
const eventloop1 = () => {
    console.log('sync start')
    setTimeout(() => {
        console.log('timeout')
        Promise.resolve().then(() => console.log('then inside timeout'))
            , 0
    }
    )
    Promise.resolve().then(() => { console.log('primise them') })
    console.log('sync end')
    queueMicrotask(() => console.log('microtask'))
}

eventloop0()
eventloop1()
