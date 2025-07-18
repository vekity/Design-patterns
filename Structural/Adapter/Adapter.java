// 目标接口
public interface Shape {
    void boundingBox();
    boolean isEmpty();
    void createManipulator();
}

// 已存在的、接口不兼容的类
public class TextView {
    public void getOrigin()   { System.out.println("TextView::GetOrigin"); }
    public void getExtent()   { System.out.println("TextView::GetExtent"); }
    public boolean isEmpty()  { return false; }
}

// 对象适配器
public class TextShape implements Shape {
    private final TextView textView;

    public TextShape(TextView tv) { this.textView = tv; }

    @Override
    public void boundingBox() {
        textView.getOrigin();
        textView.getExtent();
        System.out.println("Shape::BoundingBox (adapted from TextView)");
    }

    @Override
    public boolean isEmpty() {
        return textView.isEmpty();
    }

    @Override
    public void createManipulator() {
        System.out.println("Shape::CreateManipulator (TextManipulator)");
    }

    // Demo
    public static void main(String[] args) {
        Shape textShape = new TextShape(new TextView());
        textShape.boundingBox();
    }
}