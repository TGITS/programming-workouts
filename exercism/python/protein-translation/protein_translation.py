def proteins(strand):
    protein_by_codon = {
        "AUG": "Methionine", "UUU": "Phenylalanine", "UUC": "Phenylalanine",
        "UUA": "Leucine", "UUG": "Leucine", "UCU": "Serine", "UCC": "Serine", "UCA": "Serine", "UCG": "Serine",
        "UAU": "Tyrosine", "UAC": "Tyrosine", "UGU": "Cysteine", "UGC": "Cysteine", "UGG": "Tryptophan",
        "UAA": "STOP", "UAG": "STOP", "UGA": "STOP"
    }
    proteins_list = []
    for i in range(0, len(strand), 3):
        proteins_list.append(protein_by_codon[strand[i:i+3]])
    if "STOP" not in proteins_list:
        return proteins_list
    else:
        return proteins_list[0:proteins_list.index("STOP")]
