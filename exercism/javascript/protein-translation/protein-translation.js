const proteinByCodon = []
proteinByCodon["AUG"] = "Methionine";
proteinByCodon["UUU"] = "Phenylalanine"
proteinByCodon["UUC"] = "Phenylalanine"
proteinByCodon["UUA"] = "Leucine";
proteinByCodon["UUG"] = "Leucine";
proteinByCodon["UCU"] = "Serine";
proteinByCodon["UCC"] = "Serine";
proteinByCodon["UCA"] = "Serine";
proteinByCodon["UCG"] = "Serine";
proteinByCodon["UAU"] = "Tyrosine";
proteinByCodon["UAC"] = "Tyrosine";
proteinByCodon["UGU"] = "Cysteine";
proteinByCodon["UGC"] = "Cysteine";
proteinByCodon["UGG"] = "Tryptophan";
proteinByCodon["UAA"] = "STOP";
proteinByCodon["UAG"] = "STOP";
proteinByCodon["UGA"] = "STOP";

const isEmpty = (str) => (!str || 0 === str.length)

const isBlank = (str) => (!str || /^\s*$/.test(str))

function splitSequence(sequence) {
  let split_sequence = []
  for (var i = 0; i < sequence.length; i = i + 3) {
    split_sequence.push(sequence.slice(i, i + 3));
  }
  return split_sequence;
}

function translate(rna_sequence = '') {
  const proteins_sequence = [];
  
  if (isEmpty(rna_sequence) || isBlank(rna_sequence)) {
    return proteins_sequence;
  }

  const split_rna_sequence = splitSequence(rna_sequence);

  for (var i = 0; i < split_rna_sequence.length; i++) {
    let codon = split_rna_sequence[i];

    if (!(codon in proteinByCodon)) {
      throw new Error("Invalid codon");
    }

    let protein = proteinByCodon[codon];

    if (protein === "STOP") {
      break;
    }

    proteins_sequence.push(protein);
  }
  return proteins_sequence;
}

export { translate }