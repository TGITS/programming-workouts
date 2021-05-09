from pokemon import Pokemon


class Pokedex:

    def __init__(self, pokemons=[]):
        self.pokemons = pokemons

    def __str__(self):
        return "pokemons=\n\t" + '\n\t'.join([str(pokemon) for pokemon in self.pokemons])

    def add(self, pokemon):
        self.pokemons.append(pokemon)
