import filtre


def test_filtrage_0():
    liste_nombres = [1, 0, 5, 0, 6, 3, 7, 0, 0, 9, -81]
    liste_nombres_filtree_attendue = [1, 5, 6, 3, 7, 9, -81]

    assert liste_nombres_filtree_attendue == filtre.filtrer(liste_nombres, 0)


def test_filtrage_5():
    liste_nombres = [1, 0, 5, 0, 6, 3, 5, 7, 0, 0, 9, -81, 5]
    liste_nombres_filtree_attendue = [1, 0, 0, 6, 3, 7, 0, 0, 9, -81]

    assert liste_nombres_filtree_attendue == filtre.filtrer(liste_nombres, 5)
