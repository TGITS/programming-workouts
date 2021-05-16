import itertools

__version__== '0.1.0'

# Web-safe colors : https://en.wikipedia.org/wiki/Web_colors#Web-safe_colors
# 6 possible values for R, G & B components : "00", "33", "66", "99", "CC", "FF"

hexadecimal_components = ("00", "33", "66", "99", "CC", "FF")
web_safe_colors = ["".join(rgb) for rgb in itertools.product(hexadecimal_components, repeat=3)]
print(f"Tuple of web safe colors : {web_safe_colors}")
