// 抽象产品
interface ScrollBar { void paint(); }
interface Window    { void draw(); }

// 具体产品（Motif 系列）
class MotifScrollBar implements ScrollBar {
    public void paint() { System.out.println("Motif ScrollBar"); }
}
class MotifWindow implements Window {
    public void draw() { System.out.println("Motif Window"); }
}

// 具体产品（PM 系列）
class PMScrollBar implements ScrollBar {
    public void paint() { System.out.println("PM ScrollBar"); }
}
class PMWindow implements Window {
    public void draw() { System.out.println("PM Window"); }
}

// 抽象工厂
interface WidgetFactory {
    ScrollBar createScrollBar();
    Window    createWindow();
}

// 具体工厂
class MotifFactory implements WidgetFactory {
    public ScrollBar createScrollBar() { return new MotifScrollBar(); }
    public Window    createWindow()    { return new MotifWindow(); }
}

class PMFactory implements WidgetFactory {
    public ScrollBar createScrollBar() { return new PMScrollBar(); }
    public Window    createWindow()    { return new PMWindow(); }
}

// 客户端
public class Client {
    public static void main(String[] args) {
        WidgetFactory factory = new MotifFactory(); // 换一行即可切换风格
        ScrollBar sb = factory.createScrollBar();
        Window    w  = factory.createWindow();
        sb.paint();
        w.draw();
    }
}