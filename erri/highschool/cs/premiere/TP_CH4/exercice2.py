# script d’un programme qui demande le nom, le prénom et l’âge d’une personne et qui donne l’âge de cette personne dans x années,
#  x étant une variable que l’utilisateur doit saisir.

nom = input("Saisissez votre nom: ")
prenom = input("Saisissez votre prénom: ")
age = int(input("Saisissez votre âge: "))
x = int(input("Saisissez une durée en année: "))

print("votre âge dans", x, "ans sera", str(age+x))