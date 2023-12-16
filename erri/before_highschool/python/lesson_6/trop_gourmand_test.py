from lesson_6 import trop_gourmand


def test_throw():
    for i in range(1000):
        assert trop_gourmand.throw(6) <= 6
        assert trop_gourmand.throw(6) >= 1


def test_throws():
    lancers = trop_gourmand.throws(3)
    assert len(lancers) == 3
    for elem in lancers:
        assert elem <= 6
        assert elem >= 1
