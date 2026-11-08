# py-d4-module

本地 `import` + `if __name__ == "__main__"`。

```bash
python3 main.py
python3 -c "import main"
```

第二条会打印 `imported as module`，不会跑 `main()`。
