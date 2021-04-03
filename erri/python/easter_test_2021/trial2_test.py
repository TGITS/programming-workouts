import trial2


def test_somme():
    nombres = [1, 5, 6, 7, 1]
    assert 20 == trial2.somme(nombres)
    assert 55 == trial2.somme(range(0, 11))


def test_moyenne():
    nombres = [5, 5, 5, 4, 6]
    assert 5.0 == trial2.moyenne(nombres)

    nombres = [5, 5, 5]
    assert 5.0 == trial2.moyenne(nombres)

    nombres = [0.0, 20.0, 10, 15, 5, 16, 4]
    assert 10.0 == trial2.moyenne(nombres)
