import convertisseur
import pytest


def test_celsius_vers_fahrenheit():
    assert convertisseur.celsius_vers_fahrenheit(0) == 32
    assert convertisseur.celsius_vers_fahrenheit(100) == 212


def test_fahrenheit_vers_celsius():
    assert convertisseur.fahrenheit_vers_celsius(50) == 10
    assert convertisseur.fahrenheit_vers_celsius(32) == 0
    assert convertisseur.fahrenheit_vers_celsius(212) == 100
    assert convertisseur.fahrenheit_vers_celsius(140) == 60
    assert convertisseur.fahrenheit_vers_celsius(-40) == -40
    assert pytest.approx(
        convertisseur.fahrenheit_vers_celsius(0), 0.001) == -17.778
