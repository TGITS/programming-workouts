nombre = int(input("entrez un nombre : "))
reste = nombre % 2
quotient = nombre // 2
print("le quotient est : " + str(quotient) + " le reste est : " + str(reste))
if reste == 0 :
    print("le nombre est pair")
else:
    print("le nombre est impair")