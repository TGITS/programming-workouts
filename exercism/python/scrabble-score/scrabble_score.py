from collections import Counter
letter_by_points = {
    1: 'AEIOULNRST',
    2: 'DG',
    3: 'BCMP',
    4: 'FHVWY',
    5: 'K',
    8: 'JX',
    10: 'QZ'
}

points_by_letter = {letter: score
                    for score, letters in letter_by_points.items()
                    for letter in letters}


def score(word):
    return sum([points_by_letter[key] * value for key, value in Counter(word.upper()).items()])
