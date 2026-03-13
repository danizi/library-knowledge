const timeout = ms => new Promise((_, reject) => setTimeout(() => {
    reject(new Error('timeout'))
}, ms));

const url = 'https://jsonplaceholder.typicode.com/posts/1'
async function fetchWithTimeou(ms) {
    const res = await Promise.race([fetch(url), timeout(ms)])
    if (!res.ok) throw new Error('status' + res.status);
    return res.json()
}

// fetchWithTimeou(10)
//     .then(data => console.log(data))
//     .catch(err => { console.log('fail', err.message) })

// fetchWithTimeou(2000)
//     .then(data => console.log(data))
//     .catch(err => { console.log('fail', err.message) })




const controller = new AbortController();
const id = setTimeout(() => controller.abort(), 2000)

try {
    const res = await fetch(url, { signal: controller.signal })
    clearTimeout(id)
    const data = await res.json();
    console.log(data.title);
} catch (e) {
    console.log('error name:', e.name); // 超时时常见 'AbortError'
}


const p1 = new Promise(res => setTimeout(() => res('p1 ok'), 100));
const p2 = new Promise((_, rej) => setTimeout(() => rej('p2 fail'), 200));
const p3 = new Promise(res => setTimeout(() => res('p3 ok'), 50));

Promise.all([p1, p2, p3])
    .then(v => console.log('all:', v))
    .catch(e => console.log('all error:', e));

Promise.allSettled([p1, p2, p3]).then(v => console.log('allSettled:', v));

Promise.race([p1, p2, p3])
    .then(v => console.log('race:', v))
    .catch(e => console.log('race error:', e));

Promise.any([p1, p2, p3])
    .then(v => console.log('any:', v))
    .catch(e => console.log('any error:', e));



async function retry(fn, times = 3, delay = 200) {
    let attempt = 0
    while (attempt < times) {
        try {
            return await fn();
        } catch (e) {
            attempt++
            if (attempt >= times) throw e
            await new Promise(r => setTimeout(r, delay * 2 ** (attempt - 1)));
        }
    }
}

retry(() => fetchWithTimeou(500))
