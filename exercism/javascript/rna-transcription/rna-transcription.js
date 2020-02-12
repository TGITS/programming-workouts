const DNA2RNAMapping = {
  'G' : 'C',
  'C' : 'G',
  'T' : 'A',
  'A' : 'U'
};

export const toRna = (dna_strand) => {
  if (!dna_strand || dna_strand.trim().length === 0) {
      return '';
  }
  return Array.from(dna_strand).map((dna_letter) => DNA2RNAMapping[dna_letter]).join('');
};
