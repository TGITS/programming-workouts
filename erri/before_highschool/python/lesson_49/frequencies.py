import random


def create_list(size, min, max):
    my_list = []
    for _ in range(size):
        my_list.append(random.randint(min, max))
    return my_list


def count_value(seq, value):
    counter = 0
    for e in seq:
        if e == value:
            counter = counter + 1
    return counter


if __name__ == "__main__":
    to_search = int(input("nombre à rechercher : "))
    values = create_list(20, 0, 10)
    print(values)
    counter_to_search = count_value(values, to_search)
    print(f"nombre de {to_search} : {counter_to_search}")
    print(f"fréquence des {to_search} : {counter_to_search / len(values)}")
