print("Veuillez entrer la première durée H,M et S")

h1=int(input("H? :"))
m1=int(input("M? :"))
s1=int(input("S? :"))

print("Veuillez entrer la deuxième durée H,M et S")

h2=int(input("H? :"))
m2=int(input("M? :"))
s2=int(input("S? :"))

s=s1+s2
m=0
if s >= 60:
    m+=1
    s-=60
    
m=m+m1+m2
h=0
if m >= 60:
    h+=1
    m-=60
    
h=h+h1+h2
j=0
if h >= 24:
    j+=1
    h-=24
    
print("La somme de la durée",h1,"heures",m1,"minutes",s1,"secondes et",h2,"heures",m2,"minutes",s2,"secondes est :")
print(j,"jour",h,"heures",m,"minutes",s,"secondes")