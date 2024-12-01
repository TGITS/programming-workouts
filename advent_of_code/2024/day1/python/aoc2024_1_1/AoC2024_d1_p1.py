def extract_sequences(input_name: str, separator: str) -> tuple[list[int], list[int]]:
    left_sequence = []
    right_sequence = []
    with open(input_name, "r") as input:
        for line in input:
            pair = line.strip().split(separator)
            print(pair)
            left_sequence.append(int(pair[0]))
            right_sequence.append(int(pair[1]))

    return (left_sequence, right_sequence)


def compute_total_distance(pair_of_sequences: tuple[list[int], list[int]]) -> int:
    total = 0
    pair_of_sequences[0].sort()
    pair_of_sequences[1].sort()
    for i in range(len(pair_of_sequences[0])):
        total += abs(pair_of_sequences[0][i] - pair_of_sequences[1][i])

    return total


if __name__ == "__main__":
    print(compute_total_distance(extract_sequences("input.txt", "   ")))
