import random

def throw(nombre_faces):
    return random.randint(1,nombre_faces)

nombre_faces=int(input("combien de face au dé : "))

print("lancement du dé à", nombre_faces, "faces :", throw(nombre_faces))
