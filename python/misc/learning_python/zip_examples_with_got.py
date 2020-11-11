houses = ["Stark", "Lannister","Baratheon","Greyjoy"]
seats = ["Winterfell", "Casterly Rock", "Storm's End", "Pyke"]
sigils = ["A Gray Direwolf", "A Golden Lion", "A Crowned Black Stag", "A Golden Kraken"]
words = ["Winter is coming", "Hear me roar !", "Our is the fury !", "We do not sow"]

print(f"houses : {houses}")
print(f"seats : {seats}")
print(f"sigils : {sigils}")
print(f"seats : {words}")

print("\n###########\n")

print(f"zip(houses, seats, sigils, words) en tant que liste : \n{list(zip(houses, seats, sigils, words))}", )
print("unpacking de zip(houses, seats, sigils, words) :", *zip(houses, seats, sigils, words))

print("\n###########\n")

print("Affichage de chacun des éléments de la séquence zip(houses, seats, sigils, words) : ")
for got_house_info in zip(houses, seats, sigils, words):
    print(got_house_info)

print("\n###########\n")

print("Utilisation dans une compréhension")
print("\n".join([f"{house} : {word}" for house, _, _, word in zip(houses, seats, sigils, words)]))

print("\n###########\n")

print("Réalisation d'un 'unzip' et retour à la situation initiale")

print("zip(*zip(houses, seats, sigils, words)) comme liste : ", list(zip(*zip(houses, seats, sigils, words))))

print("\nunpacking de zip(*zip(houses, seats, sigils, words)) :")
houses_2,seats_2, sigils_2, words_2 = zip(*zip(houses, seats, sigils, words))

print(f"=> houses_2 : {houses_2}")
print(f"=> seats_2 : {seats_2}")
print(f"=> sigils_2 : {sigils_2}")
print(f"=> seats_2 : {words_2}")