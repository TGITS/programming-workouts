from tkinter import *
from tkinter import messagebox
from tkinter import simpledialog

window = Tk()
# Le Titre de la fenêtre est "Exemple Message Box et SimpleDialog".
window.title("Exemple Message Box et SimpleDialog")
# La fenêtre doit faire 600 pixels de largeur et 800 pixels de hauteur.
window.geometry("200x200")
# Fenêtre de demande du pseudo
pseudo = simpledialog.askstring(title="Pseudo", prompt="Saisissez votre pseudo : ", parent = window)
# Fenêtre de demande du pseudo
age = simpledialog.askstring(title="Age", prompt="Saisissez votre âge : ", parent = window)

messagebox.showwarning(title="Alerte", message = "Attention " + pseudo + " votre âge de " + age + " ans est trop élevé", parent = window)

window.mainloop()