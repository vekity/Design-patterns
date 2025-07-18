from typing import Dict, List, Optional

# 1. 外部状态
class GlyphContext:
    def __init__(self) -> None:
        self._fonts: Dict[int, str] = {}
        self._index = 0

    def get_font(self, idx: int) -> str:
        return self._fonts.get(idx, "Default12")

    def set_font(self, font: str, span: int) -> None:
        for i in range(span):
            self._fonts[self._index + i] = font
        self._index += span

# 2. 享元接口
class Glyph:
    def draw(self, ctx: GlyphContext, pos: int) -> None: ...

# 3. 具体享元（内部状态：字符码）
class Character(Glyph):
    __slots__ = ("_char",)   # 节省内存
    def __init__(self, c: str) -> None:
        self._char = c
    def draw(self, ctx: GlyphContext, pos: int) -> None:
        print(f"'{self._char}'[{ctx.get_font(pos)}]", end=" ")

# 4. 非共享组合
class Row(Glyph):
    def __init__(self) -> None:
        self._children: List[Glyph] = []
    def add(self, g: Glyph) -> None:
        self._children.append(g)
    def draw(self, ctx: GlyphContext, pos: int) -> None:
        print("Row:", end="