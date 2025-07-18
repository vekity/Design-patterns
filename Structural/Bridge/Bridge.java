// 抽象层
public interface Window {
    void drawRect(int x1, int y1, int x2, int y2);
    void drawText(String text, int x, int y);
}

// 实现层接口
public interface WindowImp {
    void deviceRect(int x1, int y1, int x2, int y2);
    void deviceText(String text, int x, int y);
}

// 抽象子类：应用窗口
public class ApplicationWindow implements Window {
    private final WindowImp imp;

    public ApplicationWindow(WindowImp imp) {
        this.imp = imp;
    }

    @Override public void drawRect(int x1, int y1, int x2, int y2) {
        imp.deviceRect(x1, y1, x2, y2);
    }

    @Override public void drawText(String text, int x, int y) {
        imp.deviceText(text, x, y);
    }
}

// 实现子类：X11
public class XWindowImp implements WindowImp {
    @Override public void deviceRect(int x1, int y1, int x2, int y2) {
        System.out.printf("X11::DrawRect(%d,%d)-(%d,%d)%n", x1, y1, x2, y2);
    }

    @Override public void deviceText(String text, int x, int y) {
        System.out.printf("X11::DrawText \"%s\" at (%d,%d)%n", text, x, y);
    }
}

// 实现子类：PM
public class PMWindowImp implements WindowImp {
    @Override public void deviceRect(int x1, int y1, int x2, int y2) {
        System.out.printf("PM::DrawRect(%d,%d)-(%d,%d)%n", x1, y1, x2, y2);
    }

    @Override public void deviceText(String text, int x, int y) {
        System.out.printf("PM::DrawText \"%s\" at (%d,%d)%n", text, x, y);
    }
}

// Demo
public class BridgeDemo {
    public static void main(String[] args) {
        Window win = new ApplicationWindow(new XWindowImp());
        win.drawText("Hello Bridge", 10, 20);
        win.drawRect(0, 0, 800, 600);
    }
}