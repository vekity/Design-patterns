from abc import ABC, abstractmethod

# ===== 抽象 Builder =====
class Builder(ABC):
    @abstractmethod
    def convert_character(self, ch: str): ...
    @abstractmethod
    def convert_font_change(self, font: str): ...
    @abstractmethod
    def convert_paragraph(self): ...

# ===== ConcreteBuilder：纯 ASCII =====
class ASCIIBuilder(Builder):
    def __init__(self):
        self._parts = []
    def convert_character(self, ch):   self._parts.append(ch)
    def convert_font_change(self, font): pass
    def convert_paragraph(self):       self._parts.append('\n')
    def get_ascii_text(self) -> str:   return ''.join(self._parts)

# ===== ConcreteBuilder：TeX =====
class TeXBuilder(Builder):
    def __init__(self):
        self._parts = []
    def convert_character(self, ch):   self._parts.append(ch)
    def convert_font_change(self, font):
        self._parts.append(f'\\font{{{font}}}')
    def convert_paragraph(self):
        self._parts.append('\n\\par\n')
    def get_tex_text(self) -> str:     return ''.join(self._parts)

# ===== Director =====
class RTFReader:
    def __init__(self):
        self._builder = None
    def set_builder(self, b: Builder):
        self._builder = b
    def parse_rtf(self, doc: str):
        for ch in doc:
            if ch == '\n':
                self._builder.convert_paragraph()
            elif ch == '*':
                self._builder.convert_font_change('Helvetica')
            else:
                self._builder.convert_character(ch)

# ===== 客户端 =====
if __name__ == '__main__':
    reader = RTFReader()

    ascii = ASCIIBuilder()
    reader.set_builder(ascii)
    reader.parse_rtf("Hello*font*\nWorld")
    print(ascii.get_ascii_text())

    tex = TeXBuilder()
    reader.set_builder(tex)
    reader.parse_rtf("Hello*font*\nWorld")
    print(tex.get_tex_text())