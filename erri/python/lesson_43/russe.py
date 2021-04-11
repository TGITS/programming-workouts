def multiplication(x, y):
    produit = 0
    while x > 0:
        if x % 2 == 0:
            x = x / 2
            y = y * 2
        else:
            produit = produit + y
            x = x - 1

    return produit
