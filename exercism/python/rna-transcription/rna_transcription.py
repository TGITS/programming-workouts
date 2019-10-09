DNA_TO_RNA_MAPPER = {
    'G' : 'C',
    'C' : 'G',
    'T' : 'A',
    'A' : 'U'
}

def to_rna(dna_strand):
    return ''.join([DNA_TO_RNA_MAPPER[letter] for letter in dna_strand])
