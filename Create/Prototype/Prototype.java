// 1. Prototype 接口
public interface Graphic extends Cloneable {
    void draw(int[] pos);
    Graphic clone();
}

// 2. 具体原型
public class WholeNote implements Graphic {
    public void draw(int[] pos) {
        System.out.println("Draw WholeNote at (" + pos[0] + "," + pos[1] + ")");
    }
    public WholeNote clone() {
        try { return (WholeNote) super.clone(); }
        catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
    }
}

public class HalfNote implements Graphic {
    public void draw(int[] pos) {
        System.out.println("Draw HalfNote at (" + pos[0] + "," + pos[1] + ")");
    }
    public HalfNote clone() {
        try { return (HalfNote) super.clone(); }
        catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
    }
}

// 3. Client（工具）
public class GraphicTool {
    private final Graphic prototype;

    public GraphicTool(Graphic prototype) {
        this.prototype = prototype;
    }

    public void clickAt(int[] pos) {
        Graphic newGraphic = prototype.clone(); // 关键：克隆
        newGraphic.draw(pos);
    }

    public static void main(String[] args) {
        GraphicTool wholeTool = new GraphicTool(new WholeNote());
        wholeTool.clickAt(new int[]{120, 200});
    }
}