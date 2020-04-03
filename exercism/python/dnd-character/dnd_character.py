from random import randint
from math import floor


def modifier(value):
    return int(floor((value - 10) / 2))


class Character:

    def __init__(self):
        self.strength = self.ability()
        self.dexterity = self.ability()
        self.constitution = self.ability()
        self.intelligence = self.ability()
        self.wisdom = self.ability()
        self.charisma = self.ability()
        self.hitpoints = 10 + modifier(self.constitution)

    def ability(self):
        return sum(sorted([randint(1, 6) for i in range(4)])[1:])
