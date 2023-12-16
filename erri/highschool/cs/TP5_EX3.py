reel1=float(input("entrez un réel: "))

operation=input("entrez une operation: ")

reel2=float(input("entrez un réel: "))

if operation == '+':
    print(reel1+reel2)
    
elif operation == '-':
    print(reel1-reel2)

elif operation == '*':
    print(reel1*reel2)
    
elif operation == '/':
    if reel2 != 0:
        print(reel1/reel2)
    else:
        print("division impossible")
    
else:
    print("erreur")
