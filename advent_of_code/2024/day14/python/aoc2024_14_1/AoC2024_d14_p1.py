from collections import namedtuple
from dataclasses import dataclass

Position = namedtuple("Position", ["x", "y"])
Velocity = namedtuple("Velocity", ["x", "y"])


@dataclass
class Robot:
    px: int
    py: int
    velocity: Velocity

    def move_robot(self, space_width: int, space_height: int):
        current_px = self.px
        current_py = self.py
        current_px += self.velocity.x
        current_py += self.velocity.y

        if current_px < 0:
            current_px = current_px + space_width
        elif current_px >= space_width:
            current_px = current_px - space_width

        if current_py < 0:
            current_py = current_py + space_height
        elif current_py >= space_height:
            current_py = current_py - space_height

        self.px = current_px
        self.py = current_py

    def move_robot_for_ticks(self, space_width: int, space_height: int, ticks: int):
        for _ in range(ticks):
            self.move_robot(space_width, space_height)


@dataclass
class Quadrant:
    id: int
    upperLeftCorner: Position
    width: int
    height: int
    robots: list[Robot]

    def is_robot_in(self, robot: Robot) -> bool:
        return (
            self.upperLeftCorner.x <= robot.px < self.upperLeftCorner.x + self.width
            and self.upperLeftCorner.y
            <= robot.py
            < self.upperLeftCorner.y + self.height
        )

    def add_eligible_robot(self, robot: Robot):
        if self.is_robot_in(robot):
            self.robots.append(robot)

    def count_robots_in(self) -> int:
        return len(self.robots)


def extract_data(input_name: str) -> list[Robot]:
    robots = []
    with open(input_name, "r") as input:
        for line in input:
            raw_data = line.strip().split()
            position = [
                int(coord) for coord in raw_data[0].removeprefix("p=").split(",")
            ]
            velocity = [int(vel) for vel in raw_data[1].removeprefix("v=").split(",")]
            robots.append(
                Robot(
                    px=position[0],
                    py=position[1],
                    velocity=Velocity(x=velocity[0], y=velocity[1]),
                )
            )
    return robots


if __name__ == "__main__":
    # input_name, space_width, space_height, ticks = "input_test.txt", 11, 7, 100
    input_name, space_width, space_height, ticks = "input.txt", 101, 103, 100

    robots = extract_data(input_name)
    # print("robots:", robots)

    quadrant_width = space_width // 2
    quadrant_height = space_height // 2
    quadrants = []
    # Clockwise
    quadrants.append(
        Quadrant(
            id=1,
            upperLeftCorner=Position(x=0, y=0),
            width=quadrant_width,
            height=quadrant_height,
            robots=[],
        )
    )
    quadrants.append(
        Quadrant(
            id=2,
            upperLeftCorner=Position(x=quadrant_width + 1, y=0),
            width=quadrant_width,
            height=quadrant_height,
            robots=[],
        )
    )
    quadrants.append(
        Quadrant(
            id=3,
            upperLeftCorner=Position(x=quadrant_width + 1, y=quadrant_height + 1),
            width=quadrant_width,
            height=quadrant_height,
            robots=[],
        )
    )
    quadrants.append(
        Quadrant(
            id=4,
            upperLeftCorner=Position(x=0, y=quadrant_height + 1),
            width=quadrant_width,
            height=quadrant_height,
            robots=[],
        )
    )
    # print()
    # print("quadrants:", quadrants)
    # print()

    for robot in robots:
        robot.move_robot_for_ticks(space_width, space_height, ticks)
        for quadrant in quadrants:
            quadrant.add_eligible_robot(robot)

    # print()
    # print("robots after 100 ticks:", robots)
    # print()
    # print("quadrants:", quadrants)
    # print()

    safety_factor = 1
    for quadrant in quadrants:
        # print("Robots in quadrant", quadrant.id, ":", quadrant.count_robots_in())
        safety_factor = safety_factor * quadrant.count_robots_in()

    print()
    print("safety factor: ", safety_factor)
    print()
