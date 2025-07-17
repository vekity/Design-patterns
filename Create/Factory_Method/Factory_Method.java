// Product
public interface Document {
    void open();
}

// ConcreteProduct
public class DrawingDocument implements Document {
    @Override
    public void open() {
        System.out.println("Drawing document opened");
    }
}

// Creator
public abstract class Application {
    public void openDocument() {
        Document doc = createDocument();
        doc.open();
    }
    protected abstract Document createDocument();
}

// ConcreteCreator
public class DrawingApplication extends Application {
    @Override
    protected Document createDocument() {
        return new DrawingDocument();
    }

    public static void main(String[] args) {
        new DrawingApplication().openDocument();
    }
}