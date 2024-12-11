from collections import deque
from dataclasses import dataclass


@dataclass
class Slot:
    """A memory Slot
    Free space has an ID of -1
    A file hase an index >= 0
    position is the position of the slot in the memory
    size is the size of the slot
    """

    id: int
    size: int


def extract_data(input_name: str) -> str:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(line.strip())
    return [int(c) for c in data[0]]


def construct_memory_layout(
    data: list[int],
) -> tuple[list[type[Slot]], dict[int, type[Slot]]]:
    """Compute the memory layout : a list of Slots and a dictionnary of Slot by its ID"""
    memory = []
    blocks = {}
    size = len(data)
    i = 0
    block_id = 0
    for i in range(0, size, 2):
        block = Slot(id=block_id, size=data[i])
        memory.append(block)
        blocks[block_id] = block
        block_id += 1

        if i + 1 < size:
            free_space = Slot(id=-1, size=data[i + 1])
            memory.append(free_space)

    return (memory, blocks)


def defragment(
    memory: list[type[Slot]], blocks: dict[int, type[Slot]]
) -> list[type[Slot]]:
    keys = sorted(blocks.keys(), reverse=True)
    defragmented_memory = memory[:]
    for block_id in keys:
        block = blocks[block_id]
        i = 0
        j = defragmented_memory.index(block)
        not_found = True
        while i < j and not_found:
            while defragmented_memory[i].id >= 0:
                i += 1
            if defragmented_memory[i].size == block.size:
                defragmented_memory[i].id = block.id
                defragmented_memory[j].id = -1
                not_found = False
            elif defragmented_memory[i].size > block.size:
                remaining_free_space = defragmented_memory[i].size - block.size
                defragmented_memory[i].id = block.id
                defragmented_memory[i].size = block.size
                defragmented_memory[j].id = -1
                defragmented_memory.insert(
                    i + 1, Slot(id=-1, size=remaining_free_space)
                )
                not_found = False
            else:
                i += 1
    return defragmented_memory


def display_memory(memory: list[type[Slot]]) -> str:
    display = ""
    for slot in memory:
        if slot.id >= 0:
            display += str(slot.id) * slot.size
        else:
            display += "." * slot.size
    return display


def reconstruct_memory(memory: list[type[Slot]]) -> list[int]:
    reconstructed_memory = []
    for slot in memory:
        if slot.id >= 0:
            reconstructed_memory.extend([slot.id] * slot.size)
        else:
            reconstructed_memory.extend([None] * slot.size)
    return reconstructed_memory


# def compute_checksum(memory: list[type[Slot]]) -> int:
#     checksum = 0
#     memory_as_str = display_memory(memory)

#     for i,n in enumerate(memory_as_str):
#         if n != '.':
#             checksum += i * int(n)

#     return checksum

# def compute_checksum(memory: list[type[Slot]]) -> int:
#     checksum = 0
#     position = 0
#     for slot in memory:
#         if slot.id >= 0:
#             checksum += sum([slot.id * n for n in range(position, position+slot.size)])
#         position += slot.size

#     return checksum


def compute_checksum(memory: list[type[Slot]]) -> int:
    return sum(
        position * file_id
        for position, file_id in enumerate(reconstruct_memory(memory))
        if file_id is not None
    )


if __name__ == "__main__":
    # data = extract_data("input_test.txt")
    data = extract_data("input.txt")
    print("data:", data)
    (memory, blocks) = construct_memory_layout(data)
    # print(memory)
    # print("memory:", ''.join(memory))
    # print("Blocks:", only_blocks)
    defragmented_memory = defragment(memory, blocks)
    # print(defragmented_memory)
    # print("Compacted memory: ", compacted_memory)
    # Expected : 00992111777.44.333....5555.6666.....8888..
    # Value :    00992111777.44.333....5555.6666.....8888..
    # print(''.join(memory))
    # print(display_memory(defragmented_memory))
    print(compute_checksum(defragmented_memory))

# 105995726871 too low
# 107537132056 too low
# 188927125610 also incorrect
# 108262115579 also incorrect
# 85937816669
# 6389912757138
# 6389912757138
# Good value : 6,265,268,809,555 ? => 6265268809555 ????
