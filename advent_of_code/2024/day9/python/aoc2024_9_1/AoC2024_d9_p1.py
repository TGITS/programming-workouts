def extract_data(input_name: str) -> str:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(line.strip())
    return data[0]


def compute_id_numbers_list(data: str) -> list[str]:
    memory = []
    size = len(data)
    i = 0
    id = 0
    for i in range(0, size, 2):
        block = int(data[i])

        for j in [str(id)] * block:
            memory.append(j)
        id += 1

        if i + 1 < size:
            free_space = int(data[i + 1])
            for j in ["."] * free_space:
                memory.append(j)

    return memory


def compact(memory: list[str]) -> list[str]:
    i = 0
    j = len(memory) - 1

    while i < j:
        while memory[i] != ".":
            i += 1
        while memory[j] == ".":
            j -= 1
        memory[j], memory[i] = memory[i], memory[j]
        i += 1
        j -= 1


def compute_checksum(memory: list[str]) -> int:
    checksum = 0
    for i, cell in enumerate(memory):
        if cell != ".":
            checksum += int(cell) * i

    return checksum


if __name__ == "__main__":
    # data = extract_data("input_test.txt")
    data = extract_data("input.txt")
    # print("data:", data)
    memory = compute_id_numbers_list(data)
    # print(memory)
    # print(''.join(memory))
    compact(memory)
    # print(memory)
    # print(''.join(memory))
    print(compute_checksum(memory))
