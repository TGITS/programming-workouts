from dice import Dice


class Character:

    def __init__(self, name):
        self.name = name
        d6 = Dice(6)
        self.hp = d6.roll() + d6.roll() + 12
        self.hability = d6.roll() + 6
        self.luck = d6.roll() + 6
        self.current_hp = self.hp

    def __str__(self):
        return "Character(name:" + self.name + ", hp:" + str(self.current_hp) + "/" + str(self.hp) + ", hability:" + str(self.hability) + ", luck:" + str(self.luck) + ")"
