# Reprendre l'exercice 2 en réalisant une IHM qui demande le nombre d'enfants de moins de 12 ans
# le nombre d'enfants entre ayant 12 ans et plus et moins de 18 ans et le nombre d'adultes, qui
# vous demande de saisir le tarif plein et qui vous affiche en bas de la fenêtre le prix à payer.
# Le programme réalisera l'IHM et contiendra un bouton qui validera la saisie. La commande du bouton
# sera une fonction qui calculera le prix total à payer.


from tkinter import *
fen=Tk()
fen.title("formulaire d'inscription")
fen.configure(bg="light grey")

# Champ pour la saisie du tarif
Label(fen,text="Tarif plein ",font=('arial', 20), bg="light grey").pack(anchor=W)
tarif=StringVar()
Entry(fen, textvariable=tarif, font=("arial" , 25), bg='white', fg='black').pack(anchor=W,padx=5,pady=10)

# Champ pour la saisie du nombre d'enfants de moins de 12 ans
Label(fen,text="nombre d'enfants ayant moins de 12 ans" ,font=('arial', 20), bg="light grey").pack(anchor=W)
nombreMoins12ans=StringVar()
Entry(fen, textvariable=nombreMoins12ans, font=("arial" , 25), bg='white', fg='black').pack(anchor=W,padx=5,pady=10)

# Champ pour la saisie du nombre d'enfants entre 12 et 18 ans
Label(fen,text="nombre d'enfants ayant entre 12 et 18 ans" ,font=('arial', 20), bg="light grey").pack(anchor=W)
nombreMoins18ans=StringVar()
Entry(fen, textvariable=nombreMoins18ans, font=("arial" , 25), bg='white', fg='black').pack(anchor=W,padx=5,pady=10)

# Champ pour la saisie du nombre d'adultes
Label(fen,text="nombre d'adultes" ,font=('arial', 20), bg="light grey").pack(anchor=W)
nombreAdulte=StringVar()
Entry(fen, textvariable=nombreAdulte, font=("arial" , 25), bg='white', fg='black').pack(anchor=W,padx=5,pady=10)

prixAPayer = StringVar()

def cout_total():
    '''Calcule le cout total
    :param nb_enfant_moins_de_12ans (int): nombre d'enfants de moins de 12 ans
    :param nb_enfant_entre_12_et_18ans (int): nombre d'enfants etre 12 et 18 ans
    :param nb_adultes (int): nombre d'adultes
    :param prix (float): le prix par défaut
    :return (float) : prix total'''
    prix = int(nombreMoins18ans.get())*float(tarif.get()) * 0.88 + int(nombreAdulte.get())*float(tarif.get())
    prixAPayer.set("Le prix a payer est de : " + str(prix) + " euros.")
    
Button(fen, text='Calculer', command = cout_total, font=("arial" , 25) , relief=GROOVE).pack(anchor=CENTER,padx=5,pady=10)
Label(fen, textvariable=prixAPayer, font=('arial', 20), bg="light grey").pack(anchor=W)
    
fen.mainloop()