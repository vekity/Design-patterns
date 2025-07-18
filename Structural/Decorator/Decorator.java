// 1. 公共接口
public interface VisualComponent {
    void draw();
}

// 2. 原始组件
class TextView implements VisualComponent {
    public void draw() {
        System.out.println("Draw TextView");
    }
}

// 3. 抽象装饰器
abstract class Decorator implements VisualComponent {
    protected VisualComponent component;
    Decorator(VisualComponent c) { this.component = c; }
    public void draw() { component.draw(); }
}

// 4. 具体装饰：滚动条
class ScrollDecorator extends Decorator {
    ScrollDecorator(VisualComponent c) { super(c); }
    public void draw() {
        super.draw();
        System.out.println("  + ScrollBar");
    }
    public void scrollTo() { /* 额外行为 */ }
}

// 5. 具体装饰：边框
class BorderDecorator extends Decorator {
    private final int width;
    BorderDecorator(VisualComponent c, int w) {
        super(c); this.width = w;
    }
    public void draw() {
        super.draw();
        System.out.println("  + Border " + width + "px");
    }
}

// 6. Demo
public class DecoratorDemo {
    public static void main(String[] args) {
        VisualComponent c = new BorderDecorator(
                                new ScrollDecorator(
                                    new TextView()), 3);
        c.draw();   // 实际调用链：Border→Scroll→TextView
    }
}