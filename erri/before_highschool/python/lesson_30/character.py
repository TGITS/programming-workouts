from dice import Dice


class Character:

    def __init__(self, name):
        self.name = name
        d6 = Dice(6)
        self.hp = d6.roll() + d6.roll() + 12
        self.hability = d6.roll() + 6
        self.luck = d6.roll() + 6
        self.current_hp = self.hp
        self.current_hability = self.hability
        self.current_luck = self.luck

    def __str__(self):
        return f"Character(name: {self.name}, hp: {str(self.current_hp)}/{str(self.hp)}, hability: {str(self.current_hability)}/{str(self.hability)}, luck: {str(self.current_luck)}/{str(self.luck)})"
