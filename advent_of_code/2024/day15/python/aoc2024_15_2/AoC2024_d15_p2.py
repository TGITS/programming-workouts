from collections import  deque, defaultdict
from typing import TypeAlias
import sys
sys.setrecursionlimit(1000000)

Grid: TypeAlias = defaultdict[complex, str]
OFFSETS: dict[str, complex] = {
    ">": 1 + 0j, "<": -1 + 0j, "v": 1j, "^": -1j,
}
WIDE_TILES = {".": "..", "#": "##", "O": "[]"}
WIDE_BOX = WIDE_TILES["O"]

def extract_data(input_name: str) -> tuple[Grid,str, complex]:
    grid: Grid = defaultdict(lambda: "#")
    robot: complex | None = None
    movements = None
    with open(input_name, "r") as input:
        lines = input.readlines()
        y = 0
        row = lines[y].strip()
        movements = ''
        while row:
            for x, tile in enumerate(row):
                pos = x * 2 + y * 1j
                 # If the robot was found
                if tile == "@":
                    # Keep track of it, and empty its starting position
                    robot, tile = pos, "."
                # Store the "wide tile"
                for w, wide_tile in enumerate(WIDE_TILES.get(tile, WIDE_TILES["."])):
                    grid[pos + w] = wide_tile
            y += 1
            row = lines[y].strip()

        assert robot is not None
        # The rest of the lines are the robot's movements
        while y < len(lines):
            line = lines[y].strip()
            movements += line
            y += 1
        
    return grid, movements, robot

def move_robot(grid: Grid, movements:str, robot:complex)-> None:
    for movement in movements:
        offset = OFFSETS.get(movement, 0j)
        new_robot = robot + offset

        new_robot_tile = grid[new_robot]
        robot_is_pushing = new_robot_tile in WIDE_BOX
        # If robot would run into a wall
        if new_robot_tile == "#":
            # Don't move the robot
            continue
        # If robot would run into a box from the left or right
        elif robot_is_pushing and movement in "<>":
            # Check space in front of all the boxes being pushed
            box = new_robot
            while (tile := grid[box]) in WIDE_BOX:
                box += offset
            # If boxes would be pushed into a wall
            if tile == "#":
                # Don't move the boxes or the robot
                continue

            # Push the boxes
            while box != new_robot:
                grid[box] = grid[box - offset]
                box -= offset
            grid[new_robot] = tile
        # If robot would run into a box from the top or bottom
        # Boxes are twice as wide, so the group of boxes
        # that gets pushed may have a complex shape.
        elif robot_is_pushing and movement in "^v":
            # HACK I'm storing the positions of each box to move as the
            # keys of a dict (where the value doesn't matter). This way,
            # I can preserve the order, and the box positions will be
            # unique.
            boxes_to_push: dict[complex, None] = {}
            # Get the leftmost position of this box
            box = new_robot
            while grid[box] != WIDE_BOX[0]:
                box -= 1
            # We will find the boxes to push with a breadth-first search
            box_queue = deque([box])
            while box_queue:
                box = box_queue.popleft()
                # This box will be pushed
                boxes_to_push[box] = None
                # For each position in front of this box
                for w in range(len(WIDE_BOX)):
                    in_front_of_box = box + w + offset
                    # If this box would be pushed into a wall
                    if grid[in_front_of_box] == "#":
                        # Don't push any boxes, and stop trying to
                        box_queue.clear()
                        boxes_to_push.clear()
                        break
                    # If this box would be pushed into another box
                    elif grid[in_front_of_box] in WIDE_BOX:
                        # Get the leftmost position of this box
                        while grid[in_front_of_box] != WIDE_BOX[0]:
                            in_front_of_box -= 1
                        # This box will be pushed
                        boxes_to_push[in_front_of_box] = None
                        # We will consider which boxes this box pushes
                        box_queue.append(in_front_of_box)
            # If no boxes would be pushed
            if not boxes_to_push:
                # Don't move the robot
                continue

            # Push the boxes
            # NOTE The boxes were added in order from back to front. To
            # ensure the boxes in front get pushed into empty space
            # before the boxes in back, we iterate through them in order
            # from front to back.
            for box in reversed(boxes_to_push):
                for w in range(len(WIDE_BOX)):
                    grid[box + w + offset], grid[box + w] = (
                        grid[box + w], grid[box + w + offset]
                    )

        # Move the robot
        robot = new_robot

def compute_gps_coordinate(grid: Grid) -> int:
    return sum(
        100 * int(pos.imag) + int(pos.real)
        for pos, char in grid.items()
        if char == WIDE_BOX[0]
    )

if __name__ == "__main__":
    # grid, movements, robot = extract_data("input_test.txt")
    grid, movements, robot = extract_data("input.txt")
    
    print("grid:", grid)
    print("movements:", movements)
    print("robot:", robot)
    move_robot(grid, movements, robot)
    print(compute_gps_coordinate(grid))




# input_test.txt   => 9021