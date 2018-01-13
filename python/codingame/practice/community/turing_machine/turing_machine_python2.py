import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

RIGHT="R"
LEFT="L"
HALT="HALT"

class Tape:
    def __init__(self,tape_length, initial_head_position):
        self.tape_length = tape_length
        self.initial_head_position = initial_head_position
        self.current_head_position = initial_head_position
        self.content = []
        self.number_of_actions = 0
        for i in range(0,tape_length):
            self.content.append(0)

    def write(self,symbol):
        self.content[self.current_head_position] = symbol

    def read(self):
        return self.content[self.current_head_position]

    def moveHead(self,direction, next_state):
        if direction == RIGHT:
            #Are we at the end of the tape
            if self.current_head_position == tape_length-1:
                #can't go further
                self.current_head_position = tape_length
                return HALT
            self.current_head_position += 1
            return next_state
        if direction == LEFT:
            if self.current_head_position == 0:
                #can't go further
                self.current_head_position = -1
                return HALT
            self.current_head_position -= 1
            return next_state

    def incNumberOfActions(self):
        self.number_of_actions += 1

    def dump(self):
        tape_to_string = ""
        for item in self.content:
            tape_to_string += str(item)
        return tape_to_string

class Action:
    def __init__(self, symbol_to_write, direction, next_state):
        self.symbol_to_write = symbol_to_write
        self.direction = direction
        self.next_state = next_state

    def do(self,tape):
        tape.write(self.symbol_to_write)
        next_action = tape.moveHead(self.direction,self.next_state)
        tape.incNumberOfActions()
        return next_action

    def __repr__(self):
        return self.symbol_to_write + ' ' + self.direction + ' ' + self.next_state

    def __str__(self):
        return self.symbol_to_write + ' ' + self.direction + ' ' + self.next_state

class State:
    def __init__(self,name_actions):
        temp_list = name_actions.split(':')
        self.name = temp_list[0]
        actions = temp_list[1].split(';')
        self.actions = []
        for action in actions:
            temp = action.split()
            self.actions.append(Action(temp[0],temp[1],temp[2]))

    def do(self,tape):
        action_number = int(tape.read())
        return self.actions[action_number].do(tape)

    def __repr__(self):
        representation = self.name + ' : '
        for item in self.actions:
            representation += str(item) + ';'
        return representation

    def __str__(self):
        representation = self.name + ' : '
        for item in self.actions:
            representation += str(item) + ';'
        return representation

number_of_symbols, tape_length, head_initial_position = [int(i) for i in raw_input().split()]
start = raw_input()
number_of_states = int(input())
print >> sys.stderr, "Number of symbols : {} - Tape length : {} - Head initial position : {}".format(number_of_symbols, tape_length, head_initial_position)
print >> sys.stderr, "Start symbol : {} - Number of states : {}".format(start, number_of_states)
tape = Tape(tape_length, head_initial_position)
statesByName = {}
for i in xrange(number_of_states):
    stateactions = raw_input()
    print >> sys.stderr, "Actions associated with state {}".format(stateactions)
    state = State(stateactions)
    statesByName.update({state.name : state})

current_state = statesByName[start]
current_state_name = start
print >> sys.stderr, "current state : {}".format(current_state)

while current_state_name != HALT:
    current_state_name = current_state.do(tape)
    if current_state_name != HALT:
        current_state = statesByName[current_state_name]

print(tape.number_of_actions)
print(tape.current_head_position)
print(tape.dump())
# Write an action using print
# To debug: print >> sys.stderr, "Debug messages..."

#print >> sys.stderr, "Number of solutions : {}".format(number_of_solutions)
#print("{}".format(number_of_solutions))
