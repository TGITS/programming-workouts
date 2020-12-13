def est_divisible_par_3(n):
    reste = n % 3
    return reste == 0


def est_divisible_par_5(n):
    reste = n % 5
    return reste == 0


def fizzbuzz(n):
    fb = ""
    if est_divisible_par_3(n):
        fb = fb + "Fizz"
    if est_divisible_par_5(n):
        fb = fb + "Buzz"
    if fb == "" :
        fb = str(n)
    return fb


if __name__ == "__main__":
    resultat = ""
    for n in range(1, 101):
        resultat = resultat + fizzbuzz(n) + " "

    print(resultat)
