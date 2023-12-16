class Pokemon:

    def __init__(self, name, types, size, weight, category):
        self.name = name
        self.types = types
        self.size = size
        self.weight = weight
        self.category = category

    def __str__(self):
        return f"name={self.name}, type={';'.join(self.types)}, size={self.size}, weight={self.weight}, category={self.category}" 

    def say(self):
        print(self.name)
