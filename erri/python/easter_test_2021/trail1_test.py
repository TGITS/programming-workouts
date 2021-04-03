import trial1


def test_valeur_absolue():
    assert 0 == trial1.valeur_absolue(0)
    assert 8 == trial1.valeur_absolue(8)
    assert 13 == trial1.valeur_absolue(-13)


def test_valeur_absolue_2():
    for n in range(-10, 0):
        assert -n == trial1.valeur_absolue(n)


def test_valeur_absolue_3():
    for n in range(0, 10):
        assert n == trial1.valeur_absolue(n)
