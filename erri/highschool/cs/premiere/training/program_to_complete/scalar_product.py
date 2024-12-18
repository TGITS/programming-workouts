from random import randint

"""
Produit scalaire de 2 vecteurs de même taille
"""


def create_vector(size: int) -> list[int]:
    """
    Crée un vecetur de taille size en le remplissant avec des valeurs aléatoires entières
    comprises entre 0 et 10
    """
    vec = []
    i = 0
    while i < size:
        vec.append(randint(0, 10))
        i += 1
    return vec


def scalar_product(u: list[int], v: list[int]) -> int:
    """
    Produit scalaire des 2 vecteurs u et v
    """
    # Les 2 vecteurs doivent avoir la même taille
    assert len(u) == len(v)
    max_size = len(u)
    i = 0
    product = 0
    while i < max_size:
        product += u[i] * v[i]
        i += 1

    return product


u = create_vector(4)
print("u:", u)
v = create_vector(4)
print("v:", v)
product = scalar_product(u, v)
print("u*v:", product)
