# Grâce à la notion de tableau par compréhension, filtrez le tableau de valeurs ci-dessous afin d'enlever toutes les valeurs égales à 0.
valeurs = [12, 5, 8, 32, 0, 4, 0, 0, 12, 6, 7, 0, 9, 15, 0, 0]
valeurs_sans_zeros = [e for e in valeurs if e != 0]
print(valeurs_sans_zeros)