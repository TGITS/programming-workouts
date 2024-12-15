from collections import namedtuple, deque
import sys
sys.setrecursionlimit(1000000)

UP = '^'
DOWN = 'v'
LEFT = '<'
RIGHT = '>'
WALL = '#'
DOUBLE_WALL = '##'
DOUBLE_BOX = '[]'
BOX_LEFT_PART = '['
BOX_RIGHT_PART = ']'
BOX = 'O'
ROBOT = '@'
EMPTY_SPACE = '.'
DOUBLE_EMPTY_SPACE = '..'

Position = namedtuple("Position", ["x", "y"])

def extract_data(input_name: str) -> tuple[list[str],str]:
    map = []
    movements = ''
    with open(input_name, "r") as input:
        data = input.readlines()
        i = 0
        line = data[i].strip()
        while len(line) != 0:
            map.append(line)
            i += 1
            line = data[i].strip()
    
        i += 1
        
        while i < len(data):
            line = data[i].strip()
            movements += line
            i += 1
        
    return map, movements

def transform_map(map: list[str]) -> list[list[str]]:
    new_map = []
    for line in map:
        new_line = []
        for c in line:
            if c == WALL:
                new_line.append(WALL)
                new_line.append(WALL)
            if c == BOX:
                new_line.append(BOX_LEFT_PART)
                new_line.append(BOX_RIGHT_PART)
            if c == EMPTY_SPACE:
                new_line.append(EMPTY_SPACE)
                new_line.append(EMPTY_SPACE)
            if c == ROBOT:
                new_line.append(ROBOT)
                new_line.append(EMPTY_SPACE)
        new_map.append(new_line)
    return new_map

def display_map(map: list[str]):
    for line in map:
        for c in line:
            print(c, end='')
        print()

def robot_initial_position(map: list[str]) -> Position:
    for y,line in enumerate(map):
        for x,c in enumerate(line):
            if c == ROBOT:
                return Position(y=y, x=x)

def next_position(current_position:Position, movement:str) -> Position:
    x = None
    y = None
    if movement == UP:
        return Position(current_position.x, current_position.y - 1)
    if movement == DOWN:
        return Position(current_position.x, current_position.y + 1)
    if movement == LEFT:
        return Position(current_position.x - 1, current_position.y)
    if movement == RIGHT:
        return Position(current_position.x + 1, current_position.y)

def is_next_position_wall(current_position:Position, movement:str, map:list[list[str]]) -> bool:
    position = next_position(current_position,movement)
    return map[position.y][position.x] == WALL

def is_next_position_empty_space(current_position:Position, movement:str, map:list[list[str]]) -> bool:
    position = next_position(current_position,movement)
    return map[position.y][position.x] == EMPTY_SPACE

def is_next_position_box(current_position:Position, movement:str, map:list[list[str]]) -> bool:
    position = next_position(current_position,movement)
    return map[position.y][position.x] == BOX_LEFT_PART or map[position.y][position.x] == BOX_RIGHT_PART

def is_next_position_left_box_part(current_position:Position, movement:str, map:list[list[str]]) -> bool:
    position = next_position(current_position,movement)
    return map[position.y][position.x] == BOX_LEFT_PART

def is_next_position_right_box_part(current_position:Position, movement:str, map:list[list[str]]) -> bool:
    position = next_position(current_position,movement)
    return map[position.y][position.x] == BOX_RIGHT_PART

def positions_of_left_box_part(map: list[list[str]]) -> list[Position]:
    left_box_parts = []
    for y,line in enumerate(map):
        for x, c in enumerate(line):
            if c == BOX_LEFT_PART:
                left_part = Position(y=y, x=x)
                left_box_parts.append(left_part)
    return left_box_parts

def is_movement_possible(current_position:Position, movement:str, map:list[list[str]], already_visited:set[Position]) -> bool:
    # A box is movable, if the next spot is a movable box or an empty space
    already_visited.add(current_position)
    if movement == LEFT or movement == RIGHT:
        if is_next_position_empty_space(current_position, movement, map):
            return True
        if is_next_position_wall(current_position, movement, map):
            return False
        return is_movement_possible(next_position(current_position, movement), movement, map, already_visited)
    if movement == UP or movement == DOWN:
        if is_next_position_wall(current_position, movement, map):
            return False
        
        other_position_to_check = None
        
        if map[current_position.y][current_position.x] == BOX_LEFT_PART:
            other_position_to_check = Position(x=current_position.x+1, y=current_position.y)
        elif map[current_position.y][current_position.x] == BOX_RIGHT_PART:
            other_position_to_check = Position(x=current_position.x-1, y=current_position.y)
        

        if is_next_position_empty_space(current_position, movement, map):
            if other_position_to_check in already_visited:
                return True
            else:
                return is_movement_possible(other_position_to_check, movement, map, already_visited)
        
        
        return is_movement_possible(next_position(current_position), movement, map) and is_movement_possible(other_position_to_check, movement, map,already_visited)
        

def get_positions_to_update(current_position:Position, movement:str, map:list[list[str]]) -> deque[tuple[str,Position]]:
    positions_to_update = deque()
    positions_to_update.append((map[current_position.y][current_position.x], current_position))
    
    if is_next_position_empty_space(current_position, movement, map):
        return  positions_to_update
    
    if movement == LEFT or movement == RIGHT:
        positions_to_update.extend(get_positions_to_update(next_position(current_position, movement), movement, map))
        return positions_to_update
    
    if movement == UP or movement == DOWN:
        positions_to_check = deque()
        positions_to_check.append(current_position)
        if map[current_position.y][current_position.x] == BOX_LEFT_PART:
            box_other_part = Position(x=current_position+1, y=current_position.y)
            positions_to_check.append(box_other_part)
            positions_to_update.append((BOX_LEFT_PART, box_other_part))
        elif map[current_position.y][current_position.x] == BOX_RIGHT_PART:
            box_other_part = Position(x=current_position-1, y=current_position.y)
            positions_to_check.append(box_other_part)
            positions_to_update.append((BOX_RIGHT_PART, box_other_part))
        
        for position in positions_to_check:
            positions_to_update.extend(get_positions_to_update(next_position(position), movement, map))
        return positions_to_update

def move_robot(robot_initial_position:Position, movements:str, map:list[list]):
    robot_current_position = robot_initial_position
    already_visited = set()
    for movement in movements:
        if is_next_position_wall(robot_current_position, movement, map): # in the next direction of movement this is a wall, we cannot move
            continue # Nothing todo
        if is_next_position_empty_space(robot_current_position, movement, map):
            current_position = next_position(robot_current_position, movement)
            map[current_position.y][current_position.x] = ROBOT
            map[robot_current_position.y][robot_current_position.x] = EMPTY_SPACE
            robot_current_position = Position(x=current_position.x,y=current_position.y)
        if is_next_position_box(robot_current_position, movement, map): 
            current_position = next_position(robot_current_position, movement)
            if is_movement_possible(current_position, movement, map, already_visited):
                positions_to_update = get_positions_to_update(current_position, movement, map)
                # update map
                
                dx = 0
                dy = 0
                map[robot_current_position.y][robot_current_position.x] = EMPTY_SPACE
                if movement == UP:
                    dx = 0
                    dy = -1
                elif movement == DOWN:
                    dx = 0
                    dy = 1
                elif movement == LEFT:
                    dx = -1
                    dy = 0
                elif movement == RIGHT:
                    dx = 1
                    dy = 0
                
                positions_to_update.appendleft((ROBOT, robot_current_position))
                for position in positions_to_update:
                    map[position[1].y+dy][position[1].x+dx] = position[0]
                
            else:
                continue
            
            

def compute_gps_coordinate(box:Position) -> int:
    return 100 * box.y + box.x


if __name__ == "__main__":
    map, movements = extract_data("input_test.txt")
    # map, movements = extract_data("input_test0.txt")
    # map, movements = extract_data("input.txt")
    
    print("map:")
    display_map(map)
    print()
    new_map = transform_map(map)
    print("new map:")
    display_map(new_map)

    # print()
    # print("movements:", movements)
   
    robot = robot_initial_position(new_map)
    print()
    print("robot:", robot)
    
    move_robot(robot, movements, new_map)

    print("Map after movements:")
    display_map(new_map)
    print()

    left_box_part_positions = positions_of_left_box_part(new_map)

    print(sum([compute_gps_coordinate(left_box) for left_box in left_box_part_positions]))


# input_test.txt   => 9021