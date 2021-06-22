import itertools
from jinja2 import Environment, PackageLoader, select_autoescape

# Web-safe colors : https://en.wikipedia.org/wiki/Web_colors#Web-safe_colors
# 6 possible values for R, G & B components : "00", "33", "66", "99", "CC", "FF"


def get_all_web_safe_colors():
    return [
        "#" + "".join(rgb)
        for rgb in itertools.product(("00", "33", "66", "99", "CC", "FF"), repeat=3)
    ]


def get_rendered_template():
    print(f"List of web safe colors : {get_all_web_safe_colors()}")
    env = Environment(
    loader=PackageLoader("web_safe_colors"), autoescape=select_autoescape())
    template = env.get_template("web_safe_colors.jinja2")
    rendered_template = template.render(web_safe_colors=get_all_web_safe_colors())
    print(rendered_template)
    return rendered_template

def main():
    with open("./web_safe_colors.html", 'w') as file:
        file.write(get_rendered_template())

if __name__ == "__main__":
    main()
