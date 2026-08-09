// promise-basics.js

// 成功链
Promise.resolve('ok')
  .then(v => {
    console.log('then1', v);        // 预期：then1 ok
    return v + '!!!';               // 传给下一个 then
  })
  .then(v => console.log('then2', v)) // 预期：then2 ok!!!
  .finally(() => console.log('finally success')); // 无论如何都会执行

// 失败链
Promise.reject('boom')
  .then(v => console.log('should not run', v)) // 这里不会跑
  .catch(err => {
    console.log('catch', err);      // 预期：catch boom
    return 'recovered';
  })
  .finally(() => console.log('finally failure')); // 依然会执行
