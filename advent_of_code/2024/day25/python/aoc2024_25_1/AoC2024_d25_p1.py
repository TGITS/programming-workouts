def get_data(input_name: str) -> tuple[list[int], list[int]]:
    locks = []
    keys = []
    with open(input_name, "r") as input:
        lines = input.readlines()
        max_size = len(lines)
        i = 0
        while i < max_size:
            current_line = lines[i].strip()
            if len(current_line) == 0:
                i+= 1
                continue
            
            if current_line == '#####':
                i+= 1 # We do not need the first line of a lock
                columns = [0] * 5
                max = i + 5
                while i < max:
                    current_line = lines[i].strip()
                    for j in range(5):
                        if current_line[j] == '#':
                            columns[j] += 1
                    i += 1
                locks.append(columns)
                i += 1 # We do not need the last line of a lock
                continue 
                
            if current_line == '.....':
                i+= 1 # We do not need the first line of a key
                columns = [0] * 5
                max = i + 5
                while i < max:
                    current_line = lines[i].strip()
                    for j in range(5):
                        if current_line[j] == '#':
                            columns[j] += 1
                    i += 1
                keys.append(columns)
                i += 1 # We do not need the last line of a key
                continue # Noe strictly needed
                
    return locks, keys

def count_matching_lock_key_pairs(locks:list[int], keys:list[int])-> int:
    counter = 0
    for key in keys:
        for lock in locks:
            if all([(k+l) <= 5 for k,l in zip(key,lock)]):
                counter += 1
    
    return counter

if __name__ == "__main__":
    # locks, keys = get_data("input_test.txt")
    locks, keys = get_data("input.txt")
    print("locks:", locks)
    print()
    print("keys:", keys)
    print()
    print(count_matching_lock_key_pairs(locks, keys))