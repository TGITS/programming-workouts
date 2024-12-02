import pytest
from AoC2024_d2_p2 import *


@pytest.mark.parametrize(
    "test_input,expected", [("input.txt", 1000), ("input_test.txt", 6)]
)
def test_extract_sequences(test_input, expected):
    reports = extract_reports(test_input, " ")
    assert len(reports) == expected


@pytest.mark.parametrize(
    "report,expected",
    [
        ([7, 6, 4, 2, 1], True),
        ([1, 2, 7, 8, 9], False),
        ([9, 7, 6, 2, 1], False),
        ([1, 3, 2, 4, 5], True),
        ([8, 6, 4, 4, 1], True),
        ([1, 3, 6, 7, 9], True),
    ],
)
def test_is_report_safe(report, expected):
    assert is_report_safe(report) == expected


@pytest.mark.parametrize(
    "reports,expected",
    [
        (
            [
                [7, 6, 4, 2, 1],
                [1, 2, 7, 8, 9],
                [9, 7, 6, 2, 1],
                [1, 3, 2, 4, 5],
                [8, 6, 4, 4, 1],
                [1, 3, 6, 7, 9],
            ],
            4,
        )
    ],
)
def test_count_safe_reports(reports, expected):
    assert count_safe_reports(reports) == expected
