# Écrire le script d’un programme qui crée une fenêtre avec le Titre FORMULAIRE dans
# la fenêtre qui ensuite crée un champ de saisie pour mettre votre nom, un autre pour votre prénom, ensuite demande votre poids et votre taille sous la forme 1 m 45.
# La fenêtre doit faire 600 pixels de largeur et 800 pixels de hauteur.
# Le Titre de la fenêtre est "Formulaire d’inscription".
# Vous n’oublierez pas d’indiquer dans la fenêtre l’information à saisir (en dehors du champ de saisie).

from tkinter import *

def valid():
    pass

fen = Tk()

# Le Titre de la fenêtre est "Questionnaire de rentrée".
fen.title("Questionnaire de rentrée")

# On définit la fenêtre avec une taille de 800 pixels de largeur et 600 pixels de hauteur.
fen.geometry("800x600")

# Charger le fichier logo_ch.png
gif1 = PhotoImage(file='logo_ch.png')

Label(fen, image=gif1).pack()

# Première question
label_question1 = Label(fen,text="Pensez-vous avoir réussi votre rentrée scolaire (Oui/Non)").pack(padx = 5, pady = 5, anchor=W)
reponse1 = StringVar()
champ_reponse1 = Entry(fen, textvariable = reponse1, background="yellow").pack(padx = 5, pady = 5, anchor=W)
# Deuxième question
label_question2 = Label(fen, text="Quels enseignements aimez-vous ?").pack(padx = 5, pady = 5, anchor=W)
enseignement1 = StringVar()
champ_enseignement1 = Entry(fen, textvariable = enseignement1, background="yellow").pack(padx = 5, pady = 5, anchor=W)
enseignement2 = StringVar()
champ_enseignement2 = Entry(fen, textvariable = enseignement2, background="yellow").pack(padx = 5, pady = 5, anchor=W)
enseignement3 = StringVar()
champ_enseignement3 = Entry(fen, textvariable = enseignement3, background="yellow").pack(padx = 5, pady = 5, anchor=W)
enseignement4 = StringVar()
champ_enseignement4 = Entry(fen, textvariable = enseignement4, background="yellow").pack(padx = 5, pady = 5, anchor=W)
enseignement5 = StringVar()
champ_enseignement5 = Entry(fen, textvariable = enseignement5, background="yellow").pack(padx = 5, pady = 5, anchor=W)

# Troisième question
label_question3 = Label(fen,text="Pensez-vous avoir fait le bon choix de spécialités (Oui/Non)", anchor=W).pack(padx = 5, pady = 5, anchor=W)
reponse3 = StringVar()
champ_reponse3 = Entry(fen, textvariable = reponse3, background="yellow").pack(padx = 5, pady = 5, anchor=W)


bouton_validation = Button(fen, text="Valider vos réponses", command=valid, anchor=E).pack(padx = 5, pady = 5, anchor=E)

fen.mainloop()