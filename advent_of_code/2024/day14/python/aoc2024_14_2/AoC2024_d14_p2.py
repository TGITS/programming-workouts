from collections import namedtuple
from dataclasses import dataclass
from tkinter import *

ROBOT_WIDTH = 5
ROBOT_HEIGHT = 5
PADDING = 10
SPACE_WIDTH = 101
SPACE_HEIGHT = 103

Position = namedtuple("Position", ["x", "y"])
Velocity = namedtuple("Velocity", ["x", "y"])


@dataclass
class Robot:
    px: int
    py: int
    velocity: Velocity
    space_width: int
    space_height: int
    canvas: Canvas
    representation: int

    def move(self):
        current_px = self.px
        current_py = self.py
        current_px += self.velocity.x
        current_py += self.velocity.y

        if current_px < 0:
            current_px = current_px + self.space_width
        elif current_px >= space_width:
            current_px = current_px - self.space_width

        if current_py < 0:
            current_py = current_py + self.space_height
        elif current_py >= space_height:
            current_py = current_py - self.space_height

        # dx = current_px - self.px
        # dy = current_py - self.py
        # self.canvas.move(self.representation, dx, dy)
        self.canvas.moveto(
            self.representation, current_px * ROBOT_WIDTH, current_py * ROBOT_HEIGHT
        )

        self.px = current_px
        self.py = current_py


def get_robots(input_name: str, space_width, space_height, canvas) -> list[Robot]:
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
                    space_width=space_width,
                    space_height=space_height,
                    canvas=canvas,
                    representation=None,
                )
            )
    return robots


def grid(canvas, space_width, space_height):
    for i in range(0, space_width + 1):
        # x0,y0 x1,y1
        canvas.create_line(
            PADDING + i * ROBOT_WIDTH,
            PADDING,
            PADDING + i * ROBOT_WIDTH,
            (space_height + 2) * ROBOT_HEIGHT,
            fill="black",
            width=1,
        )

    for i in range(0, space_height + 1):
        canvas.create_line(
            PADDING,
            PADDING + i * ROBOT_HEIGHT,
            (space_width + 2) * ROBOT_WIDTH,
            PADDING + i * ROBOT_HEIGHT,
            fill="black",
            width=1,
        )


def move_robots_by_block(evt):
    global start_value
    global end_value
    global step
    for i in range(start_value, end_value):
        move_robots(evt)
    start_value = end_value
    end_value += step


def move_robots(evt):
    global robots
    global tick
    tick += 1
    for robot in robots:
        robot.move()
    print("tick:", tick)


def draw_robots(canvas, robots):
    for robot in robots:
        robot.representation = canvas.create_rectangle(
            robot.px * ROBOT_WIDTH,
            robot.py * ROBOT_HEIGHT,
            robot.px * ROBOT_WIDTH + ROBOT_WIDTH,
            robot.py * ROBOT_HEIGHT + ROBOT_HEIGHT,
            fill="Green",
        )


if __name__ == "__main__":
    window = Tk()
    space_width, space_height = SPACE_WIDTH, SPACE_HEIGHT
    canvas_width = 2 * PADDING + (space_width + 1) * ROBOT_WIDTH
    canvas_height = 2 * PADDING + (space_height + 1) * ROBOT_HEIGHT
    window.geometry(
        str(canvas_width + 2 * PADDING) + "x" + str(canvas_height + 2 * PADDING)
    )
    window.title("Advent of Code 2024 Day #14")
    canvas = Canvas(window, width=canvas_width, height=canvas_height, bg="yellow")
    canvas.pack()
    # grid(canvas,space_width, space_height)

    input_name = "input.txt"
    robots = get_robots(input_name, space_width, space_height, canvas)
    # print("robots:", robots)

    # for robot in robots:
    #     robot.move_robot_for_ticks(space_width, space_height,)

    tick = 0
    draw_robots(canvas, robots)
    # start_value = 2513

    for i in range(89):
        move_robots(None)
    start_value = 89
    step = 101
    end_value = start_value + step

    window.bind("<space>", move_robots_by_block)
    window.mainloop()

# 8270 ticks Période de 101, à partir de 89
