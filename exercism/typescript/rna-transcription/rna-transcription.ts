import { CANCELLED } from "dns";

type Cytosine = 'C';
type Guanine = 'G';
type Adenine = 'A';
type Thymine = 'T';
type Uracil = 'U';
type RnaNucleotide = Cytosine | Guanine | Adenine | Uracil;
type DnaNucleotide = Cytosine | Guanine | Adenine | Thymine;

const DNA_TO_RNA: Record<DnaNucleotide, RnaNucleotide> = {
    G : 'C',
    C : 'G',
    T : 'A',
    A : 'U',
}

class Transcriptor {


    toRna(strand: string): string {
        return strand.split('').map(n => this.toRnaNucleotide(n)).join('');
    }

    private toRnaNucleotide(nucleotide: string): RnaNucleotide {
        switch (nucleotide) {
            case 'G': return DNA_TO_RNA.G;
            case 'C': return DNA_TO_RNA.C;
            case 'T': return DNA_TO_RNA.T;
            case 'A': return DNA_TO_RNA.A;
            default: {
                throw new Error('Invalid input DNA.')
            }
        }
    }
}

export default Transcriptor