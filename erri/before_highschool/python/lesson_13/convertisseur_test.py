import convertisseur


def test_conversion_1_pouce():
    assert 2.54 == convertisseur.pouce_vers_centimètre(1)


def test_conversion_10_pouces():
    assert 25.4 == convertisseur.pouce_vers_centimètre(10)


def test_conversion_2_pouces():
    assert 5.08 == convertisseur.pouce_vers_centimètre(2)


def test_conversion_centimètre():
    assert 1 == convertisseur.centimètre_vers_pouce(2.54)
    assert 10 == convertisseur.centimètre_vers_pouce(25.4)