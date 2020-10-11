type RnaNucleotide = 'C' | 'G' | 'A' | 'U';

const DNA_TO_RNA: Record<string, RnaNucleotide> = {
    'G' : 'C',
    'C' : 'G',
    'T' : 'A',
    'A' : 'U',
}

class Transcriptor {

    toRna(strand: string): string {
        return strand
                .split('')
                .map(nucleotide => DNA_TO_RNA[nucleotide] || this.invalidInput())
                .join('');
    }

    private invalidInput():void {
        throw new Error('Invalid input DNA.')
    }

}

export default Transcriptor