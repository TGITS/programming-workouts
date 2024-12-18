from random import randint

tableau = []

for _ in range(0,10):
    tableau.append(randint(0,10))

print(tableau)

somme = 0

for n in tableau:
    somme = somme + n

print(somme)

print(sum(tableau))

moyenne = 0
somme =0

for n in tableau:
    somme += n
moyenne = somme / len(tableau)
print(moyenne)

print(sum(tableau)/len(tableau))

for i in range(len(tableau)):
    print(i, " -> ", tableau[i])

# supprimer les virgules

# "1,2,3,4,5,6,7,8,9" => "1|2|3|4|5|6|7|8|9"
chaine = "1,2,3,4,5,6,7,8,9"
print("|".join(chaine.split(",")))
print("".join(chaine.split(",")))

print(tableau[0:2])

mat = []

for _ in range(10):
    mat.append([0]*10)

print(mat)

