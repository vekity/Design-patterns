import java.util.*;

// 1. 统一组件接口
public interface Graphic {
    void draw();
    default void add(Graphic g) { throw new UnsupportedOperationException(); }
    default void remove(Graphic g) { throw new UnsupportedOperationException(); }
    default Graphic getChild(int i) { throw new UnsupportedOperationException(); }
}

// 2. 叶子节点：Line
class Line implements Graphic {
    public void draw() { System.out.println("Draw Line"); }
}

// 3. 叶子节点：Text
class Text implements Graphic {
    public void draw() { System.out.println("Draw Text"); }
}

// 4. 组合节点：Picture
class Picture implements Graphic {
    private final List<Graphic> children = new ArrayList<>();

    public void draw() {
        System.out.println("--- Picture Start ---");
        children.forEach(Graphic::draw);
        System.out.println("--- Picture End ---");
    }

    public void add(Graphic g) { children.add(g); }
    public void remove(Graphic g) { children.remove(g); }
    public Graphic getChild(int i) { return children.get(i); }
}

// 5. Demo
class CompositeDemo {
    public static void main(String[] args) {
        Graphic root = new Picture();
        root.add(new Line());
        root.add(new Text());

        Graphic sub = new Picture();
        sub.add(new Line());
        sub.add(new Text());
        root.add(sub);

        root.draw();   // 一次性递归绘制整棵树
                    //  调用5次draw() root.draw() -> root.children[0].draw() , root.children[1].draw() , root.children[2].draw()->root.children[2][0].draw , root.children[2][1].draw()
                    //                                  Line() , Text() , Picture()  ;而Picture() : Line()  Text()  
     }
}
