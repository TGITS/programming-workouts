if __name__ == "__main__":
    file = open("input.txt", "r")
    lines = file.readlines()
    total = 0
    left_array = [ [ 0 for y in range( 1000 ) ] for x in range( 5 ) ]
    right_array = [ [ 0 for y in range( 1000 ) ] for x in range( 5 ) ]
    for i, line in enumerate(lines):
        pair = line.strip().split("   ")
        print(pair)
        left_sequence = list(pair[0])
        right_sequence = list(pair[1])
        for j in range(len(left_sequence)):
            left_array[j][i] = left_sequence[j]
            right_array[j][i] = right_sequence[j]

    column_number = 0
    while column_number < 5:
        left_column = [int(v) for v in left_array[column_number]]
        left_column.sort()
        right_column = [int(v) for v in right_array[column_number]]
        right_column.sort()
        for i in range(1000):
            total += abs(left_column[i] - right_column[i])
        column_number += 1

    print(total)
