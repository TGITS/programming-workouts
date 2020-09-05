class Transcriptor {
    toRna(strand: string): string {
        try {
            return strand.split('').map(n => this.toRnaNucleotide(n)).join('');
        }
        catch (e) {
            throw new Error('Invalid input DNA.');
        }
    }

    private toRnaNucleotide(nucleotide: string): string {
        switch (nucleotide) {
            case 'G': return 'C';
            case 'C': return 'G';
            case 'T': return 'A';
            case 'A': return 'U';
            default: {
                throw new Error('Invalid input nucleotide.')
            }
        }
    }
}

export default Transcriptor