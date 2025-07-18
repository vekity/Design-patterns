from abc import ABC, abstractmethod

# 1. 公共接口
class VisualComponent(ABC):
    @abstractmethod
    def draw(self) -> None: ...

# 2. 原始组件
class TextView(VisualComponent):
    def draw(self) -> None:
        print("Draw TextView")

# 3. 抽象装饰器
class Decorator(VisualComponent):
    def __init__(self, component: VisualComponent):
        self._component = component
    def draw(self) -> None:
        self._component.draw()

# 4. 具体装饰：滚动条
class ScrollDecorator(Decorator):
    def draw(self) -> None:
        super().draw()
        print("  + ScrollBar")

# 5. 具体装饰：边框
class BorderDecorator(Decorator):
    def __init__(self, component: VisualComponent, width: int):
        super().__init__(component)
        self._width = width
    def draw(self) -> None:
        super().draw()
        print(f"  + Border {self._width}px")

# 6. Demo
if __name__ == "__main__":
    component = BorderDecorator(ScrollDecorator(TextView()), 3)
    component.draw()   # Border → Scroll → TextView