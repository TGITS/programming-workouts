import itertools
import re


def count_words(sentence):
    map = {}
    pattern = re.compile(
        "'+[_,;:.!?&@$%^\\s]+'*|[_,;:.!?&@$%^\\s]+'+|[_,;:.!?&@$%^\\s]+|'+$|^'+")
    words = pattern.split(sentence.lower())
    map = {k: len(list(g)) for k, g in itertools.groupby(
        sorted([word for word in words if word is not None and len(word) > 0]))}
    return map
