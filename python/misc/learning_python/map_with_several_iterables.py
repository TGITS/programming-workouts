from itertools import starmap, repeat, count, islice
# La fonction map peut prendre plusieurs itérables en paramètres
# Il faut juste que la fonction appliquée par map, prennent en paramètre le même nombre d'arguments
print("[map] :", *map(lambda x, y, z: x+y+z,
                      ['1', '2', '3'], ['A', 'B', 'C'], ['a', 'b', 'c']))
# Equivalent avec une compréhension
print("[compréhension] :", *[x+y+z for x, y,
                             z in zip(['1', '2', '3'], ['A', 'B', 'C'], ['a', 'b', 'c'])])
# Equivalent avec starmap
print("[starmap] :", *starmap(lambda x, y, z: x+y+z,
                              [('1', 'A', 'a'), ('2', 'B', 'b'), ('3', 'C', 'c')]))
print("[map] x puissance x, pour x entier de 1 à 9 : ",
      *map(pow, range(1, 9), range(1, 9)))
# Equivalent avec starmap
print("[starmap] x puissance x, pour x entier de 1 à 9 : ",
      *starmap(pow, zip(range(1, 9), range(1, 9))))
# Cela fonctionne aussi si l'un des iterables est infini, cela n'ira pas plus loin que le plus court des itérables
# la fonction repeat de itertools répète la valeur passée en paramètre _indéfiniment_
print("les 9 preniers chiffres à la puissance 2",
      *map(pow, range(1, 9), repeat(2)))
# Cela fonctionnera aussi si les 2 iterables sont infinis, il faudra juste faire attention en exploitant le retour de map
# La fonction count de itertools retourne une liste de valeurs partant d'une valeur de départ et d'un incrément (qui vaut 1 par défaut)
powers_of_2 = map(pow, count(1), repeat(2))
print("Les puissances de 2 pour les 20 premiers nombres à partir de 1 : ",
      *islice(powers_of_2, 0, 20))
