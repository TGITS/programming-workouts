# Écrire le script d’un programme qui crée une fenêtre avec le Titre FORMULAIRE dans
# la fenêtre qui ensuite crée un champ de saisie pour mettre votre nom, un autre pour votre prénom, ensuite demande votre poids et votre taille sous la forme 1 m 45.
# La fenêtre doit faire 600 pixels de largeur et 800 pixels de hauteur.
# Le Titre de la fenêtre est "Formulaire d’inscription".
# Vous n’oublierez pas d’indiquer dans la fenêtre l’information à saisir (en dehors du champ de saisie).

from tkinter import *
from tkinter import messagebox

window = Tk()
# Le Titre de la fenêtre est "Formulaire d’inscription".
window.title("Formulaire d'inscription")
# La fenêtre doit faire 600 pixels de largeur et 800 pixels de hauteur.
window.geometry("600x800")
# Label pour demander le nom et champ de saisie pour ce dernier
name_label = Label(window,text="Nom :").pack(padx = 5,pady = 5)
name = StringVar()
name_field = Entry(window, textvariable = name).pack()
# Label pour demander le prénom et champ de saisie pour ce dernier
forname_label = Label(window, text="Prénom :").pack(padx = 5,pady = 5)
forname = StringVar()
forname_field = Entry(window, textvariable = forname).pack()
# Label pour demander la taille (sous une forme "X m YY") et champ de saisie pour ce dernier
size_label = Label(window, text="Taille (sous la forme X m YY) :").pack(padx = 5, pady = 5)
size = StringVar()
size_field = Entry(window, textvariable = size).pack()

display_action = lambda : messagebox.showinfo(title = "Affichage", message = name.get() + " " + forname.get() + " " + size.get(), parent = window)

submit_button = Button(window, text="Valider", command=display_action).pack()

window.mainloop()