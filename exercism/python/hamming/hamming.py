def distance(strand_a, strand_b):
    if len(strand_a) != len(strand_b):
        raise ValueError("The 2 strands must have the same length")
    return len([t for t in zip(strand_a, strand_b) if t[0] != t[1]])
