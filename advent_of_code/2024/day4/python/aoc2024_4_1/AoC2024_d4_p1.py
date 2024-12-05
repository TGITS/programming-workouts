# Il faut rechercher sur une ligne XMAS ou SAMX
# Pareil il faut rechercher sur une colonne XMAS ou SAMX, le plus simple serait de transformer une colonne en ligne
# Enfin, il faudrait pouvoir extraire les différentes diagonales, sous forme de chaines et de trouver XMAS ou SAMX dedans
def extract_lines(input_name: str) -> list[str]:
    lines = None
    with open(input_name, "r") as input:
       lines = [line.strip() for line in input.readlines()]

    return lines

def get_columns_from_lines(lines: list[str]) -> list[str]:
    line_size = len(lines[0])
    number_of_lines = len(lines)
    print("line size: ", line_size)
    print("number of lines: ", number_of_lines)
    return ["".join([row[i] for row in lines]) for i in range(line_size)]


def get_diagonales_from_lines(lines: list[str]) -> list[str]:
    line_size = len(lines[0])
    number_of_lines = len(lines)
    fdiag = [[] for _ in range(number_of_lines + line_size - 1)]
    bdiag = [[] for _ in range(len(fdiag))]
    min_bdiag = -number_of_lines + 1

    for x in range(line_size):
        for y in range(number_of_lines):
            fdiag[x+y].append(lines[y][x])
            bdiag[x-y-min_bdiag].append(lines[y][x])

    print(fdiag)
    print(bdiag)
    return ["".join(line) for line in fdiag] + ["".join(line) for line in bdiag]

def count_pattern_in_lines(lines: list[str]) -> int:
    count = 0
    for line in lines:
        count += line.count("XMAS")
        count += line.count("SAMX")
    
    return count

if __name__ == "__main__":
    lines = extract_lines("input.txt")
    print(lines)
    columns = get_columns_from_lines(lines)
    print(columns)
    diagonales = get_diagonales_from_lines(lines)
    print(diagonales)
    all_lines_to_search = lines + columns + diagonales
    print(count_pattern_in_lines(all_lines_to_search))
