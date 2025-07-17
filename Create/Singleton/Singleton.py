import threading

class PrinterSpooler:
    _instance = None
    _lock = threading.Lock()

    def __new__(cls):
        if cls._instance is None:
            with cls._lock:
                if cls._instance is None:
                    cls._instance = super().__new__(cls)
                    print("PrinterSpooler instantiated")
        return cls._instance

    # 业务方法示例
    def add_job(self, job: str):
        print(f"Queued: {job}")

# Demo
if __name__ == "__main__":
    s1 = PrinterSpooler()
    s2 = PrinterSpooler()
    print(s1 is s2)  # True
    s1.add_job("report.pdf")