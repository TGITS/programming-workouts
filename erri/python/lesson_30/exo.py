def f(x):
    return 6*x + 18*(1200-x)


res = 16200
for x in range(0, 1201):
    calcul = f(x)
    if 16200 == calcul:
        print(x, 1200-x, calcul)
