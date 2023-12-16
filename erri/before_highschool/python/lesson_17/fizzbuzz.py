def est_divisible_par_3(n):
    reste = n % 3
    return reste == 0


def est_divisible_par_5(n):
    reste = n % 5
    return reste == 0


def est_divisible_par_15(n):
    return est_divisible_par_3(n) and est_divisible_par_5(n)
