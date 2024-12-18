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


def run_program(program: list[int]) -> tuple[str, bool]:
    global register_A
    global register_B
    global register_C
    program_normal_ending = True

    instruction_pointer = 0
    program_length = len(program)
    output = []
    while instruction_pointer < program_length and program_normal_ending:
        opcode = program[instruction_pointer]
        operand = program[instruction_pointer + 1]
        print(display_instruction(opcode, operand))
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
                    instruction_pointer = operand
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
                output.append(str(combo_operand % 8))
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
        print("########### cycle ###########")
        print("Instruction pointer:", instruction_pointer)
        print("Register A:", register_A)
        print("Register B:", register_B)
        print("Register C:", register_C)
        print("Program output: ", output)
    return ",".join(output), program_normal_ending


if __name__ == "__main__":
    # register_A, register_B, register_C,program = extract_data("input_test.txt")
    register_A, register_B, register_C, program = extract_data("input.txt")
    print("Register A:", register_A)
    print("Register B:", register_B)
    print("Register C:", register_C)
    print("Program:", program)
    output, program_normal_ending = run_program(program)
    print("Register A:", register_A)
    print("Register B:", register_B)
    print("Register C:", register_C)
    print("Program output", output)
    print("Program normal ending:", program_normal_ending)
