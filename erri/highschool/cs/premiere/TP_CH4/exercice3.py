from random import randint

nombre1 = randint(1,100)
nombre2 = randint(1,100)
milieu = (nombre1 + nombre2) / 2
milieu_entier = (nombre1 + nombre2) // 2
print("le premier nombre vaut:", nombre1)
print("le deuxième nombre vaut:", nombre2)
print("leur milieu vaut:", str(milieu))
print("leur milieu vaut:", str(milieu_entier))