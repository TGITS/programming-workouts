def get_map(input_name: str) -> list[str]:
    map = []
    with open(input_name, "r") as input:
        for line in input:
            map.append(line.strip())
    return map

if __name__ == "__main__":
    map = get_map("input.txt")
    print("map:", map)
