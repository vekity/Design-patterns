import java.util.function.Supplier;

/**
 * 抽象主题
 */
interface Graphic {
    void draw();
    String getExtent();
}

/**
 * 真正主题：加载巨大的图片
 */
class Image implements Graphic {
    private final String fileName;

    public Image(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading heavy image: " + fileName);
    }

    @Override
    public void draw() {
        System.out.println("Drawing " + fileName);
    }

    @Override
    public String getExtent() {
        return "2000x2000";
    }
}

/**
 * 虚代理：按需创建 Image
 */
class ImageProxy implements Graphic {
    private final String fileName;
    private final Supplier<Image> loader = () -> new Image(fileName);

    // Java 8 利用 Supplier + 惰性求值
    private volatile Image realImage;
    private volatile String extentCache;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
    }

    private Image getRealImage() {
        // 双重检查锁（DCL）实现线程安全的懒加载
        Image result = realImage;
        if (result == null) {
            synchronized (this) {
                result = realImage;
                if (result == null) {
                    realImage = result = loader.get();
                }
            }
        }
        return result;
    }

    @Override
    public void draw() {
        getRealImage().draw();
    }

    @Override
    public String getExtent() {
        String cached = extentCache;
        if (cached == null) {
            synchronized (this) {
                cached = extentCache;
                if (cached == null) {
                    extentCache = cached = getRealImage().getExtent();
                }
            }
        }
        return cached;
    }
}

/**
 * 启动器
 */
public class Demo {
    public static void main(String[] args) {
        Graphic docImage = new ImageProxy("cat.jpg");

        // 第一次只拿尺寸，不触发加载
        System.out.println("Extent: " + docImage.getExtent());

        // 真正绘制时才加载
        docImage.draw();
    }
}