import pytest
import next_growing_number

def test_not_a_number():
    with pytest.raises(ValueError):
      next_growing_number.compute_next_growing_number("98ABCD")

def test_with_empty_string():
    with pytest.raises(ValueError):
        next_growing_number.compute_next_growing_number("")

def test_next_growing_number_after_9():
    assert "9" == next_growing_number.compute_next_growing_number("8")


def test_next_growing_number_after_9():
    assert "11" == next_growing_number.compute_next_growing_number("9")


def test_next_growing_number_after_10():
    assert "11" == next_growing_number.compute_next_growing_number("10")


def test_next_growing_number_after_18():
    assert "19" == next_growing_number.compute_next_growing_number("18")


def test_next_growing_number_after_19():
    assert "22" == next_growing_number.compute_next_growing_number("19")


def test_next_growing_number_after_99():
    assert "111" == next_growing_number.compute_next_growing_number("99")


def test_next_growing_number_after_2533():
    assert "2555" == next_growing_number.compute_next_growing_number("2533")


def test_next_growing_number_after_123456879():
    assert "123456888" == next_growing_number.compute_next_growing_number(
        "123456879")


def test_next_growing_number_after_11123159995399999():
    assert "11123333333333333" == next_growing_number.compute_next_growing_number(
        "11123159995399999")
