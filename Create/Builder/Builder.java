// ===== 抽象 Builder =====
public interface Builder {
    void convertCharacter(char ch);
    void convertFontChange(String font);
    void convertParagraph();
}

// ===== ConcreteBuilder：纯 ASCII =====
public class ASCIIBuilder implements Builder {
    private final StringBuilder sb = new StringBuilder();

    public void convertCharacter(char ch) { sb.append(ch); }
    public void convertFontChange(String font) {}         // 忽略
    public void convertParagraph() { sb.append('\n'); }

    public String getASCIIText() { return sb.toString(); }
}

// ===== ConcreteBuilder：TeX =====
public class TeXBuilder implements Builder {
    private final StringBuilder sb = new StringBuilder();

    public void convertCharacter(char ch) { sb.append(ch); }
    public void convertFontChange(String font) {
        sb.append("\\font{").append(font).append("}");
    }
    public void convertParagraph() { sb.append("\n\\par\n"); }

    public String getTeXText() { return sb.toString(); }
}

// ===== Director =====
public class RTFReader {
    private Builder builder;
    public void setBuilder(Builder b) { this.builder = b; }

    public void parseRTF(String doc) {
        for (int i = 0; i < doc.length(); i++) {
            char c = doc.charAt(i);
            if (c == '\n') builder.convertParagraph();
            else if (c == '*') {          // 伪代码：*font* 触发字体
                builder.convertFontChange("Helvetica");
            } else {
                builder.convertCharacter(c);
            }
        }
    }
}

// ===== 客户端 =====
public class Client {
    public static void main(String[] args) {
        RTFReader reader = new RTFReader();

        // 换成 TeXBuilder 即可得到 TeX 文本
        ASCIIBuilder ascii = new ASCIIBuilder();
        reader.setBuilder(ascii);
        reader.parseRTF("Hello*font*\nWorld");
        System.out.println(ascii.getASCIIText());
    }
}