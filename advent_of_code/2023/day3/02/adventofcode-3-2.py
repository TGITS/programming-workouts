import functools
import operator
from dataclasses import dataclass

# First parse the line to get the list of numbers (as string) and their coordinate (line number et position of the first digit in the line)
# AND the list of coordinates of the symbols.
# A symbol is a character different from a point and a digit
#
# Next compute for each number the list of coordinates of the neighbouring cells
# 
# Then find the valid number to add, the ones for which the list of coordinates of the neighbouring cells have a intersection not null with the list of coordinates of symbols

@dataclass
class NumberInfo:
    """"Class for collecting informations (value and coordinates) on a parsed number"""
    number: str
    line_number: int
    position_in_line: int

    def value(self) -> int:
        return int(self.number)
    
    def update_number(self, character:str):
        self.number += character

    def to_set_of_coordinates(self) -> set[tuple[int]]:
        return {(x, self.line_number) for x in range(self.position_in_line, self.position_in_line+len(self.number))}

@dataclass
class SymbolInfo:
    """"Class for collecting informations (value and coordinates) on a parsed symbol"""
    symbol: str
    line_number: int
    position_in_line: int
    set_of_neighbors: set

    def compute_neighbourhood(self):
        self.set_of_neighbors = {(self.position_in_line-1, self.line_number-1), (self.position_in_line, self.line_number-1),(self.position_in_line+1, self.line_number-1),
                                 (self.position_in_line-1, self.line_number), (self.position_in_line+1, self.line_number),
                                 (self.position_in_line-1, self.line_number+1), (self.position_in_line, self.line_number+1),(self.position_in_line+1, self.line_number+1)}

    def find_eligible_numbers(self, list_of_numbers):
        #print(list_of_numbers)
        return [number.value() for number in list_of_numbers if len(self.set_of_neighbors.intersection(number.to_set_of_coordinates())) > 0]

    def compute_gear_ratio(self, list_of_numbers):
        numbers = self.find_eligible_numbers(list_of_numbers)
        #print(numbers)
        if len(numbers) == 2:
            return functools.reduce(operator.mul, numbers)
        return 0


filename = 'input.txt'
#filename = 'smaller_input1.txt'
#filename = 'smaller_input2.txt'

if __name__ == '__main__':
    list_of_numbers = []
    list_of_symbols = []
    with open (filename, 'r') as input:
        for line_number, line in enumerate(input):
            number_info = None
            line_size = len(line)
            for position_in_line, character in enumerate(line):
                if(character.isdigit()):
                    if number_info is None:
                        number_info = NumberInfo(character, line_number, position_in_line)
                    else:
                        number_info.update_number(character)
                        if position_in_line == len(line)-1:
                            list_of_numbers.append(number_info)
                            number_info = None
                else:
                    if number_info is not None:
                        # We have begun to collect the digit of a number, we must store it and reinitialize the variable number
                        list_of_numbers.append(number_info)
                        number_info = None
                    if character == '*':
                        symbol = SymbolInfo(character, line_number, position_in_line, set())
                        symbol.compute_neighbourhood()
                        list_of_symbols.append(symbol)
    
    #print("The list of numbers: ")
    #for number in list_of_numbers:
    #    print(number, ':', number.to_set_of_coordinates())
    #print()  
    list_of_ratios_gear = [symbol.compute_gear_ratio(list_of_numbers) for symbol in list_of_symbols]
    #print("gear ratios: ")
    #for ratio in list_of_ratios_gear:
    #    print(ratio)
    print(sum(list_of_ratios_gear))      