
def compute_mean():
    """
    Demande à l'utilisateur 2 nombres, calcule leur moyenne et affiche cette moyenne à l'utilisateur
    """

    x = float(input('entrer un premier nombre: '))
    y = float(input('entrer un second nombre: '))
    m = (x+y)/2
    print(m)

if __name__ == "__main__":
    compute_mean()