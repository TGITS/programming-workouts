import itertools


def extract_reports(input_name: str, separator: str) -> list[list[int]]:
    reports = []
    with open(input_name, "r") as input:
        for line in input:
            report = [int(elt) for elt in line.strip().split(separator)]
            print(report)
            reports.append(report)

    return reports


def are_all_decreasing(seq: list[int]) -> bool:
    testing_seq = [i < 0 and i in [-1, -2, -3] for i in seq]
    return all(testing_seq)


def are_all_increasing(seq: list[int]) -> bool:
    testing_seq = [i > 0 and i in [1, 2, 3] for i in seq]
    return all(testing_seq)


def are_all_decreasing_or_increasing(seq: list[int]) -> bool:
    return are_all_decreasing(seq) or are_all_increasing(seq)


def are_all_differences_in_check(seq: list[int]) -> bool:
    return all([abs(i) in [1, 2, 3] for i in seq])


def compute_diff_seq(report: list[int]) -> list[int]:
    return [pair[0] - pair[1] for pair in zip(report[:-1], report[1:])]


def is_report_safe(report: list[int]) -> bool:
    transformed_report = compute_diff_seq(report)

    if are_all_decreasing_or_increasing(
        transformed_report
    ) and are_all_differences_in_check(transformed_report):
        return True

    report_combinations = itertools.combinations(report, len(report) - 1)
    return any(
        [
            are_all_decreasing_or_increasing(compute_diff_seq(combination))
            and are_all_differences_in_check(compute_diff_seq(combination))
            for combination in report_combinations
        ]
    )


def count_safe_reports(reports: list[list[int]]) -> int:
    return len([r for r in reports if is_report_safe(r)])


if __name__ == "__main__":
    reports = extract_reports("input.txt", " ")
    print(count_safe_reports(reports)) #Expected 589
