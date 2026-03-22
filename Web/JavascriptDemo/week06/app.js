const STORAGE_KEY = 'todo-list-v1';
let todos = [];

const todoFormEl = document.querySelector("#todo-form");
const todoInputEl = document.querySelector("#todo-input");
const todoListEl = document.querySelector("#todo-list");
const statTotalEl = document.querySelector("#stat-total");
const statActiveEl = document.querySelector("#stat-active");
const statCompleteEl = document.querySelector("#stat-complete");
const formErrorEl = document.querySelector("#form-error");

function updateStats() {
    const total = todos.length;
    const complete = todos.filter(t => t.done).length;
    const active = total - complete;
    statTotalEl.textContent = `Total: ${total}`;
    statActiveEl.textContent = `Active: ${active}`;
    statCompleteEl.textContent = `Complete: ${complete}`;
}

function render() {
    todoListEl.innerHTML = '';
    todos.forEach(todo => {
        const li = document.createElement('li');
        const text = document.createElement('span');
        text.textContent = todo.text;
        if (todo.done) text.classList.add('done');

        const toggleBtn = document.createElement('button');
        toggleBtn.textContent = todo.done ? '标为未完成' : '标为完成';
        toggleBtn.dataset.id = todo.id;

        const delBtn = document.createElement('button');
        delBtn.textContent = '删除';
        delBtn.dataset.id = todo.id;

        li.append(text, toggleBtn, delBtn);
        todoListEl.append(li);

        // 完成/未完成
        toggleBtn.addEventListener('click', () => {
            const target = todos.find(t => t.id === todo.id);
            target.done = !target.done;
            saveTodos(todos);
            render();
            updateStats();
        });

        // 删除
        delBtn.addEventListener('click', () => {
            todos = todos.filter(t => t.id !== todo.id);
            saveTodos(todos);
            render();
            updateStats();
        });
    });
}

function loadTodos() {
    try {
        const raw = localStorage.getItem(STORAGE_KEY);
        if (!raw) return [];
        const parsed = JSON.parse(raw);
        if (!Array.isArray(parsed)) throw new Error("not array");
        return raw;
    } catch (error) {
        localStorage.setItem(STORAGE_KEY, '[]');
        return [];
    }
}

function saveTodos(list) {
    try {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(list));
    } catch (error) {

    }
}

todos = loadTodos();
console.log('init todos:', todos);
render();
updateStats();

// 提交处理：原生校验 + 业务校验 + 持久化
todoFormEl.addEventListener("submit", e => {
    //原生效验
    e.preventDefault();
    if (!todoFormEl.reportValidity()) return;

    const value = todoInputEl.value.trim();
    formErrorEl.textContent = '';
    const errors = [];

    // 业务校验
    if (!value) errors.push('请输入内容');
    if (value.length < 2) errors.push('至少 2 个字符');
    if (todos.some(t => t.text.trim().toLowerCase() === value.toLowerCase())) {
        errors.push('已存在同名待办');
    }

    // 有错误就显示并退出
    if (errors.length) {
        formErrorEl.textContent = errors.join('；');
        todoInputEl.focus();
        return;
    }

    // 成功路径：入库、持久化、清空输入
    todos.push({ id: Date.now(), text: value, done: false });
    console.log('提交:', value)
    todoInputEl.value = ''
    todoInputEl.focus();

    saveTodos(todos);
    render();
    updateStats();

})
