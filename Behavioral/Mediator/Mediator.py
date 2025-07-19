from abc import ABC, abstractmethod

# Mediator
class DialogDirector(ABC):
    @abstractmethod
    def widget_changed(self, widget): ...

# Concrete Mediator
class FontDialogDirector(DialogDirector):
    def __init__(self):
        self.ok = Button(self)
        self.cancel = Button(self)
        self.font_list = ListBox(self)
        self.font_name = EntryField(self)

    def widget_changed(self, widget):
        if widget is self.font_list:
            self.font_name.set_text(self.font_list.get_selection())
        elif widget is self.ok:
            print("Apply font and close dialog.")
        elif widget is self.cancel:
            print("Cancel dialog.")

# Colleague
class Widget:
    def __init__(self, director):
        self.director = director
    def changed(self):
        self.director.widget_changed(self)

class Button(Widget):
    def click(self):
        self.changed()

class ListBox(Widget):
    def get_selection(self):
        return "Helvetica"
    def select(self):
        self.changed()

class EntryField(Widget):
    def set_text(self, text):
        print(f"EntryField updated to: {text}")

# Client
if __name__ == "__main__":
    director = FontDialogDirector()
    director.font_list.select()
    director.ok.click()