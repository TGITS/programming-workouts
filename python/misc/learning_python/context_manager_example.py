import pathlib

class WritableFile:
    def __init__(self,file_path):
        self.file_path=pathlib.Path(file_path)

    def __enter__(self):
        self.file_obj=self.file_path.open("w")
        return self.file_obj

    def __exit__(self,exc_type,exc_val,exc_tb):
        if self.file_obj:
            self.file_obj.close()


class ReadableFile:
    def __init__(self,file_path):
        self.file_path=pathlib.Path(file_path)

    def __enter__(self):
        self.file_obj=self.file_path.open("r")
        return self.file_obj

    def __exit__(self,exc_type,exc_val,exc_tb):
        if self.file_obj:
            self.file_obj.close()

if __name__ == "__main__":
    with WritableFile("hello.txt") as file:
        file.writelines(["Hello context manager","\n", "Thou art thus a fine construct bestowed upon us !"])

    with ReadableFile("hello.txt") as file:
        for lines in file.readlines():
            print(lines)