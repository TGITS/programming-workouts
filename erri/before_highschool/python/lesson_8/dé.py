import random

class Dé:
    def __init__(self, nombre_de_faces):
        self.nombre_de_faces = nombre_de_faces

    def lancer(self):
        return random.randint(1, self.nombre_de_faces)


if __name__ == "__main__":
    #nombre_faces = int(input("entrez un nombre de faces : "))
    d6 = Dé(6)
    print(type(d6))
    print(d6.nombre_de_faces)
    d8 = Dé(8)
    print(type(d8))
    print(d8.nombre_de_faces)
    print("lancement des dés")
    print("D6 :", d6.lancer())
    print("D8 :", d8.lancer())
    print("D6 :", Dé.lancer(d6))
    print("D8 :", Dé.lancer(d8))
