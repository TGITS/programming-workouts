min=int(input("Entrez un nombre entier positif: "))
max=int(input("Entrez un deuxième nombre entier positif supérieur au premier: "))

for i in range(min,max+1):
    res=""
    if i % 3 == 0:
        res+="Fizz"
    if i % 5 == 0:
        res+="Buzz"
    if len(res)==0:
        res+=str(i)
    print(res)

