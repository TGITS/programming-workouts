# On importe volontairement le module 'turtle' de cette manière et non sous la forme `from turtle import turtle`
# Cela permet de bien visualiser les opérations que l'on effectue par rapport à la tortue
import turtle


# On définit le mode de la tortue en mode "standard".
# En mode "standard" :
# Orientation de la tortue vers la droite ("vers l'est"),
# Les angles sont positifs dans le sens inverse des aiguilles d'une montre (sens "trigonométrique")
turtle.mode("standard")
# On positionne la tortue à l'origine (0,0) en la faisant pointer vers la droite
turtle.home()
# On rend la tortue visible
turtle.showturtle()
# On définit la vitesse de la tortue à la vitesse la plus lente
turtle.speed(10)
# On définit la couleur du stylo pour notre dessin
# La couleur correspond à du "MediumPurple"	. En valeur rgb décimal cela donne rgb(147, 112, 219)
turtle.pencolor("#9370DB")
# On définit l'épaisseur du trait
turtle.pensize(2)
# On définit une longuer initiale pour le côté
initial_side_length = 40
# On définit la longueur d'un coté comme une variable
side = initial_side_length
# On définit l'angle duquel la tortue doit tourner
# On veut que l'angle du triangle soit de 60 degrés pour un triangle equilateral
# Par rapport à la position de la tortue, il faut qu'elle tourne vers la gauche de 120 degrés (180 - 60 = 120 degrés)
angle = 120

# On veut dessiner 4 triangles au total, en augmentant à chaque fois leur taille de la valeur de initial_side_length

for j in range(0,4):

    # Dessin d'un triangle
    # La Tortue doit avancer de "side" unités et tourner à gauche de "angle"" degrés , et cela 3 fois
    # Elle doit tourner de 120 degrés par rapport à un angle plat (180 degrés), pour que l'angle du triangle soit bien de 60 degrés
    for i in range(0, 3):
        # On fait avancer la Tortue de "side" unités
        turtle.forward(side)
        # On fait tourner la tortue de "angle" degrés
        turtle.left(angle)

    # Mise à jour de la taille du côté
    side = side + initial_side_length

# On masque la tortue
turtle.hideturtle()
# On empêche la fenêtre de se fermer toute seule
turtle.done()