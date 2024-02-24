somme_depart=1500

somme=somme_depart

somme_scooter=1700

an=0

while somme<somme_scooter:
    somme=somme+(somme*1.5/100)
    an+=1
    
print(somme,an) 
    