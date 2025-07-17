public final class PrinterSpooler {
    // 1. 静态变量持有唯一实例
    private static volatile PrinterSpooler instance;

    // 2. 私有构造器防止外部 new
    private PrinterSpooler() {
        System.out.println("PrinterSpooler instantiated");
    }

    // 3. 全局访问点（双重检查锁定，线程安全）
    public static PrinterSpooler getInstance() {
        if (instance == null) {
            synchronized (PrinterSpooler.class) {
                if (instance == null) {
                    instance = new PrinterSpooler();
                }
            }
        }
        return instance;
    }

    // 业务方法示例
    public void addJob(String job) {
        System.out.println("Queued: " + job);
    }

    // Demo
    public static void main(String[] args) {
        PrinterSpooler spooler1 = PrinterSpooler.getInstance();
        PrinterSpooler spooler2 = PrinterSpooler.getInstance();
        System.out.println(spooler1 == spooler2); // true
        spooler1.addJob("report.pdf");
    }
}