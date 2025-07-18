// 子系统
class Scanner {
    void scan(String src) { System.out.println("扫描: " + src); }
}
class Parser {
    void parse() { System.out.println("语法分析"); }
}
class CodeGenerator {
    void generate() { System.out.println("生成字节码"); }
}

// Facade
class Compiler {
    private final Scanner scanner = new Scanner();
    private final Parser parser = new Parser();
    private final CodeGenerator gen = new CodeGenerator();

    public void compile(String src) {
        scanner.scan(src);
        parser.parse();
        gen.generate();
        System.out.println("编译完成");
    }
}

// 客户端
public class Main {
    public static void main(String[] args) {
        new Compiler().compile("int a=1;");
    }
}