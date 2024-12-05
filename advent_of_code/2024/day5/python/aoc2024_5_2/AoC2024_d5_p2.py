def extract_data(input_name: str) -> list[str]:
    lines = None
    with open(input_name, "r") as input:
        lines = [line.strip() for line in input.readlines()]

    return lines


def extract_rules_and_pages(
    data: list[str],
) -> tuple[dict[int, int], dict[int, int], list[list[int]]]:
    current_index = 0
    current_line = data[current_index]
    pages_valid_after_by_page = {}
    pages_valid_before_by_page = {}
    pages_to_produce = []

    while current_line != "":
        rule = current_line.split("|")
        page_before = int(rule[0])
        page_after = int(rule[1])

        pages_valid_after = pages_valid_after_by_page.get(page_before, [])
        pages_valid_after.append(page_after)
        pages_valid_after_by_page[page_before] = pages_valid_after

        pages_valid_before = pages_valid_before_by_page.get(page_after, [])
        pages_valid_before.append(page_before)
        pages_valid_before_by_page[page_after] = pages_valid_before

        current_index += 1
        current_line = data[current_index]

    current_index += 1

    for i in range(current_index, len(data)):
        pages_to_produce.append([int(page) for page in data[i].split(",")])

    return (pages_valid_after_by_page, pages_valid_before_by_page, pages_to_produce)


def is_pages_to_produce_invalid(
    pages_to_check: list[int], pages_valid_before: dict[int, int]
) -> bool:
    is_invalid = True
    for i, num in enumerate(pages_to_check):
        is_invalid = is_invalid and not (
            set(pages_valid_before.get(num, [])) & set(pages_to_check[i + 1 :])
        )
    return is_invalid


def find_invalid_pages(
    pages_to_produce: list[list[int]], page_ordering_rules: dict[int, int]
) -> list[list[int]]:
    return [
        pages_list
        for pages_list in pages_to_produce
        if not is_pages_to_produce_invalid(pages_list, page_ordering_rules)
    ]


def correct_invalid_pages(
    pages_to_produce: list[list[int]],
    pages_valid_before_by_page: dict[int, int]
) -> list[list[int]]:
    corrected_list_of_pages_to_produce = []
    number_to_process = len(pages_to_produce)
    i = 0
    while i < number_to_process:
        corrected_pages = pages_to_produce[i][:] #une copie de travail
        pages_to_process = pages_to_produce[i][:] # une copie pour avoir la liste des pages à traiter
        while pages_to_process: # Tant qu'il y a des éléments dans la liste, i.e. que la liste est non vide
            page_to_process = pages_to_process.pop(0) # Je récupère la valeur du premier élement et je le retire de la liste des pages à traiter
            index_page_to_process = corrected_pages.index(page_to_process)
            set_of_pages_to_move = set(pages_valid_before_by_page.get(page_to_process, [])) & set(corrected_pages[index_page_to_process+1 :])
            #print(set_of_pages_to_move)
            if len(set_of_pages_to_move) > 0 : # Il y a des déplacements de pages à faire
                corrected_pages.remove(page_to_process)
                max_index_to_move = max([corrected_pages.index(page) for page in set_of_pages_to_move])
                corrected_pages.insert(max_index_to_move+1, page_to_process)
        corrected_list_of_pages_to_produce.append(corrected_pages)
        i += 1

    return corrected_list_of_pages_to_produce


def find_middle_number(list_of_pages: list[list[int]]) -> list[int]:
    middle_numbers = []
    for list_of_numbers in list_of_pages:
        length = len(list_of_numbers)
        middle = length // 2
        middle_numbers.append(list_of_numbers[middle])

    return middle_numbers


if __name__ == "__main__":
    data = extract_data("input.txt")
    (pages_valid_after_by_page, pages_valid_before_by_page, pages_to_produce) = (
        extract_rules_and_pages(data)
    )
    invalid_pages = find_invalid_pages(pages_to_produce, pages_valid_before_by_page)
    print(invalid_pages)
    corrected_list_of_pages = correct_invalid_pages(invalid_pages, pages_valid_before_by_page)
    print(corrected_list_of_pages)
    middle_numbers = find_middle_number(corrected_list_of_pages)
    print(middle_numbers)
    print(sum(middle_numbers))
