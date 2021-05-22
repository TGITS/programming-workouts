from web_safe_colors import __version__, get_all_web_safe_colors, get_rendered_template
from math import pow

hexadecimal_components = ("00", "33", "66", "99", "CC", "FF")
number_of_hexadecimal_components = len(hexadecimal_components)
total_number_of_colors = pow(number_of_hexadecimal_components, 3)


def test_version():
    assert __version__ == "0.1.0"


def test_should_get_216_web_safe_colors():
    assert total_number_of_colors == len(get_all_web_safe_colors())


def test_first_and_last_colors_from_the_list():
    assert "#000000" == get_all_web_safe_colors()[0]
    assert "#FFFFFF" == get_all_web_safe_colors()[215]


def test_colors_creation():

    for start in hexadecimal_components:
        assert 36 == len(
            [color for color in get_all_web_safe_colors() if color.startswith("#" + start)]
        )

    for start in [s + s for s in hexadecimal_components]:
        assert number_of_hexadecimal_components == len(
            [color for color in get_all_web_safe_colors() if color.startswith("#" + start)]
        )

def test_main():
    assert get_rendered_template().startswith("<!DOCTYPE html>")