dna_nucleotides = ['G', 'C', 'T', 'A']
rna_nucleotides = ['C', 'G', 'A', 'U']

print("La liste des nucléotides de l'ADN :\n",
      dna_nucleotides)
print("La liste des nucléotides de l'ARN :\n", rna_nucleotides)
print("Résultat de l'application de zip sur les 2 listes :\n",
      zip(dna_nucleotides, rna_nucleotides))
print("Résultat de l'application de zip sur les 2 listes après unpacking :\n",
      *zip(dna_nucleotides, rna_nucleotides))
print("Résultat de l'application de zip sur les 2 listes après conversion en liste :\n",
      list(zip(dna_nucleotides, rna_nucleotides)))
print("Résultat de l'application de zip sur les 2 listes après conversion en tuple :\n",
      tuple(zip(dna_nucleotides, rna_nucleotides)))
print("Résultat de l'application de zip sur les 2 listes après conversion en dictionnaire :\n",
      dict(zip(dna_nucleotides, rna_nucleotides)))

print("\n###########\n")

for pair in zip(dna_nucleotides, rna_nucleotides):
    print("Pair de nucléotides ADN - ARN :", pair)
