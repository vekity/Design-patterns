from __future__ import annotations
from typing import Optional

class Graphic:
    def draw(self) -> None: ...
    def get_extent(self) -> str: ...

class Image(Graphic):
    def __init__(self, filename: str) -> None:
        self._filename = filename
        self._load_from_disk()

    def _load_from_disk(self) -> None:
        print(f"Loading heavy image: {self._filename}")

    def draw(self) -> None:
        print(f"Drawing {self._filename}")

    def get_extent(self) -> str:
        return "2000x2000"

class ImageProxy(Graphic):
    def __init__(self, filename: str) -> None:
        self._filename = filename
        self._real_image: Optional[Image] = None
        self._extent_cache: Optional[str] = None

    def draw(self) -> None:
        if self._real_image is None:
            self._real_image = Image(self._filename)
        self._real_image.draw()

    def get_extent(self) -> str:
        if self._extent_cache is None:
            if self._real_image is None:
                self._real_image = Image(self._filename)
            self._extent_cache = self._real_image.get_extent()
        return self._extent_cache

if __name__ == "__main__":
    doc_image: Graphic = ImageProxy("cat.jpg")
    print("Extent:", doc_image.get_extent())  # 不触发 load
    doc_image.draw()                          # 触发 load & draw