import random
import string

class Robot(object):
    already_used_names = set()

    def __init__(self):
        random.seed()
        self.name = None
        self.reset()

    def generate_random_name(self):
        return "{}{}{}{}{}".format(
            random.choice(string.ascii_uppercase), 
            random.choice(string.ascii_uppercase), 
            random.randint(0,9), 
            random.randint(0,9),
            random.randint(0,9))
    
    def reset(self):
        if self.name :
            Robot.already_used_names.remove(self.name)
        
        self.name = self.generate_random_name()

        if self.name in Robot.already_used_names:
            self.reset()
        else:
            Robot.already_used_names.add(self.name)
            


