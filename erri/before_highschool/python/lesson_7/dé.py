class Dé:
    def __init__(self, nombre_de_faces):
        self.nombre_de_faces = nombre_de_faces


if __name__ == "__main__":
    nombre_faces = int(input("entrez un nombre de faces : "))
    d6 = Dé(nombre_faces)
    print(type(d6))
    print(d6.nombre_de_faces)
    d6_1 = Dé(nombre_faces)
    print(d6_1.nombre_de_faces)
