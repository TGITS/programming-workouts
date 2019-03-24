import random

class Robot(object):

    _letters = list("ABCDEFGHIJKMNOPQRSTUVWXYZ")
    _already_used_names = {}

    def __init__(self):
        random.seed()
        self.reset()

    def reset(self):
        name = "{}{}{}{}{}".format(random.choice(Robot._letters), random.choice(Robot._letters), random.randint(0,9), random.randint(0,9),random.randint(0,9))
        if name in Robot._already_used_names:
            self.reset()
        else:
            Robot._already_used_names.add(name)
            self.name = name


