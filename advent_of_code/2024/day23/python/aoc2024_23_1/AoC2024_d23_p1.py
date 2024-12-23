from itertools import combinations


def get_data(input_name: str) -> list[str]:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(line.strip())
    return data


def extract_connections(data: list[str]) -> dict[str, set[str]]:
    connections_by_computer = {}
    for line in data:
        computers = line.split("-")
        computer1 = computers[0]
        computer2 = computers[1]
        computers_associated_with_computer1 = connections_by_computer.get(
            computer1, set()
        )
        computers_associated_with_computer1.add(computer2)
        connections_by_computer[computer1] = computers_associated_with_computer1

        computers_associated_with_computer2 = connections_by_computer.get(
            computer2, set()
        )
        computers_associated_with_computer2.add(computer1)
        connections_by_computer[computer2] = computers_associated_with_computer2
    return connections_by_computer


def find_triples(connections_by_computer: dict[str, set[str]]) -> set[tuple[str]]:
    set_of_triples = set()
    for k, v in connections_by_computer.items():
        for c1, c2 in combinations(v, 2):
            if c1 in connections_by_computer[c2]:
                set_of_triples.add(tuple(sorted([k, c1, c2])))
    return set_of_triples


def find_eligible_triples(triples: set[tuple[str]]) -> set[tuple[str]]:
    return {triple for triple in triples if any(c.startswith("t") for c in triple)}


if __name__ == "__main__":
    data = get_data("input_test.txt")
    # data = get_data("input.txt")
    print("data:", data)
    print()
    connections_by_computer = extract_connections(data)
    print(connections_by_computer)
    print()
    triples = find_triples(connections_by_computer)
    print(triples)
    print(len(triples))
    print()
    eligible_triples = find_eligible_triples(triples)
    print(eligible_triples)
    print(len(eligible_triples))
