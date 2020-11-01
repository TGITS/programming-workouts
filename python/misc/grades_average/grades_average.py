import csv


def extract_grade(filename):
    lines = []
    with open(filename) as csvfile:
        lines = csv.reader(csvfile, delimiter=',')
        return [row for row in lines]


def compute_average(grades, coefficients):
    return sum(int(grade) * int(coefficient) for grade, coefficient in zip(grades, coefficients))/sum(int(coefficient) for coefficient in coefficients)


if __name__ == "__main__":
    subjects, grades, coefficients = extract_grade("grades.csv")
    print("subjects :", subjects)
    print("grades :", grades)
    print("coefficients :", coefficients)
    print("list of tuples (subject, grade, coefficient) :",
          list(zip(subjects, grades, coefficients)))
    print("{:2.2f}".format(compute_average(grades, coefficients)))
