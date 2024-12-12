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
    position: int


def extract_data(input_name: str) -> str:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(line.strip())
    return [int(c) for c in data[0]]


def construct_memory_layout(
    data: list[int],
) -> tuple[list[type[Slot]], list[type[Slot]], list[int]]:
    """Compute the memory layout : a list of Slots representing the File, a list of Slots representing the Free Space and finally the total memory 'disk'"""
    disk = []
    blocks = []
    free_spaces = []
    position = 0
    file_id = 0

    for i, block_size in enumerate(data):
        if i % 2 == 0:  # File
            file = Slot(id=file_id, size=block_size, position=position)
            blocks.append(file)
            disk.extend([file_id] * block_size)
            file_id += 1
        else:
            free_space = Slot(id=-1, size=block_size, position=position)
            free_spaces.append(free_space)
            disk.extend([None] * block_size)
        position += block_size

    return (blocks, free_spaces, disk)


def defragment(
    files: list[type[Slot]], frees_paces: list[type[Slot]], disk: list[int]
) -> list[type[Slot]]:
    defragmented_disk = [None] * len(disk)
    spaces = free_spaces.copy()

    for file in sorted(files, key=lambda f: -f.id):
        moved = False

        for i, space in enumerate(spaces):
            if space.position > file.position:
                break
            if space.size >= file.size:
                position = space.position
                for j in range(file.size):
                    defragmented_disk[position + j] = file.id

                spaces[i] = Slot(
                    id=-1,
                    position=space.position + file.size,
                    size=space.size - file.size,
                )
                if spaces[i].size == 0:
                    spaces.pop(i)
                moved = True
                break

        if not moved:
            position = file.position
            for j in range(file.size):
                defragmented_disk[position + j] = file.id

    return defragmented_disk


def compute_checksum(memory: list[int]) -> int:
    return sum(position * id for position, id in enumerate(memory) if id is not None)


if __name__ == "__main__":
    # data = extract_data("input_test.txt")
    data = extract_data("input.txt")
    # print("data:", data)
    (files, free_spaces, disk) = construct_memory_layout(data)
    defragmented_disk = defragment(files, free_spaces, disk)
    print(
        compute_checksum(defragmented_disk)
    )  # "input_test.txt" => 2858 / "input.txt" => 6389911791746
