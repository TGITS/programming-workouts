# Problème de TFJM 2026 - voir https://tfjm.org/les-problemes/ et https://tfjm.org/wp-content/uploads/2026/01/index.pdf
#
# On appelle nombre permutatif un nombre de N∗ dont le double est constitué des mêmes chiffres dans un ordre quelconque,
# sauf un unique chiffre (compris entre 0 et 4) qui est remplacé par son double.
#
# Par exemple, 2×163 = 326 : son double est constitué des mêmes chiffres, sauf un chiffre 1 qui est remplacé par un chiffre 2.
# En revanche, le nombre 11 n’est pas permutatif, car pour obtenir 2×11 = 22 il faudrait remplacer deux de ses chiffres par leurs doubles.
# Parmi ceux-là, on appelle :
# - nombre rotatif un nombre tel que la permutation consiste en faire passer le premier chiffre en dernier, et c'est ce chiffre qui est doublé. Par exemple, 253 serait rotatif si son double était 534.
# - nombre fitator un nombre tel que la permutation consiste en faire passer le dernier chiffre en premier, et c’est ce chiffre qui est doublé. Par exemple, 253 serait fitator si son double était 625.
# Le nombre 163 est permutatif mais n’est ni rotatif ni fitator.


def is_rotatif(n: int) -> bool:
    if n < 10:
        return False

    double_n = 2 * n
    str_n = str(n)
    str_double_n = str(double_n)

    if int(str_n[0]) > 4:
        return False

    # An alternative way to do it
    # expected_str_n = str(int(str_double_n[-1]) // 2) + str_double_n[0:-1]
    # return expected_str_n == str_n
    expected_str_double_n = str_n[1:] + str(int(str_n[0]) * 2)
    return expected_str_double_n == str_double_n


def rotatif_numbers_below(limit: int) -> list[int]:
    return [n for n in range(limit) if is_rotatif(n)]


def is_fitator(n: int) -> bool:
    if n < 10:
        return False

    double_n = 2 * n
    str_n = str(n)
    str_double_n = str(double_n)

    if int(str_n[-1]) > 4:
        return False

    # An alternative way to do it
    # expected_str_n =  str_double_n[1:] + str(int(str_double_n[0]) // 2)
    # return expected_str_n == str_n
    expected_str_double_n = str(int(str_n[-1]) * 2) + str_n[0:-1]
    return expected_str_double_n == str_double_n


def fitator_numbers_below(limit: int) -> list[int]:
    return [n for n in range(limit) if is_fitator(n)]


def is_permutatif(n: int) -> bool:
    if n < 10:
        return False

    double_n = 2 * n
    str_n = sorted(str(n))
    str_double_n = sorted(str(double_n))

    expected_str_double_n = []
    count_below_5 = len([digit for digit in str_n if digit in "01234"])
    i = 0
    while i < count_below_5:
        expected_str_double_n.append(
            str_n[0:i] + [str(int(str_n[i]) * 2)] + str_n[i + 1 :]
        )
        i += 1

    return any(
        expected_str_double_n == str_double_n
        for expected_str_double_n in expected_str_double_n
    )


def permutatif_numbers_below(limit: int) -> list[int]:
    return [n for n in range(limit) if is_permutatif(n)]


def main():
    print("Hello from nombre-permutatif!")
    limit = 100_000
    print("Rotatif numbers below", limit, ":", rotatif_numbers_below(limit))
    print("Fitator numbers below", limit, ":", fitator_numbers_below(limit))
    print("Permutatif numbers below", limit, ":", permutatif_numbers_below(limit))


if __name__ == "__main__":
    main()
