# écrire le script d’un programme qui demande de saisir votre nom, votre prénom et votre âge et qui restituera la phrase suivante :
# "Bonjour xxx yyy, dans 5 ans votre âge sera zzz".
name = input("Saisissez votre nom : ")
forname = input("Saisissez votre prénom : ")
age = int(input("Saisissez votre âge : "))
print("Bonjour", forname, name, "dans 5 ans votre âge sera", str(age+5))
