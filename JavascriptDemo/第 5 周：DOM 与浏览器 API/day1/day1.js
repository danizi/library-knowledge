const titleEl = document.querySelector("h1");
if (!titleEl) throw new Error("缺少 h1");
titleEl.textContent = "hello dom";
titleEl.style.color = "tomato";


/** @type {NodeListOf<HTMLElement>} */
const items = document.querySelectorAll(".item");
items.forEach((el, i) => {
    el.textContent = `第（${i + 1}）项`;
});

const highlightEl = document.querySelector("#highlight");
const resetBtn = document.querySelector("#reset");
if (!highlightEl || !resetBtn) throw new Error("缺少按钮");

highlightEl.addEventListener("click", () => {
    items.forEach((el) => {
        el.style.background = "#fff3cd"; // 浅黄
    });
});

resetBtn.addEventListener("click", () => {
    items.forEach((el) => {
        el.style.background = "";
    });
});
