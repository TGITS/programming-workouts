def est_pair(nombre):
    """Test si le nombre entier passé en paramètre est pair"""
    return nombre % 2 == 0

def diviseurs(nombre):
    """Donne la liste des diviseurs d'un nombre en dehors de lui-même"""
    diviseurs = []

    for n in range(1,nombre):
        if nombre % n == 0:
            diviseurs.append(n)
    
    return diviseurs

print("Est-ce que 24 est pair ?", est_pair(24))
print("La liste des diviseurs de 6", diviseurs(6))