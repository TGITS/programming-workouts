object Hamming {

    fun compute(leftStrand: String, rightStrand: String): Int {
        if (leftStrand.length != rightStrand.length) {
            throw IllegalArgumentException("left and right strands must be of equal length");
        }

        var countDifferences = 0
        if (leftStrand.length > 0) {
            for (i in 0 until leftStrand.length) {
                if (leftStrand[i] != rightStrand[i]) countDifferences++
            }
        }
        
        return countDifferences

        //return leftStrand.asSequence().zip(rightStrand.asSequence()).filter({pair -> pair.first != pair.second}).count()
    }
}
