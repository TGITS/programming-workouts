somme_depart=int(input("Saisissez une somme: "))

somme=somme_depart

max_an=int(input("Saisissez un nb d'années: "))
an=0

taux=float(input("Saisissez un taux: "))

while an<max_an:
    somme=somme+(somme*taux/100)
    an+=1
    
print(somme,an) 