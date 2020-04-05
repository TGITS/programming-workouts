def distance(strand_a, strand_b):
    if len(strand_a) != len(strand_b):
        raise ValueError("The 2 strands must have the same length")
    return len([1 for elt_a, elt_b in zip(strand_a, strand_b) if elt_a != elt_b])
