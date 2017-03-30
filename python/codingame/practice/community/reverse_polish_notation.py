#https://www.codingame.com/ide/puzzle/reverse-polish-notation
#13
#4 5 MUL 12 SWP SUB 4 5 3 ROL 10 ADD MOD
# 4 5 MUL ==> 20
# 20 12 SWP ==> 12 20
# 12 20 SUB ==> -8
# -8 4 5 3 ROL ==> -8 4 5 ==> 4 5 -8
# 4 5 -8 10 ADD ==> 4 5 2
# 4 5 2 MOD ==> 4
# 4 1

import sys
import math


def is_int(s):
    if s[0] in ('-', '+'):
        return s[1:].isdigit()
    return s.isdigit()

def add(stack):
    length = len(stack)
    print_stack_debug(stack)
    if len(stack) > 1:
        head = stack.pop()
        second = stack.pop()
        if not is_int(str(head)) or not is_int(str(second)):
            manage_error(stack)
        else:
            stack.append(str( int(second) + int(head)))
    else:
        manage_error(stack)
    print_stack_debug(stack)

def sub(stack):
    length = len(stack)
    print_stack_debug(stack)
    if len(stack) > 1:
        head = stack.pop()
        second = stack.pop()
        if not is_int(str(head)) or not is_int(str(second)):
            manage_error(stack)
        else:
            stack.append(str(int(second) - int(head)))
    else:
        manage_error(stack)
    print_stack_debug(stack)

def mul(stack):
    length = len(stack)
    print_stack_debug(stack)
    if len(stack) > 1:
        head = stack.pop()
        second = stack.pop()
        if not is_int(str(head)) or not is_int(str(second)):
            manage_error(stack)
        else:
            stack.append(str(int(second) * int(head)))
    else:
        manage_error(stack)
    print_stack_debug(stack)

def div(stack):
    length = len(stack)
    print_stack_debug(stack)
    if len(stack) > 1:
        head = stack.pop()
        second = stack.pop()
        if not is_int(str(head)) or not is_int(str(second)):
            manage_error(stack)
        else:
            if int(head) != 0:
                stack.append(str(int(second) // int(head)))
            else:
                manage_error(stack)
    else:
       manage_error(stack)
    print_stack_debug(stack)

def mod(stack):
    length = len(stack)
    print_stack_debug(stack)
    if len(stack) > 1:
        head = stack.pop()
        second = stack.pop()
        if not is_int(str(head)) or not is_int(str(second)):
            manage_error(stack)
        else:
            if int(head) != 0:
                stack.append(str(int(second) % int(head)))
            else:
                manage_error(stack)
    else:
        manage_error(stack)
    print_stack_debug(stack)

def pop(stack):
    length = len(stack)
    print_stack_debug(stack)
    if len(stack) > 0:
        head = stack.pop()
        print_stack_debug(stack)
    else:
        manage_error(stack)
    print_stack_debug(stack)

def dup(stack):
    length = len(stack)
    print_stack_debug(stack)
    if len(stack) > 0:
        head = stack.pop()
        stack.append(head)
        stack.append(head)
        print_stack_debug(stack)
    else:
       manage_error(stack)
    print_stack_debug(stack)

def swap(stack):
    length = len(stack)
    if len(stack) > 1:
        head = stack.pop()
        second = stack.pop()
        stack.append(head)
        stack.append(second)
        print_stack_debug(stack)
    else:
        manage_error(stack)
    print_stack_debug(stack)

#Implémentation correcte à réaliser
def rol(stack):
    length = len(stack)
    if len(stack) > 3:
        stack.pop()
        head = stack.pop()
        second = stack.pop()
        third = stack.pop()
        stack.append(second)
        stack.append(head)
        stack.append(third)
        print_stack_debug(stack)
    else:
       manage_error(stack)
    print_stack_debug(stack)

def manage_error(stack):
    del stack[:]
    stack.append(error_code)

def stack_to_str(stack):
    resultat = ""
    for elem in stack:
        resultat = resultat + str(elem) + " "
    return resultat.strip()

def print_stack(stack):
    print(stack_to_str(stack))

def print_stack_debug(stack):
    print("[DEBUG] Stack : {}".format(stack_to_str(stack)), file=sys.stderr)

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.
stack = []
operations = { 'ADD' : add, 'SUB' : sub, 'MUL': mul, 'DIV' : div, 'MOD': mod,'POP': pop, 'DUP':dup, 'SWP': swap, 'ROL': rol}
error_code = "ERROR"

n = int(input())
for instruction in input().split():
    print("Read instructions {}".format(instruction), file=sys.stderr)
    if is_int(instruction):
        stack.append(instruction)
    else :
        if instruction in operations:
            op = operations[instruction]
            op(stack)
        else:
            manage_error(stack)

    if stack[-1] == error_code:
        break;

print_stack_debug(stack)
print_stack(stack)
