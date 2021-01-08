import re


def abbreviate(words):
    return "".join([word[:1] for word in re.split(r"[\s\-_]+", words)]).upper()
