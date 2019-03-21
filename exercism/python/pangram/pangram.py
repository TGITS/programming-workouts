def is_pangram(sentence):
    if not sentence:
        return False
    
    return set('abcdefghijklmnopqrstuvwxyz').issubset(set(sentence.lower()))
