import tableau


def test_séparation_positif_negatif():
    input = [0, -1, 2, -2, 1, -3, 5, -9]
    t_positif = [0, 2, 1, 5]
    t_negatif = [-1, -2, -3, -9]
    r_t_positif, r_t_negatif = tableau.séparation_positif_negatif(input)
    assert(t_positif == r_t_positif)
    assert(t_negatif == r_t_negatif)
