from itertools import combinations


def extract_data(filename):
    """Read raw data and transform them in a easily processable form"""
    with open(filename) as file:
        input = file.read().splitlines()
    tiles = tuple(tuple(int(x) for x in line.split(",")) for line in input)
    return tiles


def extend(axis1, axis2):
    extended_axis1 = {}
    index2 = sorted(axis2.keys())
    for x in sorted(axis1.keys()):
        prev_cross = 0
        start, end = axis1[x]
        inside = False
        for y in index2:
            if y == start:
                if inside:
                    start = prev_cross
                break
            if axis2[y][0] < x < axis2[y][1]:
                inside ^= True
                prev_cross = y

        inside = False
        for y in reversed(index2):
            if y == end:
                if inside:
                    end = prev_cross
                break
            if axis2[y][0] < x < axis2[y][1]:
                inside ^= True
                prev_cross = y
        extended_axis1[x] = (start, end)
    return extended_axis1


def solve_part1(filename):
    red_tiles = extract_data(filename)
    max_area = 0
    for a, b in combinations(red_tiles, 2):
        current_area = (abs(b[0] - a[0]) + 1) * (abs(b[1] - a[1]) + 1)
        if current_area > max_area:
            max_area = current_area
    return max_area


# Generate a list of outer edges, split by vertical and horizontal.
# Determine whether each edge can be extended in either direction while staying inside the polygon, using the list of perpendicular (non-extended) edges.
# Extend them until they meet another edge.
# For each rectangle, check that all four of its edges are fully contained within the extended polygon edges.
# Store the area of the largest such rectangle.
def solve_part2(filename):
    red_tiles = extract_data(filename)
    rows = {}
    columns = {}
    for i in range(len(red_tiles)):
        if red_tiles[i][0] == red_tiles[i - 1][0]:
            columns[red_tiles[i][0]] = sorted((red_tiles[i][1], red_tiles[i - 1][1]))
        elif red_tiles[i][1] == red_tiles[i - 1][1]:
            rows[red_tiles[i][1]] = sorted((red_tiles[i][0], red_tiles[i - 1][0]))
        else:
            print(f"Not lined up: {red_tiles[i]}, {red_tiles[i - 1]}")

    rows, columns = extend(rows, columns), extend(columns, rows)

    max_area = 0
    for i in range(len(red_tiles) - 1):
        for j in range(i + 1, len(red_tiles)):
            x_low, x_high = sorted((red_tiles[i][0], red_tiles[j][0]))
            y_low, y_hi = sorted((red_tiles[i][1], red_tiles[j][1]))
            area = (x_high - x_low + 1) * (y_hi - y_low + 1)

            if area > max_area:
                contained = True
                contained = contained and rows[y_low][0] <= x_low
                contained = contained and x_high <= rows[y_low][1]
                contained = contained and rows[y_hi][0] <= x_low
                contained = contained and x_high <= rows[y_hi][1]
                contained = contained and columns[x_low][0] <= y_low
                contained = contained and y_hi <= columns[x_low][1]
                contained = contained and columns[x_high][0] <= y_low
                contained = contained and y_hi <= columns[x_high][1]
                if contained:
                    max_area = area
    return max_area


def main():
    filename = "./data/input.txt"

    # Part 1
    print()
    print("Part 1 :", solve_part1(filename))  #  4771508457 on my input data

    # Part 2
    print()
    print(
        "Part 2 :",
        solve_part2(filename),
    )  #  1539809693 on my input data


if __name__ == "__main__":
    main()
