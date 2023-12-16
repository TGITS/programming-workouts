class Personne:

    def __init__(self, prénom, nom, age):
        self.prénom = prénom
        self.nom = nom
        self.age = age


if __name__ == "__main__":
    jump = Personne("toutou", "de la moquette", 5)
    print(jump.prénom, jump.nom, jump.age)
