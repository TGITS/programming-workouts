def est_divisible_par_3(n):
    reste = n % 3
    return reste == 0


def est_divisible_par_5(n):
    reste = n % 5
    return reste == 0


def est_divisible_par_15(n):
    return est_divisible_par_3(n) and est_divisible_par_5(n)


def fizzbuzz(n):
    if est_divisible_par_15(n):
        return "FizzBuzz"
    elif est_divisible_par_3(n):
        return "Fizz"
    elif est_divisible_par_5(n):
        return "Buzz"
    else:
        return str(n)

if __name__=="__main__":
    resultat = ""
    for n in range(1, 101):
        resultat = resultat + fizzbuzz(n) + " "
    
    print(resultat)
