def compute_mean(x: float, y: float) -> float:
    """
    Calcule et retourne la moyenne des 2 nombres réels donnés en paramètres
    """
    return (x+y)/2

def get_float_from_user(message: str) -> float:
    return float(input(message))


if __name__ == "__main__":
    x = get_float_from_user('entrer un premier nombre: ')
    y = get_float_from_user('entrer un second nombre: ')
    print(compute_mean(x,y))