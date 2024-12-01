if __name__ == "__main__":
    file = open("input.txt", "r")
    lines = file.readlines()
    total = 0
    count = 0
    for line in lines:
        pair = line.strip().split("   ")
        count += 1
        print(pair)
        left_sequence = list(pair[0])
        left_sequence.sort()
        right_sequence = list(pair[1])
        right_sequence.sort()
        for i in range(len(left_sequence)):
            total += abs(int(left_sequence[i]) - int(right_sequence[i]))

    print(count, total)
