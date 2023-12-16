armes = ["balmung", "épée finale", "chlaim solais"]
for arme in armes:
    print(arme)

print()
print("------")
print()

for c in armes[-1]:
    print(c)

print()
print("------")
print()

armes.append("joyeuse")
print(armes)

print()
print("------")
print()

print(*armes)

print()