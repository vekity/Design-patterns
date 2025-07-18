import java.util.*;

// 1. 外部状态
class GlyphContext {
    private int index = 0;
    private Map<Integer, Font> fontMap = new HashMap<>();

    public Font getFont(int idx) { return fontMap.getOrDefault(idx, new Font("Default", 12)); }
    public void setFont(Font f, int span) {
        for (int i = 0; i < span; i++) fontMap.put(index + i, f);
        index += span;
    }
}

// 2. 享元接口
interface Glyph {
    void draw(GlyphContext ctx, int pos);
}

// 3. 具体享元（内部状态：字符码）
class Character implements Glyph {
    private final char charCode;

    Character(char c) { this.charCode = c; }

    public void draw(GlyphContext ctx, int pos) {
        System.out.printf("'%c'[%s] ", charCode, ctx.getFont(pos).getName());
    }
}

// 4. 非共享组合
class Row implements Glyph {
    private final List<Glyph> children = new ArrayList<>();

    public void add(Glyph g) { children.add(g); }

    public void draw(GlyphContext ctx, int pos) {
        System.out.print("Row: ");
        for (Glyph g : children) g.draw(ctx, pos);
        System.out.println();
    }
}

// 5. 工厂
class GlyphFactory {
    private final Character[] pool = new Character[128];

    public Character createCharacter(char c) {
        int idx = c;
        if (pool[idx] == null) pool[idx] = new Character(c);
        return pool[idx];
    }
    public Row createRow() { return new Row(); }
}

// 6. Demo
public class FlyweightDemo {
    public static void main(String[] args) {
        GlyphFactory factory = new GlyphFactory();
        GlyphContext ctx = new GlyphContext();
        ctx.setFont(new Font("Times", 12), 5);

        Row row = factory.createRow();
        row.add(factory.createCharacter('H'));
        row.add(factory.createCharacter('e'));
        row.add(factory.createCharacter('l'));
        row.add(factory.createCharacter('l'));
        row.add(factory.createCharacter('o'));

        row.draw(ctx, 0);  // 5 个字符，但只创建 4 个共享实例
    }
}