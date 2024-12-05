import re


def extract_lines(input_name: str) -> list[str]:
    lines = None
    with open(input_name, "r") as input:
        lines = [line.strip() for line in input.readlines()]

    return lines


def count_x_mas(line1: str, line2: str, line3: str) -> bool:
    count = 0
    a_pattern = r".A."
    m_m_pattern = r"M.M"
    m_s_pattern = r"M.S"
    s_m_pattern = r"S.M"
    s_s_pattern = r"S.S"
    if (
        re.search(m_s_pattern, line1)
        and re.search(a_pattern, line2)
        and re.search(m_s_pattern, line3)
    ):
        count += 1
    if (
        re.search(m_m_pattern, line1)
        and re.search(a_pattern, line2)
        and re.search(s_s_pattern, line3)
    ):
        count += 1
    if (
        re.search(s_s_pattern, line1)
        and re.search(a_pattern, line2)
        and re.search(m_m_pattern, line3)
    ):
        count += 1
    if (
        re.search(s_m_pattern, line1)
        and re.search(a_pattern, line2)
        and re.search(s_m_pattern, line3)
    ):
        count += 1

    return count


def count_all_patterns(lines: list[str]) -> int:
    line_size = len(lines[0])
    print(line_size)
    number_of_lines = len(lines)
    print(number_of_lines)
    count = 0
    for j in range(number_of_lines - 3):
        for i in range(line_size - 3):
            part1 = lines[j][i : i + 3]
            part2 = lines[j + 1][i : i + 3]
            part3 = lines[j + 2][i : i + 3]
            count += count_x_mas(part1, part2, part3)

    return count


def count_patterns(lines: list[str]) -> int:
    line_size = len(lines[0])
    number_of_lines = len(lines)
    count = 0
    for j in range(1, number_of_lines - 1):
        for i in range(1, line_size - 1):
            if lines[j][i] == "A":
                if (
                    lines[j - 1][i - 1] == "M"
                    and lines[j - 1][i + 1] == "S"
                    and lines[j + 1][i - 1] == "M"
                    and lines[j + 1][i + 1] == "S"
                ) or (
                    lines[j - 1][i - 1] == "M"
                    and lines[j - 1][i + 1] == "M"
                    and lines[j + 1][i - 1] == "S"
                    and lines[j + 1][i + 1] == "S"
                ) or (
                    lines[j - 1][i - 1] == "S"
                    and lines[j - 1][i + 1] == "S"
                    and lines[j + 1][i - 1] == "M"
                    and lines[j + 1][i + 1] == "M"
                ) or (
                    lines[j - 1][i - 1] == "S"
                    and lines[j - 1][i + 1] == "M"
                    and lines[j + 1][i - 1] == "S"
                    and lines[j + 1][i + 1] == "M"
                ):
                    count += 1
    return count


if __name__ == "__main__":
    lines = extract_lines("input.txt")
    #print(lines)
    #print(count_all_patterns(lines))
    print(count_patterns(lines))
