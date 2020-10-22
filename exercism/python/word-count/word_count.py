import re
from collections import Counter


def count_words(sentence):
    count_by_word = {}
    pattern = re.compile(
        "'+[_,;:.!?&@$%^\\s]+'*|[_,;:.!?&@$%^\\s]+'+|[_,;:.!?&@$%^\\s]+|'+$|^'+")
    words = [word for word in pattern.split(sentence.lower()) if word]
    return Counter(words)
