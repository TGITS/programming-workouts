# 1 - extraire les données
# 2 - extraire séparément "page ordering rules" et "pages to produce"
# 3 - déterminer la liste de "pages to produce" valides
# 4 - calculer la somme des valeurs des "middle page number" de la liste de "pages to produce" valides


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


def is_pages_to_produce_valid(
    pages_to_check: list[int], pages_valid_before: dict[int, int]
) -> bool:
    is_valid = True
    for i, num in enumerate(pages_to_check):
        print(
            i,
            num,
            pages_valid_before.get(num, []),
            pages_to_check[i + 1 :],
            set(pages_valid_before.get(num, [])) & set(pages_to_check[i + 1 :]),
        )
        is_valid = is_valid and not (
            set(pages_valid_before.get(num, [])) & set(pages_to_check[i + 1 :])
        )
    print(is_valid)
    return is_valid


def find_valid_pages(
    pages_to_produce: list[list[int]], page_ordering_rules: dict[int, int]
) -> list[list[int]]:
    return [
        pages_list
        for pages_list in pages_to_produce
        if is_pages_to_produce_valid(pages_list, page_ordering_rules)
    ]


def find_middle_number(list_of_pages: list[list[int]]) -> list[int]:
    middle_numbers = []
    for list_of_numbers in list_of_pages:
        length = len(list_of_numbers)
        middle = length // 2
        middle_numbers.append(list_of_numbers[middle])

    return middle_numbers


if __name__ == "__main__":
    data = extract_data("input.txt")
    print(data)
    (pages_valid_after_by_page, pages_valid_before_by_page, pages_to_produce) = (
        extract_rules_and_pages(data)
    )
    print(pages_valid_after_by_page)
    print(pages_valid_before_by_page)
    print(pages_to_produce)
    valid_pages = find_valid_pages(pages_to_produce, pages_valid_before_by_page)
    print(valid_pages)
    middle_numbers = find_middle_number(valid_pages)
    print(middle_numbers)
    print(sum(middle_numbers))
