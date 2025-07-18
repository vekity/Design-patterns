# 子系统
class Scanner:
    def scan(self, src: str):
        print('扫描:', src)

class Parser:
    def parse(self):
        print('语法分析')

class CodeGenerator:
    def generate(self):
        print('生成字节码')

# Facade
class Compiler:
    def __init__(self):
        self._scanner = Scanner()
        self._parser = Parser()
        self._gen = CodeGenerator()

    def compile(self, src: str):
        self._scanner.scan(src)
        self._parser.parse()
        self._gen.generate()
        print('编译完成')

# 客户端
if __name__ == '__main__':
    Compiler().compile('a = 1')