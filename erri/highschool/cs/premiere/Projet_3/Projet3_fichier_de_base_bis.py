#créé le 25/11/2024
from tkinter import*

def quadrillage():
    '''
    trace le quadrillage de la feuille de canvas comme dans la figure
    '''
    pass

def tableau_jeu()->list:
    '''
    crée le tableau correspondant à la grille de jeu de la figure
    '''
    tab=[[0]*10 for i in range(9)]
    tab[0][5]=20
    tab[1][9]=30
    tab[2][7]=30
    tab[3][2]=20
    tab[4][0]=10
    tab[5][8]=10
    tab[6][4]=10
    tab[7][3]=30
    tab[8][7]=20
    
    return(tab)
    

def affiche_valeurs(tab:list):
    '''
    affiche dans le quadrillage les valeurs du tableau de jeu autres que 0
    '''
    for k in range(9):
        for h in range(10):
            if tab[k][h] !=0:
                canvas.create_text(35+h*50,35+k*50,text=str(tab[k][h]), font=("Arial",20))




def deplacement_pion(evt):
    '''
    déplace le pion sur la grille en fonction des touches bas, gauche et droite
    :param evt: événement lié à l'action des touches
    '''
    global x_pion
    global y_pion
    global compteur
    global score
    
    
    (x_haut,y_haut,x_bas,y_bas)=canvas.coords(pion)
    if y_pion <8 and compteur<28:
        if evt.keysym == "Down" :
            canvas.move(pion,0,50)
            compteur+=1
            y_pion+=1
            score(canvas.coords(pion))
    if x_pion >0 and compteur<28:
        if evt.keysym == "Left" :
            canvas.move(pion,-50,0)
            compteur+=1
            x_pion+= -1
            score(canvas.coords(pion))
    if x_pion <9 and compteur<28:
        if evt.keysym == "Right" :
            canvas.move(pion,50,0)
            compteur+=1
            x_pion+=1
            score(canvas.coords(pion))
    
    if compteur==28:
        compteur+=1
        Label(fen,text="Ton score est: "+str(score_points) ,font=('arial', 20),fg="White", bg="Green").pack()
        
     

def score(pion):
    '''
    calcule le score en fonction du contenu de la case dans le tableau
    et annule la valeur du tableau si besoin en mettant à l'affichage
    *** sur la case
    :param pion:objet construit dans le programme
    '''
    global score_points
    score_points+=tab[int(pion[1]/50)][int(pion[0]/50)]
    if tab[int(pion[1]/50)][int(pion[0]/50)]!=0:
        canvas.create_text(pion[0]+15,pion[1]+15,text="***",fill="Black",font=("Arial",25))
    tab[int(pion[1]/50)][int(pion[0]/50)]=0
        

#programme    


fen=Tk()
fen.title("Plus haut score")
fen.geometry("520x600")
canvas = Canvas(fen, width=520, height=470, bg="yellow")

x_pion=0
y_pion=0
compteur=0
score_points=0
tab=tableau_jeu()

for i in range(12):
    canvas.create_line(10+i*50,10,10+i*50,460,width=2,fill="Black")
for j in range(12):
    canvas.create_line(10,10+j*50,510,10+j*50,width=2,fill="Black")
canvas.pack()
fen.bind("<KeyPress>",deplacement_pion)
pion=canvas.create_oval(20,20,50,50,fill="Blue")
affiche_valeurs(tableau_jeu())


fen.mainloop()