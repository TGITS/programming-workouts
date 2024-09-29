from math import pi

def volume_cylindre(hauteur:float, rayon:float) -> float:
    """
    hauteur: nombre réel représentant la hauteur du cylindre
    rayon: nombre réel représentant le rayon de la base du cylindre
    calcule et retourne le volume du cylindre ayant pour hauteur "hauteur" et comme base le disque de rayon "rayon"
    le volume d'un cylindre V de hauteur h et de rayon de base r est donné par la formule V = PI * R^2 * h
    """
    return pi * rayon **2 * hauteur

if __name__ == "__main__":
    h = float(input('entrer la hauteur du cylindre: '))
    r = float(input('entrer le rayon de la base du cylindre: '))
    print(volume_cylindre(h,r))