from tri import tri, minimum, minimum_avec_indice


def test_tri():
    tableau = [10, 1, 7, 9, 8]
    tableau_attendu = [1, 7, 8, 9, 10]
    assert tableau_attendu == tri(tableau)


def test_min():
    tableau = [10, 1, 7, 9, 8]
    minimum_attendu = 1
    assert minimum_attendu == minimum(tableau)

def test_min_index():
    tableau = [10, 1, 7, 9, 8]
    minimum_attendu = 1
    indice_minimum_attendu = 1
    valeur, indice = minimum_avec_indice(tableau)
    assert minimum_attendu == valeur
    assert indice_minimum_attendu == indice
