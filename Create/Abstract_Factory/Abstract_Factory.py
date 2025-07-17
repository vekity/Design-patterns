from abc import ABC, abstractmethod

# 抽象产品
class ScrollBar(ABC):
    @abstractmethod
    def paint(self): ...

class Window(ABC):
    @abstractmethod
    def draw(self): ...

# 具体产品（Motif 系列）
class MotifScrollBar(ScrollBar):
    def paint(self):
        print("Motif ScrollBar")

class MotifWindow(Window):
    def draw(self):
        print("Motif Window")

# 具体产品（PM 系列）
class PMScrollBar(ScrollBar):
    def paint(self):
        print("PM ScrollBar")

class PMWindow(Window):
    def draw(self):
        print("PM Window")

# 抽象工厂
class WidgetFactory(ABC):
    @abstractmethod
    def create_scroll_bar(self) -> ScrollBar: ...
    @abstractmethod
    def create_window(self) -> Window: ...

# 具体工厂
class MotifFactory(WidgetFactory):
    def create_scroll_bar(self): return MotifScrollBar()
    def create_window(self):     return MotifWindow()

class PMFactory(WidgetFactory):
    def create_scroll_bar(self): return PMScrollBar()
    def create_window(self):     return PMWindow()

# 客户端
if __name__ == "__main__":
    factory = MotifFactory()  # 换成 PMFactory() 即可整体切换风格
    sb = factory.create_scroll_bar()
    w  = factory.create_window()
    sb.paint()
    w.draw()