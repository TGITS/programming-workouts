from dataclasses import dataclass

@dataclass
class File:
    position: int
    id: int
    size: int
    
@dataclass
class Space:
    position: int
    size: int

def extract_data(input_name: str) -> str:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(line.strip())
    return data[0]


def compute_id_numbers_list(data: str) -> tuple[list[str], dict[int, str], int]:
    
    size = len(data)
    blocks = {}
    block_id = 0
    spaces = {}
    for i in range(0, size, 2):
        block_size = int(data[i])
        block = File(position=i, id=block_id, size=block_size)
        blocks[block_id] = block
        block_id += 1

        if i + 1 < size:
            space = Space(i, int(data[i + 1]))
            spaces[id] = space

    return (memory, only_blocks)


def compact(memory: list[str], only_blocks: dict[int, str]) -> str:
    working_copy = "".join(memory)
    keys = sorted(only_blocks.keys(), reverse=True)
    # print("Keys:", keys)
    for block_id in keys:
        block_size = only_blocks[block_id]
        pattern = "." * block_size
        block = "".join([str(block_id)]*block_size)
        index_block = working_copy.rfind(block)
        index_first_dot = working_copy.find(".", 0, index_block)
        index_pattern = working_copy.find(pattern, index_first_dot, index_block)

        if index_pattern > 0:
            working_copy = (
                working_copy[0:index_pattern]
                + block
                + working_copy[index_pattern + len(block) : index_block]
                + pattern
                + working_copy[index_block + len(pattern) :]
            )

    return working_copy


def compute_checksum(memory: list[str]) -> int:
    # print("memory as list:", memory)
    checksum = 0
    for i, cell in enumerate(memory):
        if cell != ".":
            checksum += int(cell) * i

    return checksum


if __name__ == "__main__":
    # data = extract_data("input_test.txt")
    data = extract_data("input.txt")
    # print("data:", data)
    (memory, only_blocks) = compute_id_numbers_list(data)
    # print(memory)
    # print("memory:", ''.join(memory))
    # print("Blocks:", only_blocks)
    compacted_memory = compact(memory, only_blocks)
    # print("Compacted memory: ", compacted_memory)
    # Expected : 00992111777.44.333....5555.6666.....8888..
    # Value :    00992111777.44.333....5555.6666.....8888..
    # print(''.join(memory))
    print(compute_checksum(list(compacted_memory)))

# 105995726871 too low
# 107537132056 too low
# 188927125610 also incorrect
# Good value : 6,265,268,809,555
# 6265268809555
