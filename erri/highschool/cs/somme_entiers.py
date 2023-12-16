n=int(input("saisissez un nb entier positif: "))

somme=0

for i in range(0,n+1):
    somme=somme+i
    print(somme)

print()

print(sum(range(0,n+1)))