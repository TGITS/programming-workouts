def value(colors):
    return color_code(colors[0])*10 + color_code(colors[1])


def color_code(color):
    return ['black', 'brown', 'red', 'orange', 'yellow', 'green', 'blue', 'violet', 'grey', 'white'].index(color)
