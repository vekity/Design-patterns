from typing import Protocol

class Shape(Protocol):
    def bounding_box(self) -> None: ...
    def is_empty(self) -> bool: ...
    def create_manipulator(self) -> None: ...

class TextView:
    def get_origin(self) -> None:
        print("TextView::get_origin")
    def get_extent(self) -> None:
        print("TextView::get_extent")
    def is_empty(self) -> bool:
        return False

class TextShape:
    def __init__(self, text_view: TextView):
        self._tv = text_view

    def bounding_box(self) -> None:
        self._tv.get_origin()
        self._tv.get_extent()
        print("Shape::bounding_box (adapted from TextView)")

    def is_empty(self) -> bool:
        return self._tv.is_empty()

    def create_manipulator(self) -> None:
        print("Shape::create_manipulator (TextManipulator)")

if __name__ == "__main__":
    shape: Shape = TextShape(TextView())
    shape.bounding_box()