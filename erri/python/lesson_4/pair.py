def est_pair(nombre):
    reste = nombre % 2
    return reste == 0


number = int(input("entrez un nombre : "))

if est_pair(number) :
    print("le nombre est pair")
else:
    print("le nombre est impair")