def valeur_absolue(n):
    if n >= 0:
        return n
    else:
        return -n


def signe_produit(a, b):
    if a >= 0 and b >= 0:
        return "+"
    if a < 0 and b >= 0:
        return "-"
    if a >= 0 and b < 0:
        return "-"
    if a < 0 and b < 0:
        return "+"
