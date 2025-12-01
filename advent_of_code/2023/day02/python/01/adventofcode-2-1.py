import re

# Constants
MAX_RED_CUBES = 12
MAX_GREEN_CUBES = 13
MAX_BLUE_CUBES = 14



if __name__ == "__main__":
    general_pattern = r'Game\s(\d+):(.+)'
    red_pattern = r'\s+(\d+)\s+red'
    blue_pattern = r'\s+(\d+)\s+blue'
    green_pattern = r'\s+(\d+)\s+green'
    possible_games = []
    with open ('input.txt', 'r') as input:
        for line in input:
            matches = re.fullmatch(general_pattern, line.strip())
            game_number = matches.group(1)
            results = matches.group(2)
            #print(game_number)
            #print(results)
            possible_game = True
            red_matches = re.findall(red_pattern, results)
            if red_matches is not None and len(red_matches) > 0:
                i = 0
                while i < len(red_matches):
                    #print(red_matches[i])
                    if int(red_matches[i]) > MAX_RED_CUBES:
                        possible_game = False
                        break
                    i += 1

            blue_matches = re.findall(blue_pattern, results)
            if blue_matches is not None and len(blue_matches) > 0:
                i = 0
                while i < len(blue_matches):
                    #print(blue_matches[i])
                    if int(blue_matches[i]) > MAX_BLUE_CUBES:
                        possible_game = False
                        break
                    i += 1
                    
            green_matches = re.findall(green_pattern, results)
            if green_matches is not None and len(green_matches) > 0:
                i = 0
                while i < len(green_matches):
                    #print(green_matches[i])
                    if int(green_matches[i]) > MAX_GREEN_CUBES:
                        possible_game = False
                        break
                    i += 1
                    
            if possible_game:
                possible_games.append(int(game_number))

           
    print(sum(possible_games))    
        