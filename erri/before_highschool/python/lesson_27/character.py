class Character:

    def __init__(self, name):
        self.name = name

    def __str__(self):
        return "Character(name:" + self.name + ")"
