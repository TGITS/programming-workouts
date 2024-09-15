# Écrire le script d’un programme qui vous demande votre pseudo et votre âge et qui
# affiche une messagebox d’avertissement de titre « alerte » et dont le texte est « Attention
# (pseudo) votre âge de (âge) ans est trop élevé ».

from tkinter import *
from tkinter import messagebox

pseudo = input("Saisissez votre pseudo : ")
age = input("Saisissez votre âge : ")
window = Tk()
messagebox.showwarning(title="Alerte", message = "Attention " + pseudo + " votre âge de " + age + " ans est trop élevé", parent = window)
window.mainloop()
