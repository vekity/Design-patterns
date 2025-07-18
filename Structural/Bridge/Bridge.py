from abc import ABC, abstractmethod
from typing import Protocol

# 抽象层
class Window(ABC):
    def __init__(self, imp: "WindowImp"):
        self._imp = imp

    @abstractmethod
    def draw_rect(self, x1: int, y1: int, x2: int, y2: int) -> None: ...

    @abstractmethod
    def draw_text(self, text: str, x: int, y: int) -> None: ...

class ApplicationWindow(Window):
    def draw_rect(self, x1: int, y1: int, x2: int, y2: int) -> None:
        self._imp.device_rect(x1, y1, x2, y2)

    def draw_text(self, text: str, x: int, y: int) -> None:
        self._imp.device_text(text, x, y)

# 实现层
class WindowImp(Protocol):
    def device_rect(self, x1: int, y1: int, x2: int, y2: int) -> None: ...
    def device_text(self, text: str, x: int, y: int) -> None: ...

class XWindowImp(WindowImp):
    def device_rect(self, x1: int, y1: int, x2: int, y2: int) -> None:
        print(f"X11::DrawRect({x1},{y1})-({x2},{y2})")
    def device_text(self, text: str, x: int, y: int) -> None:
        print(f'X11::DrawText "{text}" at ({x},{y})')

class PMWindowImp(WindowImp):
    def device_rect(self, x1: int, y1: int, x2: int, y2: int) -> None:
        print(f"PM::DrawRect({x1},{y1})-({x2},{y2})")
    def device_text(self, text: str, x: int, y: int) -> None:
        print(f'PM::DrawText "{text}" at ({x},{y})')

# Demo
if __name__ == "__main__":
    app = ApplicationWindow(XWindowImp())
    app.draw_text("Hello Bridge", 10, 20)
    app.draw_rect(0, 0, 800, 600)