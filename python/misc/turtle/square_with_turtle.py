import turtle

print("Les coordonnées de départ de la tortue sont : ", str(turtle.position()))

print("La couleur du trait est rouge et celle de remplissage est jaune")
turtle.color('red', 'yellow')

# Définissons le démarrage du remplissage
turtle.begin_fill()

# Assurons-nous que la tortue va tracer
turtle.pendown()

# Définissons l'angle et la longueur initiale
angle = 90.0
longueur = 200.0

for i in range(0,4):
    if i > 0 :
        # On ne fait pas tourner la tortue au premier passage
        print("La tortue tourne d'un angle de  ", angle, "°") 
        turtle.left(angle)
    print("La tortue avance de  ", longueur, "pixels") 
    turtle.forward(longueur)
    print("Les coordonnées de la tortue sont maintenant : ", str(turtle.position()))
    
# Indiquons qu'on a fini de définir la zone de remplissage et qu'il faut remplir
turtle.end_fill()

# Masquons la tortue
turtle.hideturtle()

# Indiquons que c'est fini et bloquons l'affichage de l'écran
turtle.done()