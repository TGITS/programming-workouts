#http://rosettacode.org/wiki/N-queens_problem#Python
# From: http://wiki.python.org/moin/SimplePrograms - author, Steve Howell

BOARD_SIZE = int(input())

def under_attack(col, queens):
    return col in queens or any(abs(col - x) == len(queens)-i for i,x in enumerate(queens))

def solve(n):
    solutions = [[]]
    for row in range(n):
        solutions = [solution+[i+1]
                       for solution in solutions
                       for i in range(BOARD_SIZE)
                       if not under_attack(i+1, solution)]
    return solutions

number_of_solutions = 0
for answer in solve(BOARD_SIZE):
    solutions_list = list(enumerate(answer, start=1))
    print(solutions_list)
    number_of_solutions += 1

print("number of solutions : {}".format(number_of_solutions))
