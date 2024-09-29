def calcule_parallelepipede(longueur: float, largeur: float, hauteur: float) -> float:
    """
    Calcule et renvoie le volume d'un parallélépipède à partir de sa longueur, sa largeur et sa hauteur fournies en paramètre.
    :param longueur (float): longueur du parallélépipède
    :param largeur (float): largeur du parallélépipède
    :param hauteur (flaot): hauteur du parallélépipède
    :return (float): le volume du parallélépipède
    """
    return longueur * largeur * hauteur

def saisi(message: str) -> float:
    """
    Affiche le contenu du paramètre message à l'utilisateur et attend sa saisie.
    Le valeur saisie par l'utilisateur est convertie en float et est retournée par la fonction
    :param message (str): le message affiché à l'utilisateur
    :return (float): la valeur saisie par l'utilisateur et convertie en float
    """
    return float(input(message))

def affiche(valeur:float):
    print(f"Le volume est : {valeur}")

if __name__ == "__main__":
    longueur = saisi("Saisir la longueur : ")
    largeur = saisi("Saisir la largueur : ")
    hauteur = saisi("Saisir la hauteur : ")
    volume = calcule_parallelepipede(longueur, largeur, hauteur)
    affiche(volume)