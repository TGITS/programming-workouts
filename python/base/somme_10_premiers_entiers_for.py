# Calcul de la somme des nombres de 1 à 10 (inclus)
somme = 0

# Pour chaque nombre dans l'intervalle (1,10)
for nombre in range(1,11):
    # J'ajoute ce nombre à ma somme
    somme = somme + nombre

# J'ai calculé ma somme, je l'affiche
print('somme (avec for et range):', somme)

# Tips, encore plus simple en Python
# Cela marche aussi avec une liste
print('somme (avec sum()):', sum(range(1,11)))