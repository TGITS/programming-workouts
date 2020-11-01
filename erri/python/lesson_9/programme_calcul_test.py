import programme_calcul


def test_calcul_avec_0():
    assert programme_calcul.calcul(0) == 45
    assert programme_calcul.calcul(8) == 845
    assert programme_calcul.calcul(13) == 1345