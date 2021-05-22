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
# On définit la vitesse de la tortue à la vitesse la plus rapide
turtle.speed(10)
# On définit la couleur du stylo pour notre dessin
# La couleur correspond à du "MediumPurple"	. En valeur rgb décimal cela donne rgb(147, 112, 219)
turtle.pencolor("#9370DB")
# On définit l'épaisseur du trait
turtle.pensize(2)
# On définit la longueur initiale d'un coté comme une variable
side_initial_length = 10
# On définit la longueur de laquelle le côté grandit à chaque fois
step = 5
# On définit l'angle duquel la tortue doit tourner
angle = 90
# On doit dessiner 30 rectangles
# La valeur initial d'un côté est défini dans la variable side_initial_length
side = side_initial_length
for j in range(0, 30):
    # On va dessiner un carré de côté "side"

    # La Tortue doit avancer de 100 unités et tourner à gauche de 90 degrés, et cela 4 fois pour dessiner un carré
    for i in range(0, 4):
        # On fait avancer la Tortue de "side" unités
        turtle.forward(side)
        # On fait tourner la tortue de "angle" degrés
        turtle.right(angle)

    # Une fois le carré dessiné il faut mettre à jour la longueur du côté, en l'augmentant de la valeur de step, avant de passer au dessin suivant
    side = side + step  # On pourrait également écrire sous la forme `side += step`

# On masque la tortue
turtle.hideturtle()
# On empêche la fenêtre de se fermer toute seule
turtle.done()
