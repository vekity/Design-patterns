from __future__ import annotations
from abc import ABC, abstractmethod
from typing import List, Optional

# 1. 统一组件接口
class Graphic(ABC):
    @abstractmethod
    def draw(self) -> None: ...

    def add(self, g: "Graphic") -> None:
        raise NotImplementedError
    def remove(self, g: "Graphic") -> None:
        raise NotImplementedError
    def get_child(self, idx: int) -> Optional["Graphic"]:
        raise NotImplementedError

# 2. 叶子节点
class Line(Graphic):
    def draw(self) -> None:
        print("Draw Line")

class Text(Graphic):
    def draw(self) -> None:
        print("Draw Text")

# 3. 组合节点
class Picture(Graphic):
    def __init__(self) -> None:
        self._children: List[Graphic] = []

    def draw(self) -> None:
        print("--- Picture Start ---")
        for child in self._children:
            child.draw()
        print("--- Picture End ---")

    def add(self, g: Graphic) -> None:
        self._children.append(g)

    def remove(self, g: Graphic) -> None:
        self._children.remove(g)

    def get_child(self, idx: int) -> Optional[Graphic]:
        return self._children[idx] if 0 <= idx < len(self._children) else None

# 4. Demo
if __name__ == "__main__":
    root = Picture()
    root.add(Line())
    root.add(Text())

    sub = Picture()
    sub.add(Line())
    sub.add(Text())
    root.add(sub)

    root.draw()   # 递归绘制整棵树