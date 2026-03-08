const countBtn = document.querySelector("#btn-count");
const countText = document.querySelector("#count-text");
let count = 0;
countBtn.addEventListener("click", () => {
    count += 1;
    countText.textContent = `${count}`;
})

/** @type {HTMLInputElement | null} */
const inputTextEl = document.querySelector("#input-text");
const inputLenEl = document.querySelector("#input-len");
inputTextEl.addEventListener("input", () => {
    inputLenEl.textContent = `${inputTextEl.value.length} 字符`;
});

/** @type {HTMLFormElement | null} */
const loginForm = document.querySelector("login-form");
const formMsg = document.querySelector("#form-msg");
loginForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const user = loginForm.user.value.trim();
    const pwd = loginForm.pwd.value;
    if (!user) {
        formMsg.textContent = "用户名必填";
        return;
    }
    if (pwd.length < 4) {
        formMsg.textContent = "密码至少 4 位";
        return;
    }
    formMsg.textContent = "提交成功";
})

