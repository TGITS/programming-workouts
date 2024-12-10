# https://github.com/grabovszky/aoc2024/blob/main/solutions/day09/part2.py
from solutions.common import read_input
from .utils import parse_input, compact_disk_part2, compute_checksum


def solve_part2(content):
    files, free_spaces, disk = parse_input(content)
    compacted = compact_disk_part2(files, free_spaces, len(disk))
    return compute_checksum(compacted)


def main():
    DAY_NUMBER = 9
    content = read_input(DAY_NUMBER)
    result = solve_part2(content)
    return result


if __name__ == "__main__":
    main()