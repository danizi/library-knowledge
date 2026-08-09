// @ts-check

(function () {
  /**
   * @typedef {Object} Product
   * @property {string} title
   * @property {number} price
   * @property {number} stock
   * @property {string} category
   */

  /**
   * @typedef {Object} Order
   * @property {number} id
   * @property {number} totalProducts
   * @property {number} totalQuantity
   * @property {number} total
   * @property {number} discountedTotal
   */

  /**
   * @typedef {"收入" | "支出"} TxType
   */

  /**
   * @typedef {Object} Transaction
   * @property {string} id
   * @property {TxType} type
   * @property {number} amount
   * @property {string} date
   * @property {string} note
   */

  /** @type {Promise<Product[]> | null} */
  let productsPromise = null;

  /**
   * @template {Element} T
   * @param {ParentNode} root
   * @param {string} selector
   * @returns {T}
   */
  function mustQuery(root, selector) {
    const el = root.querySelector(selector);
    if (!el) throw new Error("缺少节点: " + selector);
    return /** @type {T} */ (el);
  }

  /**
   * @param {unknown} error
   * @returns {string}
   */
  function errorMessage(error) {
    return error instanceof Error ? error.message : String(error);
  }

  /**
   * @param {HTMLElement} rowsEl
   * @param {number} colspan
   * @param {string} message
   */
  function setRowsMessage(rowsEl, colspan, message) {
    rowsEl.innerHTML = '<tr><td colspan="' + colspan + '">' + message + "</td></tr>";
  }

  /**
   * @returns {Promise<Product[]>}
   */
  function fetchProductsOnce() {
    if (!productsPromise) {
      productsPromise = fetch("https://dummyjson.com/products?limit=30")
        .then(function (res) {
          if (!res.ok) throw new Error("商品请求失败：" + res.status);
          return res.json();
        })
        .then(function (data) {
          const payload = /** @type {{products: Product[]}} */ (data);
          return payload.products;
        });
    }
    return productsPromise;
  }

  /**
   * @param {Element} root
   */
  function mountInventory(root) {
    const keywordEl = /** @type {HTMLInputElement} */ (mustQuery(root, "[data-inv-keyword]"));
    const rangeEl = /** @type {HTMLSelectElement} */ (mustQuery(root, "[data-inv-range]"));
    const applyBtn = /** @type {HTMLButtonElement} */ (mustQuery(root, "[data-inv-apply]"));
    const statusEl = /** @type {HTMLElement} */ (mustQuery(root, "[data-inv-status]"));
    const rowsEl = /** @type {HTMLElement} */ (mustQuery(root, "[data-inv-rows]"));
    /** @type {Product[]} */
    let products = [];

    /**
     * @param {Product[]} list
     */
    function render(list) {
      if (!list || list.length === 0) {
        setRowsMessage(rowsEl, 4, "暂无数据");
        return;
      }
      rowsEl.innerHTML = list
        .map(function (p) {
          const cls = p.stock < 20 ? "low" : p.stock > 50 ? "high" : "";
          return (
            "<tr>" +
            "<td>" + p.title + "</td>" +
            "<td>" + p.price + "</td>" +
            '<td class="' + cls + '">' + p.stock + "</td>" +
            "<td>" + p.category + "</td>" +
            "</tr>"
          );
        })
        .join("");
    }

    function applyFilter() {
      const keyword = keywordEl.value.trim().toLowerCase();
      const range = rangeEl.value;
      let filtered = products.filter(function (p) {
        return p.title.toLowerCase().includes(keyword);
      });
      if (range === "low") filtered = filtered.filter(function (p) { return p.stock < 20; });
      if (range === "mid") filtered = filtered.filter(function (p) { return p.stock >= 20 && p.stock <= 50; });
      if (range === "high") filtered = filtered.filter(function (p) { return p.stock > 50; });
      render(filtered);
      statusEl.textContent = "筛选结果：" + filtered.length + " 条";
    }

    function load() {
      statusEl.textContent = "加载中...";
      rowsEl.innerHTML = "";
      return fetchProductsOnce()
        .then(function (list) {
          products = list;
          render(products);
          statusEl.textContent = "加载成功：" + products.length + " 条";
        })
        .catch(function (error) {
          statusEl.textContent = "加载失败";
          setRowsMessage(rowsEl, 4, errorMessage(error));
        });
    }

    applyBtn.addEventListener("click", applyFilter);
    load();
  }

  /**
   * @param {Element} root
   */
  function mountOrders(root) {
    const sortEl = /** @type {HTMLSelectElement} */ (mustQuery(root, "[data-ord-sort]"));
    const applyBtn = /** @type {HTMLButtonElement} */ (mustQuery(root, "[data-ord-apply]"));
    const statusEl = /** @type {HTMLElement} */ (mustQuery(root, "[data-ord-status]"));
    const rowsEl = /** @type {HTMLElement} */ (mustQuery(root, "[data-ord-rows]"));
    /** @type {Order[]} */
    let orders = [];

    /**
     * @param {number} total
     * @returns {string}
     */
    function getStatus(total) {
      if (total < 500) return "待支付";
      if (total < 1500) return "已支付";
      return "已完成";
    }

    /**
     * @param {Order[]} list
     */
    function render(list) {
      if (!list || list.length === 0) {
        setRowsMessage(rowsEl, 6, "暂无数据");
        return;
      }
      rowsEl.innerHTML = list
        .map(function (o) {
          return (
            "<tr>" +
            "<td>" + o.id + "</td>" +
            "<td>" + o.totalProducts + "</td>" +
            "<td>" + o.totalQuantity + "</td>" +
            "<td>" + o.total + "</td>" +
            "<td>" + o.discountedTotal + "</td>" +
            "<td>" + getStatus(o.total) + "</td>" +
            "</tr>"
          );
        })
        .join("");
    }

    function applySort() {
      const mode = sortEl.value;
      const sorted = orders.slice();
      if (mode === "asc") sorted.sort(function (a, b) { return a.total - b.total; });
      if (mode === "desc") sorted.sort(function (a, b) { return b.total - a.total; });
      render(sorted);
      statusEl.textContent = "排序结果：" + sorted.length + " 条";
    }

    function load() {
      statusEl.textContent = "加载中...";
      rowsEl.innerHTML = "";
      return fetch("https://dummyjson.com/carts?limit=20")
        .then(function (res) {
          if (!res.ok) throw new Error("订单请求失败：" + res.status);
          return res.json();
        })
        .then(function (data) {
          const payload = /** @type {{carts: Order[]}} */ (data);
          orders = payload.carts;
          render(orders);
          statusEl.textContent = "加载成功：" + orders.length + " 条";
        })
        .catch(function (error) {
          statusEl.textContent = "加载失败";
          setRowsMessage(rowsEl, 6, errorMessage(error));
        });
    }

    applyBtn.addEventListener("click", applySort);
    load();
  }

  /**
   * @param {Element} root
   */
  function mountTransactions(root) {
    const typeEl = /** @type {HTMLSelectElement} */ (mustQuery(root, "[data-tx-type]"));
    const applyBtn = /** @type {HTMLButtonElement} */ (mustQuery(root, "[data-tx-apply]"));
    const summaryEl = /** @type {HTMLElement} */ (mustQuery(root, "[data-tx-summary]"));
    const statusEl = /** @type {HTMLElement} */ (mustQuery(root, "[data-tx-status]"));
    const rowsEl = /** @type {HTMLElement} */ (mustQuery(root, "[data-tx-rows]"));
    /** @type {Transaction[]} */
    let txList = [];

    /**
     * @param {Product[]} products
     * @returns {Transaction[]}
     */
    function buildFromProducts(products) {
      return products.slice(0, 10).map(function (p, i) {
        return {
          id: "T" + (i + 1),
          type: p.stock % 2 === 0 ? "收入" : "支出",
          amount: p.price,
          date: new Date(Date.now() - i * 86400000).toLocaleDateString(),
          note: p.title
        };
      });
    }

    /**
     * @param {Transaction[]} list
     */
    function render(list) {
      if (!list || list.length === 0) {
        setRowsMessage(rowsEl, 5, "暂无数据");
        return;
      }
      rowsEl.innerHTML = list
        .map(function (t) {
          return (
            "<tr>" +
            "<td>" + t.id + "</td>" +
            "<td>" + t.type + "</td>" +
            "<td>" + t.amount + "</td>" +
            "<td>" + t.date + "</td>" +
            "<td>" + t.note + "</td>" +
            "</tr>"
          );
        })
        .join("");
    }

    /**
     * @param {Transaction[]} list
     */
    function updateSummary(list) {
      const income = list
        .filter(function (t) { return t.type === "收入"; })
        .reduce(function (sum, t) { return sum + t.amount; }, 0);
      const expense = list
        .filter(function (t) { return t.type === "支出"; })
        .reduce(function (sum, t) { return sum + t.amount; }, 0);
      summaryEl.textContent = "收入合计：" + income + "，支出合计：" + expense;
    }

    function applyFilter() {
      const mode = typeEl.value;
      let filtered = txList.slice();
      if (mode === "income") filtered = filtered.filter(function (t) { return t.type === "收入"; });
      if (mode === "expense") filtered = filtered.filter(function (t) { return t.type === "支出"; });
      render(filtered);
      updateSummary(filtered);
      statusEl.textContent = "筛选结果：" + filtered.length + " 条";
    }

    function load() {
      statusEl.textContent = "加载中...";
      rowsEl.innerHTML = "";
      return fetchProductsOnce()
        .then(function (products) {
          txList = buildFromProducts(products);
          render(txList);
          updateSummary(txList);
          statusEl.textContent = "加载成功：" + txList.length + " 条（模拟流水）";
        })
        .catch(function (error) {
          statusEl.textContent = "加载失败";
          setRowsMessage(rowsEl, 5, errorMessage(error));
        });
    }

    applyBtn.addEventListener("click", applyFilter);
    load();
  }

  /** @type {{ mountInventory: (root: Element) => void, mountOrders: (root: Element) => void, mountTransactions: (root: Element) => void }} */
  const api = {
    mountInventory: mountInventory,
    mountOrders: mountOrders,
    mountTransactions: mountTransactions
  };

  /** @type {any} */ (window).BusinessApps = api;
})();
