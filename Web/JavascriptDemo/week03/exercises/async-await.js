function delayResolve(name, ms) {
    return new Promise(resolve => setTimeout(() => resolve(name), ms));
}

function delayReject(ms) {
    return new Promise((_, reject) => setTimeout(() => reject(new Error('boom')), ms));
}

async function runSerial() {
    console.log('serial start')
    const a = await delayResolve('A', 300);
    const b = await delayResolve('B', 200);
    console.log('serial result', a, b);
    console.log('serial end');
}

async function runParallel() {
    console.log('parallel start');
    const [a, b] = await Promise.all([
        delayResolve('A', 300),
        delayResolve('B', 200),
    ]);
    console.log('parallel result', a, b);
    console.log('parallel end');
}


runSerial()
runParallel()
