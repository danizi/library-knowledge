async function fetchPost() {
    try {
        const res = await fetch('https://jsonplaceholder.typicode.com/posts/1');
        if (!res.ok) throw new Error('status' + res.status);
        const data = await res.json();
        console.log('title:', data.title, 'userId:', data.userId);
    } catch (e) {
        console.log("fetch error:", e.message)
    } finally {
        console.log("done")
    }
}

async function demo(url) {
    try {
        const res = await fetch(url);

        if (!res.ok) throw new Error('status:' + res.status);
        const data = await res.json();
        //console.log(data)
        return data
    } catch (e) {
        //console.log("fail:", e.message)
        return null
    } finally {
        //console.log("done")
    }
}

const [data1, data2] = await Promise.all(
    [
        demo('https://jsonplaceholder.typicode.com/posts/1'),
        demo('https://jsonplaceholder.typicode.com/users/1')
    ]
)
console.log(data1?.title ??"",data1?.name??"")
console.log(data2?.title ??"",data2?.name??"")
