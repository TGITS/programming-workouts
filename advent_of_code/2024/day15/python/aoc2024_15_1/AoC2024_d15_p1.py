from collections import namedtuple, deque
from dataclasses import dataclass

UP = '^'
DOWN = 'v'
LEFT = '<'
RIGHT = '>'
WALL = '#'
BOX = 'O'
ROBOT = '@'

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

def positions_of_wall(map: list[str]) -> set[Position]:
    walls = set()
    for y,line in enumerate(map):
        for x,c in enumerate(line):
            if c == WALL:
                walls.add(Position(y=y, x=x))
    return walls

def robot_initial_position(map: list[str]) -> Position:
    for y,line in enumerate(map):
        for x,c in enumerate(line):
            if c == ROBOT:
                return Position(y=y, x=x)
            
def positions_of_boxes(map: list[str]) -> list[Position]:
    boxes = []
    for y,line in enumerate(map):
        for x,c in enumerate(line):
            if c == BOX:
                boxes.append(Position(y=y, x=x))
    return boxes

def compute_next_position(position:Position, movement:str) -> Position:
    if movement == UP:
        return Position(x=position.x, y=position.y - 1)
    if movement == DOWN:
        return Position(x=position.x, y=position.y + 1)
    if movement == LEFT:
        return Position(x=position.x - 1, y=position.y)
    if movement == RIGHT:
        return Position(x=position.x + 1, y=position.y)

def move_robot(robot:Position, boxes:list[Position], walls:set[Position], movements:str) -> list[Position]:
    boxes_positions = boxes
    robot_position = robot
    for movement in movements:
        next_position = compute_next_position(robot_position, movement)
        if next_position in walls: # in the next direction of movement this is a wall, we cannot move
            continue
        if next_position in boxes_positions: 
            # we found a box, we can push the box, if at the end of line of box it is an empty space, this not a wall
            first_box_of_the_line = next_position
            current_position = first_box_of_the_line
            while current_position in boxes_positions:
                current_position = compute_next_position(current_position, movement)
            if current_position in walls:
                continue # we cannot move all the boxes and the robot
            else:
                # we have to move all the boxes and the robot, the current position is in an empty spot
                # the current position is a new box position
                # the first element in the deque, is to be remove from the boxes and the value is the new robot position
                boxes_positions.append(current_position)
                boxes_positions.remove(first_box_of_the_line)
                robot_position = first_box_of_the_line
        else:
            # we are on an empty spot, this is the new position for the robot
            robot_position = next_position
    return boxes_positions

def compute_gps_coordinate(box:Position) -> int:
    return 100 * box.y + box.x


if __name__ == "__main__":
    # map, movements = extract_data("input_test.txt")
    # map, movements = extract_data("input_test0.txt")
    map, movements = extract_data("input.txt")
    print("map:", map)
    print()
    print("movements:", movements)
    print()
    robot = robot_initial_position(map)
    print("robot:", robot)
    print()
    walls = positions_of_wall(map)
    print("walls:", walls)
    print()
    boxes = positions_of_boxes(map)
    print("boxes:", boxes)
    print()
    boxes = move_robot(robot,boxes, walls, movements)
    print("boxes:", boxes)
    print()
    print(sum([compute_gps_coordinate(box) for box in boxes]))


# input_test.txt   => 10092
# input_test0.txt  => 2028