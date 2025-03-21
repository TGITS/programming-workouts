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
    set_of_neighbors: set

    def value(self) -> int:
        return int(self.number)
    
    def update_number(self, character:str):
        self.number += character

    def compute_neighbours(self):
        self.set_of_neighbors = {(x, self.line_number-1) for x in range(self.position_in_line-1, self.position_in_line+len(self.number)+1)}
        self.set_of_neighbors = self.set_of_neighbors.union({(self.position_in_line-1, self.line_number), (self.position_in_line+len(self.number), self.line_number)})
        self.set_of_neighbors = self.set_of_neighbors.union({(x, self.line_number+1) for x in range(self.position_in_line-1, self.position_in_line+len(self.number)+1)})

    def is_eligible(self, set_of_symbols_coordinates:set[tuple[int,int]]) -> bool:
        print("Neighbors for", self.number, "-", self.position_in_line, self.line_number, len(self.number),"-",":", self.set_of_neighbors,)
        return len(self.set_of_neighbors.intersection(set_of_symbols_coordinates)) > 0

@dataclass
class SymbolInfo:
    """"Class for collecting informations (value and coordinates) on a parsed symbol"""
    symbol: str
    line_number: int
    position_in_line: int

    def to_coordinates(self)-> tuple[int]:
        return (self.position_in_line, self.line_number)

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
                        number_info = NumberInfo(character, line_number, position_in_line, set())
                    else:
                        number_info.update_number(character)
                        if position_in_line == len(line)-1:
                            number_info.compute_neighbours()
                            list_of_numbers.append(number_info)
                            #print(number_info)
                            number_info = None
                else:
                    if number_info is not None:
                        # We have begun to collect the digit of a number, we must store it and reinitialize the variable number
                        number_info.compute_neighbours()
                        list_of_numbers.append(number_info)
                        #print(number_info)
                        number_info = None
                    if character != '.' and character != '\n':
                        list_of_symbols.append(SymbolInfo(character, line_number, position_in_line))
        
    #print("The list of numbers: ")
    #for number in list_of_numbers:
    #    print(number)
    #print()
    #for symbol in list_of_symbols:
    #    print(symbol)
    set_of_symbols_coordinates = {symbol.to_coordinates() for symbol in list_of_symbols}
    for coordinates in set_of_symbols_coordinates:
        print(coordinates)
    eligible_numbers = [number_info.value() for number_info in list_of_numbers if number_info.is_eligible(set_of_symbols_coordinates)]
    #for eligible_number in eligible_numbers:
    #    print(eligible_number)
    #print(set_of_symbols_coordinates)
    print(sum(eligible_numbers))      