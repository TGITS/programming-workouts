object Hamming {

    fun compute(leftStrand: String, rightStrand: String): Int {
        if (leftStrand.length != rightStrand.length) {
            throw IllegalArgumentException("left and right strands must be of equal length");
        }

        return leftStrand.asSequence().zip(rightStrand.asSequence()).filter({pair -> pair.first != pair.second}).count()
    }
}
