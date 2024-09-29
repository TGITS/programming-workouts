from tkinter import *
from tkinter import messagebox

def saisi():
    """
    Récupère et retourne les valeurs saisies par l'utilisateur après les avoir convertie en float
    Les valeurs sont affectées au variables globales longueur, largeur, hauteur
    """
    global longueur
    global largeur
    global hauteur
    longueur = float(valeur_longueur.get())
    largeur = float(valeur_largeur.get())
    hauteur = float(valeur_hauteur.get())

def affiche():
    """
    Calcule le volume à partir des variables globales longueur, largeur et hauteur el l'affiche dans une messagebox
    """
    volume = longueur * largeur * hauteur
    messagebox.showinfo(title = "Volume", message = f"Le volume est  {volume}", parent = window)

global window 
global valeur_longueur
global valeur_largeur
global valeur_hauteur

window = Tk()
window.title("Saisie des dimensions du parallélépipède")
window.geometry("600x800")

Label(window,text="Longeur :").pack(padx = 5,pady = 5)
valeur_longueur = StringVar()
Entry(window, textvariable = valeur_longueur).pack()

Label(window, text="Largeur :").pack(padx = 5,pady = 5)
valeur_largeur = StringVar()
Entry(window, textvariable = valeur_largeur).pack()

Label(window, text="Hauteur :").pack(padx = 5, pady = 5)
valeur_hauteur = StringVar()
Entry(window, textvariable = valeur_hauteur).pack()

Button(window, text="Soumettre", command=saisi).pack()
Button(window, text="Caculer", command=affiche).pack()

window.mainloop()
