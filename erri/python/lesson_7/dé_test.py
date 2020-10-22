from dé import Dé


def test_création_dé():
    d6 = Dé(8)
    assert d6.nombre_de_faces == 8
