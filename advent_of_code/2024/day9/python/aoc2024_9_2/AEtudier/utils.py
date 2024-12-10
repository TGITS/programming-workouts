# https://github.com/grabovszky/aoc2024/blob/main/solutions/day09/utils.py

from collections import deque
from dataclasses import dataclass


@dataclass
class File:
    id: int
    size: int
    position: int


@dataclass
class FreeSpace:
    position: int
    size: int


def parse_input(content):
    """Parse input into files, free spaces, and initial disk state.

    Returns:
        Tuple of (files, free_spaces, initial_disk) where:
        - files: List of File objects
        - free_spaces: List of FreeSpace objects
        - initial_disk: List representing initial disk state
    """
    files = []
    free_spaces = []
    disk = []
    pos = 0
    file_id = 0

    # Parse alternating file and space lengths
    for i, length in enumerate(map(int, content.strip())):
        length = int(length)
        if i % 2 == 0:  # File
            files.append(File(file_id, length, pos))
            disk.extend([file_id] * length)
            file_id += 1
        else:  # Free space
            free_spaces.append(FreeSpace(pos, length))
            disk.extend([None] * length)
        pos += length

    return files, free_spaces, disk


def compact_disk_part1(disk):
    """Compact disk for part 1 using deque for efficient block movement."""
    dq = deque(disk)
    result = []

    while dq:
        # Get next block from the left
        block = dq.popleft()
        if block is None:
            # If it's a free space, find the rightmost file block
            while dq and block is None:
                block = dq.pop()
        if block is not None:
            result.append(block)

    # Pad the result with None to maintain disk size
    result.extend([None] * (len(disk) - len(result)))
    return result


def compact_disk_part2(files, free_spaces, disk_size):
    """Compact disk for part 2 by moving whole files."""
    result = [None] * disk_size
    spaces = free_spaces.copy()  # Copy to modify safely

    # Process files from highest ID to lowest
    for file in sorted(files, key=lambda f: -f.id):
        moved = False

        # Try to fit file in each free space from left to right
        for i, space in enumerate(spaces):
            if space.position > file.position:
                break
            if space.size >= file.size:
                # Move file to this space
                pos = space.position
                for j in range(file.size):
                    result[pos + j] = file.id

                # Update free space
                spaces[i] = FreeSpace(
                    space.position + file.size, space.size - file.size
                )
                if spaces[i].size == 0:
                    spaces.pop(i)
                moved = True
                break

        if not moved:
            # Keep file in original position
            pos = file.position
            for j in range(file.size):
                result[pos + j] = file.id

    return result


def compute_checksum(disk):
    # Compute checksum by multiplying file IDs with their positions.
    return sum(pos * fid for pos, fid in enumerate(disk) if fid is not None)