from dataclasses import dataclass


@dataclass
class Student:
    name: str
    score: int


students = [
    Student("a", 80),
    Student("b", 95),
    Student("c", 70),
]
ordered = sorted(students, key=lambda s: s.score, reverse=True)
print(ordered)

try:
    raise ValueError("demo error")
except ValueError as e:
    print(f"caught: {e}")
