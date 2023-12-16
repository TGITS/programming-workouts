types_pokemon = [
    "Normal",
    "Fighting",
    "Flying",
    "Poison",
    "Ground",
    "Rock",
    "Bug",
    "Ghost",
    "Steel",
    "Fire",
    "Water",
    "Grass",
    "Electric",
    "Psychic",
    "Ice",
    "Dragon",
    "Dark",
    "Fairy",
]

for type in types_pokemon:
    print(type.upper())

print()

for type in types_pokemon:
    print(type.lower())

print()

for i in range(0, len(types_pokemon)):
    print(i, types_pokemon[i])

print()

for i, type in enumerate(types_pokemon):
    print(i, type)

types_lowercase = []

for type in types_pokemon:
    types_lowercase.append(type.lower())

print(types_lowercase)

types_lowercase_2 = [type.lower() for type in types_pokemon]
print(types_lowercase_2)
