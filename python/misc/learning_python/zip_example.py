from itertools import count, islice

houses = ["Stark", "Lannister","Baratheon","Greyjoy"]
seats = ["Winterfell", "Casterly Rock", "Storm's End", "Pyke"]
sigils = ["A Gray Direwolf", "A Golden Lion", "A Crowned Black Stag", "A Golden Kraken"]
words = ["Winter is coming", "Hear me roar !", "Our is the fury !", "We do not sow"]

print(f"zip(houses, seats, sigils, words) en tant que liste : \n{list(zip(houses, seats, sigils, words))}", )

print("\n###########\n")

print("Affichage de chacun des éléments de la séquence zip(houses, seats, sigils, words) : ")
for got_house_info in zip(houses, seats, sigils, words):
    print(got_house_info)


print("\n###########\n")

print("zip avec des séquences infinies")
print([*islice(zip(count(0),count(1),count(2),count(3)), 10)])