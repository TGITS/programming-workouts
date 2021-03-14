import itertools

def squared(x): return x**2

print("Itérateurs créés à partir d'une map")
iterator_of_powers_of_2_for_first_numbers = map(squared, range(1, 10))
print("La fonction map retourne un objet iterator :", iterator_of_powers_of_2_for_first_numbers)
print("C'est bien un objet iterator avec les méthodes __iter__ et __next__")
print("Il possède la fonction __iter__ ('__iter__' in dir(houses_starting_with_t)) :", '__iter__' in dir(iterator_of_powers_of_2_for_first_numbers))
print("Il possède la fonction __next__ ('__next__' in dir(houses_starting_with_t)) :", '__next__' in dir(iterator_of_powers_of_2_for_first_numbers))
print("Premier élément :", next(iterator_of_powers_of_2_for_first_numbers))
print("Elément suivant :", next(iterator_of_powers_of_2_for_first_numbers))
print("La suite des éléments : ", end = '')
print(*iterator_of_powers_of_2_for_first_numbers)
print("Somme des carrés des 9 premiers entiers strictement positifs :", sum(map(squared, range(1, 10))))
print("Maximum des carrés des 9 premiers entiers strictement positifs :", max(map(squared, range(1, 10))))
print("Minimum des carrés des 9 premiers entiers strictement positifs :", min(map(squared, range(1, 10))))

print('\n##############\n')

print('Liste avec map')
iterator_of_powers_of_2_for_first_numbers = map(squared, range(1, 10))
print("Liste des carrés des 9 premiers entiers strictement positifs :", list(iterator_of_powers_of_2_for_first_numbers))

print('\n##############\n')

print("Ensemble avec map")
iterator_of_powers_of_2_for_first_numbers = map(squared, range(1, 10))
print("Ensemble des carrés des 9 premiers entiers strictement positifs :",set(iterator_of_powers_of_2_for_first_numbers))

print('\n##############\n')

def squared_pair(x): return (x,x**2)

print("Dictionnaire avec map")
iterator_powers_of_2_for_first_numbers = map(squared_pair, range(1, 10))
print("Tableau associatif des carrés des 9 premiers entiers strictement positifs :",dict(iterator_powers_of_2_for_first_numbers))

print('\n##############\n')

print("Itérateurs _infinis_ à partir d'une map")
neverending_iterator_of_powers_of_2 = map(squared, itertools.count(1))
print("Premier élément :", next(neverending_iterator_of_powers_of_2))
print("Elément suivant :", next(neverending_iterator_of_powers_of_2))
print("La suite des éléments pour les entiers inférieurs à 1000 : ", end = '')
print(*itertools.takewhile(lambda x: x < 1000,
                           neverending_iterator_of_powers_of_2))
print("Elément suivant :", next(neverending_iterator_of_powers_of_2))

print('\n##############\n')

print('Map et filter travaillant de concert')
first_event_numbers = filter(lambda x: x % 2 == 0, range(1, 10))
print("Un objet _filter_, first_event_numbers :", first_event_numbers)
list_powers_of_2_for_first_event_numbers = map(squared, first_event_numbers)
print("Les premiers carrés :", list(list_powers_of_2_for_first_event_numbers))

print('\n##############\n')

print("Pour effectuer l'équivalent d'une compréhension imbriquée on utilise product")
columns = ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
rows = (1, 2, 3, 4, 5, 6, 7, 8)
chessboard = itertools.product(columns,rows)
print("Liste des cases de l'échiquier :", list(chessboard))

print('\n##############\n')
