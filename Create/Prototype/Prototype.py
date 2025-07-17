import copy
from abc import ABC, abstractmethod

# 1. Prototype 接口
class Graphic(ABC):
    @abstractmethod
    def draw(self, pos): ...
    @abstractmethod
    def clone(self) -> "Graphic": ...

# 2. 具体原型
class WholeNote(Graphic):
    def draw(self, pos):
        print(f"Draw WholeNote at {pos}")
    def clone(self):
        return copy.deepcopy(self)

class HalfNote(Graphic):
    def draw(self, pos):
        print(f"Draw HalfNote at {pos}")
    def clone(self):
        return copy.deepcopy(self)

# 3. Client（工具）
class GraphicTool:
    def __init__(self, prototype: Graphic):
        self._prototype = prototype

    def click_at(self, pos):
        new_graphic = self._prototype.clone()  # 关键：克隆
        new_graphic.draw(pos)

if __name__ == "__main__":
    tool = GraphicTool(WholeNote())
    tool.click_at((120, 200))