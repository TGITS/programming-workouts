valeur_initiale=100000

valeur=valeur_initiale

for i in range(0,10):
    valeur=valeur+valeur/100
    
print("valeur appartement au bout de 10 ans :", valeur)



valeur=valeur_initiale

an=0

while valeur < valeur_initiale*2:
    valeur=valeur+valeur/100
    an=an+1
    
    
print("valeur appartement au bout de",an, "ans :", valeur)