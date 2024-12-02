def extract_reports(input_name: str, separator: str) -> list[list[int]]:
    reports = []
    with open(input_name, "r") as input:
        for line in input:
            report = [int(elt) for elt in line.strip().split(separator)]
            print(report)
            reports.append(report)

    return reports


def are_all_decreasing_or_increasing(seq: list[int]) -> bool:
    return all([i < 0 for i in seq]) or all([i > 0 for i in seq])


def are_all_differences_in_check(seq: list[int]) -> bool:
    return all([abs(i) in [1, 2, 3] for i in seq])


def is_report_safe(report: list[int]) -> bool:
    transformed_report = [pair[0] - pair[1] for pair in zip(report[:-1], report[1:])]
    print(transformed_report)
    return are_all_decreasing_or_increasing(
        transformed_report
    ) and are_all_differences_in_check(transformed_report)


def count_safe_reports(reports: list[list[int]]) -> int:
    return len([r for r in reports if is_report_safe(r)])


if __name__ == "__main__":
    reports = extract_reports("input.txt", " ")
    print(count_safe_reports(reports)) #Expected 549
