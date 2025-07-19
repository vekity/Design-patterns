// Mediator 接口
abstract class DialogDirector {
    abstract void showDialog();
    abstract void widgetChanged(Widget widget);
    protected abstract void createWidgets();
}

// 中介者实现
class FontDialogDirector extends DialogDirector {
    private Button ok;
    private Button cancel;
    private ListBox fontList;
    private EntryField fontName;

    @Override
    protected void createWidgets() {
        ok = new Button(this);
        cancel = new Button(this);
        fontList = new ListBox(this);
        fontName = new EntryField(this);
    }

    @Override
    public void widgetChanged(Widget widget) {
        if (widget == fontList) {
            fontName.setText(fontList.getSelection());
        } else if (widget == ok) {
            System.out.println("Apply font and close dialog.");
        } else if (widget == cancel) {
            System.out.println("Cancel dialog.");
        }
    }

    @Override
    public void showDialog() {
        createWidgets();
        System.out.println("Dialog shown with font options.");
    }
}

// 抽象同事类
abstract class Widget {
    protected DialogDirector director;
    public Widget(DialogDirector director) {
        this.director = director;
    }
    public void changed() {
        director.widgetChanged(this);
    }
    public abstract void handleMouse();
}

// 具体同事类
class Button extends Widget {
    public Button(DialogDirector director) { super(director); }
    public void handleMouse() {
        changed();
    }
}

class ListBox extends Widget {
    public ListBox(DialogDirector director) { super(director); }
    public String getSelection() {
        return "Helvetica";
    }
    public void handleMouse() {
        changed();
    }
}

class EntryField extends Widget {
    public EntryField(DialogDirector director) { super(director); }
    public void setText(String text) {
        System.out.println("EntryField updated to: " + text);
    }
    public void handleMouse() {
        changed();
    }
}

// 使用示例
public class MediatorDemo {
    public static void main(String[] args) {
        DialogDirector director = new FontDialogDirector();
        director.showDialog();
        // 模拟用户选择字体
        ((ListBox)((FontDialogDirector)director).fontList).handleMouse();
        ((Button)((FontDialogDirector)director).ok).handleMouse();
    }
}