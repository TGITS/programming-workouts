# 15/02/2025
# auteur: Vaudry Erri



from tkinter import *
from tkinter.messagebox import askyesno, showwarning
from csv import *

def lire_stock() -> dict[str,int]:
    fichier_stock=open("stock.csv")
    table_stock=list(reader(fichier_stock))
    fichier_stock.close()
    stock={}
    for i in range(1,len(table_stock)):
        stock[table_stock[i][0]]=int(table_stock[i][1])
        
    return stock

def ecrire_stock(stock: dict[str,int]):
    fichier_stock = open('stock.csv', 'w', newline='')
    fichier = writer(fichier_stock, delimiter=',', lineterminator='\n')
    fichier.writerow(["article","quantité"])
    for cle,valeur in stock.items():
        fichier.writerow([cle,valeur])
    fichier_stock.close()

def lire_prix() -> dict[str,float]:
    fichier_prix=open("prix.csv")
    table_prix=list(reader(fichier_prix))
    fichier_prix.close()
    prix={}
    for i in range(1,len(table_prix)):
        prix[table_prix[i][0]]=float(table_prix[i][1])
    return prix

def ecrire_prix(prix: dict[str,int]):
    fichier_prix = open('prix.csv', 'w', newline='')
    fichier = writer(fichier_prix, delimiter=',', lineterminator='\n')
    fichier.writerow(["article","prix"])
    for cle,valeur in prix.items():
        fichier.writerow([cle,valeur])
    fichier_prix.close()
    
def augmenter_prix_pourcentage(prix: dict[str,int], pourcentage: int):
    '''
    Augmentation des prix contenu dans le dictionnaire du pourcentage indiqué en paramètre.
    Le dictionnaire représentant les prix est directement modifié
    '''
    for cle,valeur in prix.items():
        prix[cle] = prix[cle] + (prix[cle] * pourcentage / 100)

def stock():
    stock = lire_stock()
    livraison=Tk()
    livraison.geometry("500x500")
    livraison.title("Livraison")
    livraison.configure(bg="white")
    
    Label(livraison,text="SARL Chatelet",font=('arial', 15), bg="white",anchor="w").grid(row=0,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(livraison,text="357 rue Marceline",font=('arial', 15), bg="white",anchor="w").grid(row=1,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(livraison,text="59500 Douai",font=('arial', 15), bg="white",anchor="w").grid(row=2,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(livraison,text="Arrivage",font=('arial', 15), bg="white",anchor="w").grid(row=5,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(livraison,text="Articles",font=('arial', 15), bg="white",anchor="w").grid(row=6,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(livraison,text="Quantité",font=('arial', 15), bg="white",anchor="w").grid(row=6,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(livraison,text="gomme",font=('arial', 15), bg="white",anchor="w").grid(row=8,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    gomme=IntVar(livraison)
    gomme.set(stock["gomme"])
    Entry(livraison, textvariable=gomme, font=("arial", 15), bg='white', fg='black').grid(row=8,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(livraison,text="règle",font=('arial', 15), bg="white",anchor="w").grid(row=9,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    regle=IntVar(livraison)
    regle.set(stock["regle"])
    Entry(livraison, textvariable=regle, font=("arial", 15), bg='white', fg='black').grid(row=9,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(livraison,text="compas",font=('arial', 15), bg="white",anchor="w").grid(row=10,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    compas=IntVar(livraison)
    compas.set(stock["compas"])
    Entry(livraison, textvariable=compas, font=("arial", 15), bg='white', fg='black').grid(row=10,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(livraison,text="stylo rouge",font=('arial', 15), bg="white",anchor="w").grid(row=11,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    stylor=IntVar(livraison)
    stylor.set(stock["stylo rouge"])
    Entry(livraison, textvariable=stylor, font=("arial", 15), bg='white', fg='black').grid(row=11,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(livraison,text="stylo bleue",font=('arial', 15), bg="white",anchor="w").grid(row=12,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    stylob=IntVar(livraison)
    stylob.set(stock["stylo bleu"])
    Entry(livraison, textvariable=stylob, font=("arial", 15), bg='white', fg='black').grid(row=12,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(livraison,text="crayon",font=('arial', 15), bg="white",anchor="w").grid(row=13,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    crayon=IntVar(livraison)
    crayon.set(stock["crayon"])
    Entry(livraison, textvariable=crayon, font=("arial", 15), bg='white', fg='black').grid(row=13,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    def valider():
        stock["gomme"] = gomme.get()
        stock["regle"] = regle.get()
        stock["compas"] = compas.get()
        stock["stylo rouge"] = stylor.get()
        stock["stylo bleu"] = stylob.get()
        stock["crayon"] = crayon.get()
        ecrire_stock(stock)
    
    Button(livraison, text='VALIDER', command = valider, font=("arial", 15) , relief=GROOVE, width= 8,height=2,wraplength=100,anchor="center").grid(row=14,column=3,padx=2,pady=2, sticky='w'+'e'+'n'+'s')





def commande():
    commande=Tk()
    commande.geometry("500x500")
    commande.title("Livraison")
    commande.configure(bg="white")
    
    Label(commande,text="SARL Chatelet",font=('arial', 15), bg="white",anchor="w").grid(row=0,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(commande,text="357 rue Marceline",font=('arial', 15), bg="white",anchor="w").grid(row=1,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(commande,text="59500 Douai",font=('arial', 15), bg="white",anchor="w").grid(row=2,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(commande,text="Bon de commande",font=('arial', 15), bg="white",anchor="w").grid(row=5,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(commande,text="Articles",font=('arial', 15), bg="white",anchor="w").grid(row=6,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(commande,text="Quantité",font=('arial', 15), bg="white",anchor="w").grid(row=6,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(commande,text="gomme",font=('arial', 15), bg="white",anchor="w").grid(row=8,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    gomme=IntVar(commande)
    gomme.set(0)
    Entry(commande, textvariable=gomme, font=("arial", 15), bg='white', fg='black').grid(row=8,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(commande,text="règle",font=('arial', 15), bg="white",anchor="w").grid(row=9,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    regle=IntVar(commande)
    regle.set(0)
    Entry(commande, textvariable=regle, font=("arial", 15), bg='white', fg='black').grid(row=9,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(commande,text="compas",font=('arial', 15), bg="white",anchor="w").grid(row=10,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    compas=IntVar(commande)
    compas.set(0)
    Entry(commande, textvariable=compas, font=("arial", 15), bg='white', fg='black').grid(row=10,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(commande,text="stylo rouge",font=('arial', 15), bg="white",anchor="w").grid(row=11,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    stylor=IntVar(commande)
    stylor.set(0)
    Entry(commande, textvariable=stylor, font=("arial", 15), bg='white', fg='black').grid(row=11,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(commande,text="stylo bleue",font=('arial', 15), bg="white",anchor="w").grid(row=12,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    stylob=IntVar(commande)
    stylob.set(0)
    Entry(commande, textvariable=stylob, font=("arial", 15), bg='white', fg='black').grid(row=12,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    Label(commande,text="crayon",font=('arial', 15), bg="white",anchor="w").grid(row=13,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    crayon=IntVar(commande)
    crayon.set(0)
    Entry(commande, textvariable=crayon, font=("arial", 15), bg='white', fg='black').grid(row=13,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')   
    
    def commande_possible(stock, commande_courante):
        for cle in commande_courante.keys():
             if cle in stock.keys():
                if commande_courante[cle]> stock[cle]:
                    return False
             else:
                return False
        return True
    
    def facture(commande_courante, prix):
                total=0.0
                for cle in commande_courante.keys():
                    if cle in prix.keys():
                        total+=prix[cle]*commande_courante[cle]
                return round(total,2)
    
    def valide_commande():
        stock = lire_stock()
        prix = lire_prix()
        
        commande_courante = {}
        commande_courante["gomme"] = gomme.get()
        commande_courante["regle"] = regle.get()
        commande_courante["compas"] = compas.get()
        commande_courante["stylo rouge"] = stylor.get()
        commande_courante["stylo bleu"] = stylob.get()
        commande_courante["crayon"] = crayon.get()
        
        def maj_stock():
            if commande_possible(stock, commande_courante):
                for cle in commande_courante.keys():
                    if cle in stock.keys():
                        stock[cle]=stock[cle]-commande_courante[cle]
            ecrire_stock(stock)
            a_payer.destroy()
            commande.destroy()
            
        if commande_possible(stock, commande_courante) == True:
            a_payer=Tk()
            a_payer.geometry("500x400")
            a_payer.title("Montant à régler")
            a_payer.configure(bg="white")
            Label(a_payer,text="SARL Chatelet",font=('arial', 15), bg="white",anchor="w").grid(row=0,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
            Label(a_payer,text="357 rue Marceline",font=('arial', 15), bg="white",anchor="w").grid(row=1,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
            Label(a_payer,text="59500 Douai",font=('arial', 15), bg="white",anchor="w").grid(row=2,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
            Label(a_payer,text="Le montant de la commande est : ",font=('arial', 15), bg="white",anchor="w").grid(row=5,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
            total=IntVar(a_payer)
            total.set(facture(commande_courante, prix))
            Entry(a_payer, textvariable=total, font=("arial", 15), bg='white', fg='black').grid(row=8,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
            
            
            
            Button(a_payer, text='PAYER', command = maj_stock, font=("arial", 15) , relief=GROOVE, width= 8,height=2,wraplength=100,anchor="center").grid(row=10,column=2,padx=2,pady=2, sticky='n'+'s')
        else:
            showwarning(title="Commande impossible", message="Commande impossible")
            
    Button(commande, text='VALIDER', command = valide_commande, font=("arial", 15) , relief=GROOVE, width= 8,height=2,wraplength=100,anchor="center").grid(row=14,column=3,padx=2,pady=2, sticky='w'+'e'+'n'+'s')




def prix():
    prix=Tk()
    prix.geometry("800x300")
    prix.title("Montant à régler")
    prix.configure(bg="white")
    Label(prix,text="SARL Chatelet",font=('arial', 15), bg="white",anchor="w").grid(row=0,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(prix,text="357 rue Marceline",font=('arial', 15), bg="white",anchor="w").grid(row=1,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(prix,text="59500 Douai",font=('arial', 15), bg="white",anchor="w").grid(row=2,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(prix,text="Indiquez le pourcentage d'augmentation des prix ",font=('arial', 15), bg="white",anchor="w").grid(row=5,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    pourcentage=IntVar(prix)
    pourcentage.set(0)
    Entry(prix, textvariable=pourcentage, font=("arial", 15), bg='white', fg='black').grid(row=8,column=2,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    Label(prix,text="%",font=('arial', 15), bg="white",anchor="w").grid(row=8,column=3,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
    
    def modifie():
        dict_prix = lire_prix()
        augmenter_prix_pourcentage(dict_prix, pourcentage.get())
        ecrire_prix(dict_prix)
        reponse = askyesno(title="Autre modification", message="Voulez-vous faire une autre modification de prix ?")
        if reponse:
            pourcentage.set(0)
        else:
            prix.destroy()
        
        
    
    Button(prix, text='VALIDER', command = modifie, font=("arial", 15) , relief=GROOVE, width= 8,height=2,wraplength=100,anchor="center").grid(row=14,column=2,padx=2,pady=2, sticky='n'+'s')

    




def quitter():
    fen.destroy()




fen=Tk()
fen.geometry("500x700")
fen.title("SARL Chatelet")
fen.configure(bg="white")

Label(fen,text="SARL Chatelet",font=('arial', 15), bg="white",anchor="w").grid(row=0,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
Label(fen,text="357 rue Marceline",font=('arial', 15), bg="white",anchor="w").grid(row=1,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')
Label(fen,text="59500 Douai",font=('arial', 15), bg="white",anchor="w").grid(row=2,column=0,padx=1,pady=1, sticky='w'+'e'+'n'+'s')

Button(fen, text='Gérer le stock', command = stock, font=("arial" , 15) , relief=GROOVE, width= 10,height=4,wraplength=100,anchor="center").grid(row=3,column=1,padx=2,pady=2, sticky='w'+'e'+'n'+'s')
Button(fen, text='Faire une commande', command = commande, font=("arial" , 15) , relief=GROOVE, width= 10,height=4,wraplength=100,anchor="center").grid(row=4,column=1,padx=2,pady=2, sticky='w'+'e'+'n'+'s')
Button(fen, text='Modifie les prix', command = prix, font=("arial" , 15) , relief=GROOVE, width= 10,height=4,wraplength=100,anchor="center").grid(row=5,column=1,padx=2,pady=2, sticky='w'+'e'+'n'+'s')
Button(fen, text='Quitter', command = quitter, font=("arial" , 15) , relief=GROOVE, width= 10,height=4,wraplength=100,anchor="center").grid(row =6,column=1,padx=2,pady=2, sticky='w'+'e'+'n'+'s')
fen.mainloop() 