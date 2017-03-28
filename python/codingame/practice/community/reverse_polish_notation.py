# https://www.codingame.com/ide/puzzle/reverse-polish-notation
import sys
import math


def is_int(s):
    if s[0] in ('-', '+'):
        return s[1:].isdigit()
    return s.isdigit()

def add(stack):
    length = len(stack)
    if len(stack) > 1:
        head = stack.pop()
        second = stack.pop()
        print("head {} - second to head {}".format(head,second), file=sys.stderr)
        if not is_int(str(head)) or not is_int(str(second)):
            stack.append(error_code)
        else:
            stack.append(str(int(head) +  int(second)))
    else:
        stack.append(error_code)

def print_stack(stack):
    while len(stack) > 0:
        print(stack.pop())

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.
stack = []
#operations = { 'ADD' : add, 'SUB' : sub, 'MUL': mul, 'DIV' : div, 'MOD': mod,'POP': pop, 'DUP':dup, 'SWP':swap, 'ROL' rol}
operations = { 'ADD' : add}
error_code = "ERROR"

n = int(input())
for instruction in input().split():
    print("Read instruction {}".format(instruction), file=sys.stderr)
    if is_int(instruction):
        stack.append(int(instruction))
    else :
        op = operations[instruction]
        op(stack)

print_stack(stack)
