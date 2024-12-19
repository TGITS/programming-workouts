from collections import deque

REGISTER_A = "Register A: "
REGISTER_B = "Register B: "
REGISTER_C = "Register C: "
PROGRAM = "Program: "


def extract_data(input_name: str) -> list[str]:
    register_A = None
    register_B = None
    register_C = None
    program = []
    with open(input_name, "r") as input:
        lines = input.readlines()
        current_line_index = 0
        register_A = int(lines[current_line_index].strip().removeprefix(REGISTER_A))
        current_line_index += 1
        register_B = int(lines[current_line_index].strip().removeprefix(REGISTER_B))
        current_line_index += 1
        register_C = int(lines[current_line_index].strip().removeprefix(REGISTER_C))
        current_line_index += 2
        raw_program = lines[current_line_index].strip().removeprefix(PROGRAM).split(",")
        for instruction in raw_program:
            program.append(int(instruction))

    return register_A, register_B, register_C, program


def combo(operand: int) -> int:
    global register_A
    global register_B
    global register_C
    if operand in [0, 1, 2, 3]:
        return operand
    if operand == 4:
        return register_A
    if operand == 5:
        return register_B
    if operand == 6:
        return register_C
    if operand == 7:
        return None


def display_instruction(opcode: int, operand: int) -> str:
    opcode_to_str = {
        0: "adv",
        1: "bxl",
        2: "bst",
        3: "jnz",
        4: "bxc",
        5: "out",
        6: "bdv",
        7: "cdv",
    }
    return opcode_to_str[opcode] + " " + str(operand)


def run_program(program: list[int]) -> tuple[list[int], bool]:
    global register_A
    global register_B
    global register_C
    program_normal_ending = True

    instruction_pointer = 0
    program_length = len(program)
    output = []
    loop_already_done = set()
    while instruction_pointer < program_length and program_normal_ending:
        opcode = program[instruction_pointer]
        operand = program[instruction_pointer + 1]

        if opcode == 0:  # adv
            numerator = register_A
            denominator = combo(operand)
            if not denominator is None:
                register_A = numerator // (2**denominator)
                instruction_pointer += 2
            else:
                # Error - We stop the program and display its current value
                program_normal_ending = False
                break
        elif opcode == 1:  # bxl
            register_B = register_B ^ operand
            instruction_pointer += 2
        elif opcode == 2:  # bst
            combo_operand = combo(operand)
            if not combo_operand is None:
                register_B = combo_operand % 8
                instruction_pointer += 2
            else:
                # Error - We stop the program and display its current value
                program_normal_ending = False
                break
        elif opcode == 3:  # jnz
            if register_A == 0:
                instruction_pointer += 2
            else:
                if operand < program_length:
                    if (
                        not (operand, register_A, register_B, register_C)
                        in loop_already_done
                    ):
                        instruction_pointer = operand
                        loop_already_done.add(
                            (instruction_pointer, register_A, register_B, register_C)
                        )
                    else:
                        program_normal_ending = False
                        break
                else:
                    # Error - We stop the program and display its current value
                    program_normal_ending = False
                    break
        elif opcode == 4:  # bxc
            register_B = register_B ^ register_C
            instruction_pointer += 2
        elif opcode == 5:  # out
            combo_operand = combo(operand)
            if not combo_operand is None:
                output.append(combo_operand % 8)
                instruction_pointer += 2
            else:
                # Error - We stop the program and display its current value
                program_normal_ending = False
                break
        elif opcode == 6:  # bdv
            numerator = register_A
            denominator = combo(operand)
            if not denominator is None:
                register_B = numerator // (2**denominator)
                instruction_pointer += 2
            else:
                # Error - We stop the program and display its current value
                program_normal_ending = False
                break
        elif opcode == 7:  # cdv
            numerator = register_A
            denominator = combo(operand)
            if not denominator is None:
                register_C = numerator // (2**denominator)
                instruction_pointer += 2
            else:
                # Error - We stop the program and display its current value
                program_normal_ending = False
                break

    return output, program_normal_ending


def find_A_value(program: list[int]):
    global register_A
    global register_B
    global register_C

    candidate_values = deque()
    candidate_values.append(0)

    for i in range(1, len(program) + 1):
        candidate_value = candidate_values.popleft() * 8
        print(candidate_value)
        for j in range(8):
            value_to_test = candidate_value + j
            print("value to test:", value_to_test)
            register_A = value_to_test
            print("Register A:", register_A)
            output, program_normal_ending = run_program(program)
            print("output:", output)
            print("program:", program)
            print("output[0] == program[-i]:", output[0] == program[-i])
            print("output[0]:", output[0])
            print("program[-i]:", program[-i])
            if program_normal_ending and output[0] == program[-i]:
                print("candidate value:", value_to_test)
                candidate_values.append(value_to_test)

    print(candidate_values)
    return min(candidate_values)

def find_value(program:list[int], regA:int=0, position:int=0) -> int:
    global register_A
    if position == len(program): return regA
    for i in range(8):
        register_A = regA * 8 + i
        output, program_normal_ending = run_program(program)
        if len(output) and output[0] == program[-(position + 1)]:
            ret_val = find_value(program, (regA * 8 + i), position + 1)
            if ret_val: return ret_val

# On a 16 instructions de 3 bits à chaque fois (une instruction un nombre compris entre 0 et 2**3 - 1)
# Sachant que le programme fait une boucle en réduisant A de 3 bits à chaque tour ( division par 2**3 soit un décalage de 3 bits)
#
# La dernière instruction correspond à une valeur de A compris entre 0 et 7
# Pour l'instruction suivante il faut multiplier la ou les valeurs par 8
# Et ainsi de suite, on va multiplier par 8, 16, fois, avec une liste de valeurs candidates à chaque fois.
# Il faut qu'on prenne la plus petite des valeurs candidates possibles
if __name__ == "__main__":
    # Expected value for register A : 117440
    register_A, register_B, register_C, program = extract_data("input_test.txt")
    # register_A, register_B, register_C, program = extract_data("input.txt")
    print("Register A:", register_A)
    print("Register B:", register_B)
    print("Register C:", register_C)
    print("Program:", program)
    # print(find_value(program))
    value_for_A = find_A_value(program)
    print(value_for_A)
    # register_A = 117440
    # print(run_program(program))
