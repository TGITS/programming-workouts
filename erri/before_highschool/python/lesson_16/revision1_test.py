import revision1


def test_valeur_absolue():
    assert 0 == revision1.valeur_absolue(0)
    assert 8 == revision1.valeur_absolue(8)
    assert 13 == revision1.valeur_absolue(-13)


def test_valeur_absolue_2():
    for n in range(-10, 0):
        assert -n == revision1.valeur_absolue(n)


def test_valeur_absolue_3():
    for n in range(0, 10):
        assert n == revision1.valeur_absolue(n)


def test_signe_produit():
    assert "+" == revision1.signe_produit(212, 545)
    assert "+" == revision1.signe_produit(-323, -656)
    assert "-" == revision1.signe_produit(212, -545)
    assert "-" == revision1.signe_produit(-323, 656)
    assert "+" == revision1.signe_produit(0, 0)
    assert "+" == revision1.signe_produit(0, 656)
    assert "+" == revision1.signe_produit(212, 0)
    assert "-" == revision1.signe_produit(0, -656)
    assert "-" == revision1.signe_produit(-212, 0)
