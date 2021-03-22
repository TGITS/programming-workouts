def find_anagrams(word, candidates):
    return [candidate for candidate in candidates
            if word.lower() != candidate.lower() and sorted(list(word.lower())) == sorted(list(candidate.lower()))]
