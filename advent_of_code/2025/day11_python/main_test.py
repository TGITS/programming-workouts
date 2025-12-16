from main import extract_data, solve_part1, solve_part2, solve_part2


def test_extract_data():
    filename = "./data/input_test1.txt"
    # expected_input = [
    #     (".##.", [[3], [1, 3], [2], [2, 3], [0, 2], [0, 1]], (3, 5, 4, 7)),
    #     (
    #         "...#.",
    #         [[0, 2, 3, 4], [2, 3], [0, 4], [0, 1, 2], [1, 2, 3, 4]],
    #         (7, 5, 12, 7, 2),
    #     ),
    #     (
    #         ".###.#",
    #         [[0, 1, 2, 3, 4], [0, 3, 4], [0, 1, 2, 4, 5], [1, 2]],
    #         (10, 11, 11, 5, 10, 5),
    #     ),
    # ]
    graph = extract_data(filename)
    print("input:", graph)
    # assert input == expected_input


def test_solve_part1():
    filename = "./data/input_test1.txt"
    assert solve_part1(filename) == 5


def test_solve_part2():
    filename = "./data/input_test2.txt"
    assert solve_part2(filename) == 2


def test_solve_part2_bis():
    filename = "./data/input_test2.txt"
    assert solve_part2(filename) == 2
