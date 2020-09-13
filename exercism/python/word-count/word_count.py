import itertools
import re


def count_words(sentence):
    map = {}
    pattern = re.compile("[\s_,;:.!?&@$%^('(?=\s))((?<=\s)')]")
    map = {k: len(list(g)) for k, g in itertools.groupby(
        sorted(pattern.split(sentence.lower()))) if len(k) > 0}
    return map
