from main import (
    extract_data,
    compute_euclidian_distances,
    connect_junction_boxes,
    solve_part1,
    solve_part2,
)


def test_solve_part1():
    filename = "./data/input_test.txt"
    connection_limit = 10
    junctions, circuits = extract_data(filename)
    distances = compute_euclidian_distances(junctions)
    circuits = connect_junction_boxes(distances, circuits, connection_limit)
    assert solve_part1(circuits) == 40


def test_solve_part2():
    filename = "./data/input_test.txt"
    connection_limit = 10
    junctions, circuits = extract_data(filename)
    distances = compute_euclidian_distances(junctions)
    circuits = connect_junction_boxes(distances, circuits, connection_limit)
    assert solve_part2(distances, circuits, connection_limit) == 25272
