# On demande un nombre entier à l'utilisateur
# On convertit la valeur récupérée en int
number = int(input('Entrer un nombre entier: '))

# On teste si le nombre est divisible par 2 (reste nul)
if number % 2 == 0:
    print('pair')
else:
    # Sinon il n'est pas divisible par 2
    print('impair')
    # On teste s'il est divisible par 3
    if number % 3 == 0:
        print('divisible par 3')
    # On teste s'il est divisible par 5
    elif number % 5 == 0 :
        print('divisible par 5')
    # Tous les autres cas
    else:
        print('premier ou divisible par un nombre impair strictement plus grand que 5')
