type Cytosine = 'C';
type Guanine = 'G';
type Adenine = 'A';
type Thymine = 'T';
type Uracil = 'U';
type RnaNucleotide = Cytosine | Guanine | Adenine | Uracil;
type DnaNucleotide = Cytosine | Guanine | Adenine | Thymine;

class Transcriptor {


    toRna(strand: string): string {
        return strand.split('').map(n => this.toRnaNucleotide(n)).join('');
    }

    private toRnaNucleotide(nucleotide: string): RnaNucleotide {
        switch (nucleotide) {
            case 'G': return 'C';
            case 'C': return 'G';
            case 'T': return 'A';
            case 'A': return 'U';
            default: {
                throw new Error('Invalid input DNA.')
            }
        }
    }
}

export default Transcriptor