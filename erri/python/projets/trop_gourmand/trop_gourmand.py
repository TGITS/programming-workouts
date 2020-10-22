import random


def throw(nombre_faces):
    return random.randint(1, nombre_faces)


def throws(nombre_lancers):
    lancers = []
    for i in range(nombre_lancers):
        lancers.append(throw(6))
    return lancers

print("Lancement d'un dé à 6 faces : ", throw(6))
print("lancement de 3 dés : ", throws(3))
