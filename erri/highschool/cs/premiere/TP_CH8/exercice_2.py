# Écrire la fonction ressemble(num, ref) qui teste si le numéro num qui est une chaîne de caractères contenant 10 caractères exactement dont éventuellement des étoiles 
# peut correspondre au numéro ref qui est une chaîne de caractères contenant 10 caractères chiffres exactement.

def ressemble(num: str, ref:str) -> bool:
    '''Prédicat qui teste si un numéro avec des caractères éventuellement masqués par des "*" correspond à un numéro de référence

    num : le numéro à tester
    ref : le numéro de référence

    num et ref doivent faire la même taille, si ce n'est pas le cas la fonction retourne False
    '''
    # On s'assure que les 2 chaines ont bien la même taille
    if (len(num) != len(ref)):
        return False
    
    size = len(num)
    i = 0
    # L'idée est de comparer la caractères à la même position dans chacune des chaines
    # Si les 2 caractères sont égaux, ou si cela correspond au caractère "*", on peut continuer
    # Ce qui signifie que si les 2 caractères sont différents et que le caractère dans le numéro à tester n'est pas une "*"
    # alors on poeut retourner False directement
    # Par cotnre si on arrive au bout de la boucle sans être sortie, c'est qu'on doit retourner True 
    while i < size:
        if (num[i] != ref[i]) and (num[i] != "*"):
            return False
        i += 1

    return True

print(ressemble("0231**4556","0431674556"))
print(ressemble("0231**4556","0231674556"))