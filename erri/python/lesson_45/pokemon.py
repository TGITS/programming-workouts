class Pokemon:

    def __init__(self, name, type):
        self.name = name
        self.type = type

    def __str__(self):
        return f"name={self.name}, type={';'.join(self.type)}"

    def say(self):
        print(self.name)
