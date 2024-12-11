def extract_data(input_name: str) -> list[str]:
    data = []
    with open(input_name, "r") as input:
        for line in input:
            data.append(line.strip())
    return data

if __name__ == "__main__":
    data = extract_data("input_test.txt")
    # data = extract_data("input.txt")