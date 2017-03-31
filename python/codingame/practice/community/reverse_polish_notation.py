#https://www.codingame.com/ide/puzzle/reverse-polish-notation

import sys
import math


def is_int(s):
    if s[0] in ('-','+'):
        return s[1:].isdigit()
    return s.isdigit()

def add(stack):
    print_stack_debug(stack)
    if len(stack) > 1:
        head = int(stack.pop())
        second = int(stack.pop())
        stack.append(str(second + head))
    else:
        manage_error(stack)
    print_stack_debug(stack)

def sub(stack):
    print_stack_debug(stack)
    if len(stack) > 1:
        head = int(stack.pop())
        second = int(stack.pop())
        stack.append(str(second - head))
    else:
        manage_error(stack)
    print_stack_debug(stack)

def mul(stack):
    print_stack_debug(stack)
    if len(stack) > 1:
        head = int(stack.pop())
        second = int(stack.pop())
        stack.append(str(second * head))
    else:
        manage_error(stack)
    print_stack_debug(stack)

def div(stack):
    print_stack_debug(stack)
    if len(stack) > 1:
        head = int(stack.pop())
        second = int(stack.pop())
        if head != 0:
            stack.append(str(second // head))
        else:
            manage_error(stack)
    else:
       manage_error(stack)
    print_stack_debug(stack)

def mod(stack):
    print_stack_debug(stack)
    if len(stack) > 1:
        head = int(stack.pop())
        second = int(stack.pop())
        if head != 0:
            stack.append(str(second % head))
        else:
            manage_error(stack)
    else:
        manage_error(stack)
    print_stack_debug(stack)

def pop(stack):
    print_stack_debug(stack)
    if len(stack) > 0:
        head = stack.pop()
        print_stack_debug(stack)
    else:
        manage_error(stack)
    print_stack_debug(stack)

def dup(stack):
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
    if len(stack) > 1:
        head = stack.pop()
        second = stack.pop()
        stack.append(head)
        stack.append(second)
        print_stack_debug(stack)
    else:
        manage_error(stack)
    print_stack_debug(stack)

def rol(stack):
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
    stack.insert(0,error_code)

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
if n>99:
    print("Number of instructions {}".format(n), file=sys.stderr)
    print("More instructions than the limit {}".format(n), file=sys.stderr)
    manage_error(stack)
else:
    instructions = input().split()
    if len(instructions) != n :
        print("The given number of instructions ({}) and the real number of instructions ({}) are different".format(n,len(instructions)), file=sys.stderr)
        manage_error(stack)
    else :
        for instruction in instructions:
            print("Instruction : {}".format(instruction), file=sys.stderr)
            if is_int(instruction):
                stack.append(instruction)
            else :
                if instruction in operations:
                    op = operations[instruction]
                    op(stack)
                else:
                    manage_error(stack)
                if len(stack) > 0 and stack[-1] == error_code:
                    break

print_stack_debug(stack)
print_stack(stack)
