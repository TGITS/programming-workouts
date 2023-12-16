def f(x):
    return (x-4) * 7


if __name__ == "__main__":
    nombre = int(input("entrez la valeur de x : "))
    print("la valeur de l'expression (x-4) * 7 est :", f(nombre))

    for x in range(-100, 101):
        print("pour x valant", x, "la valeur de l'expression est :", f(x))
