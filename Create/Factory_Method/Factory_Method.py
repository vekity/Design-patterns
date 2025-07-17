from abc import ABC, abstractmethod

# Product
class Document(ABC):
    @abstractmethod
    def open(self) -> None: ...

# ConcreteProduct
class DrawingDocument(Document):
    def open(self) -> None:
        print("Drawing document opened")

# Creator
class Application(ABC):
    def open_document(self) -> None:
        doc = self.create_document()
        doc.open()

    @abstractmethod
    def create_document(self) -> Document: ...

# ConcreteCreator
class DrawingApplication(Application):
    def create_document(self) -> Document:
        return DrawingDocument()

if __name__ == "__main__":
    DrawingApplication().open_document()