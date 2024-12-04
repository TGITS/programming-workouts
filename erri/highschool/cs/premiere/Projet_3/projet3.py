#créé le 25/11/2024
from tkinter import *

def quadrillage():
    '''
    trace le quadrillage de la feuille de canvas comme dans la figure
    '''
    for i in range(0,11):
        canvas.create_line(10 + i*50,10,10+ i*50,510,fill="black", width=1)
        
    for  i in range(0,11):  
        canvas.create_line(10,10+i*50,510,10+i*50,fill="black", width=1)

def tableau_jeu()->list:
    '''
    crée le tableau correspondant à la grille de jeu de la figure
    '''
    tab = []
    for i in range(0,10): 
        tab.append([0]*10)
    
    tab[0][5]=20
    tab[1][9]=30
    tab[2][7]=30
    tab[3][2]=20
    tab[4][0]=10
    tab[5][8]=10
    tab[6][4]=10
    tab[7][3]=30
    tab[8][7]=20
    
    print(tab)
    return tab


def affiche_valeurs(tab:list):
    '''
    affiche dans le quadrillage les valeurs du tableau de jeu autres que 0
    '''
    for ligne in tab:
        for case in ligne:
            if case != 0:
                canvas.create_text(ligne.index(case)*50+35,tab.index(ligne)*50+35, text = str(case), font = ("Arial", 10))
                

def deplacement_pion(evt):
    '''
    déplace le pion sur la grille en fonction des touches bas, gauche et droite
    :param evt: événement lié à l'action des touches
    '''
    global x_pion
    global y_pion
    global max_mouv
    global le_score
    
    if evt.keysym == "Down" and max_mouv < 28:
        if y_pion < 9: # Pour ne pas sortir du tableau
            score(pion)
            canvas.move(pion, 0, 50)
            y_pion += 1
            max_mouv +=1
    if evt.keysym == "Left" and max_mouv < 28:
        if x_pion > 0: # Pour ne pas sortir du tableau
            score(pion)
            canvas.move(pion, -50, 0)
            x_pion -= 1
            max_mouv+=1
    if evt.keysym == "Right" and max_mouv < 28:
        if x_pion < 9: # Pour ne pas sortir du tableau
            score(pion)
            canvas.move(pion, 50, 0)
            x_pion += 1
            max_mouv+=1
      
    if max_mouv >= 28 :
        canvas.create_text(250, 510, text = "Score : " + str(le_score), font = ("Arial", 12))
        
        
def score(pion):
    '''
    calcule le score en fonction du contenu de la case dans le tableau
    et annule la valeur du tableau si besoin en mettant à l'affichage
    *** sur la case
    :param pion:objet construit dans le programme
    '''
    global le_score
    global canvas
    global x_pion
    global y_pion
    global tableau
    
    (x_haut, y_haut, x_bas, y_bas) = canvas.coords(pion)
    valeur = tableau[y_pion][x_pion]

    if valeur > 0:
        le_score += valeur
        print("score: ", le_score, " - valeur: ", valeur, "tableau[y_pion][x_pion]: ", tableau[y_pion][x_pion])
        canvas.create_text(x_haut, y_haut , text = "***", font = ("Arial", 15))
        tableau[y_pion][x_pion] = 0
        
#programme    
fen = Tk()
fen.geometry("520x600")
fen.title("Plus haut score")
canvas = Canvas(fen, width = 520, height = 520, bg = "yellow")
canvas.pack()
quadrillage()
tableau=tableau_jeu()
affiche_valeurs(tableau)
pion=canvas.create_oval(20,20,50,50, fill = "Blue")
x_pion=0
y_pion=0
max_mouv=0
le_score=0
fen.bind("<KeyPress>", deplacement_pion)
fen.mainloop()

