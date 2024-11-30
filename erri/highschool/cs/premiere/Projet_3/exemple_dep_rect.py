from tkinter import *

def deplacement_rectangle(evt):
    (x_haut, y_haut, x_bas, y_bas) = dessin.coords(rectangle)

    if evt.keysym == "Down":
        dessin.move(rectangle, 0, 70)
    if evt.keysym == "Left":
        dessin.move(rectangle, -70, 0)
    if evt.keysym == "Right":
        dessin.move(rectangle, 70, 0)
    if evt.keysym == "Up":
        dessin.move(rectangle, 0, -70)

fen = Tk()
fen.geometry("300x500")
dessin = Canvas(fen, width = 300, height = 400, bg = "yellow")
dessin.pack()
rectangle = dessin.create_rectangle(90, 50, 120, 100, fill = "Blue")
dessin.create_text(150, 350, text = "Deplacement rectangle", font = ("Arial", 20))
fen.bind("<KeyPress>", deplacement_rectangle)
fen.mainloop()