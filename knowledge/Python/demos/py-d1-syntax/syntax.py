def greet(name: str, times: int = 1) -> str:
    msg = f"hi {name}"
    return msg if times == 1 else f"{msg} x{times}"


ok = True
empty = None
label = "ready" if ok and empty is None else "bad"
print(greet("kotlin-dev"))
print(greet("java-dev", times=2))
print(label)
