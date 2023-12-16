nombre = int(input("entrer un nombre entier supérieur: "))

somme = 0

for d in range(1,nombre):
    if nombre % d == 0:
        somme = somme + d
    
if somme == nombre:
    print("le nombre", str(nombre), "est parfait")

else:
    print("le nombre", str(nombre), "n'est pas parfait")
 