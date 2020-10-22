import random


class Dice:

    def __init__(self, number_of_faces):
        self.number_of_faces = number_of_faces

    def throw(self):
        self.visible_face = random.randint(1, self.number_of_faces)



if __name__ == "__main__":
    d6 = Dice(6)
    Dice.throw(d6)
    print(d6.visible_face)
