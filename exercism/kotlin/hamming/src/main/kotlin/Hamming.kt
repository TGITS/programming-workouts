object Hamming {

    fun compute(leftStrand: String, rightStrand: String): Int {
        require(leftStrand.length == rightStrand.length) { "left and right strands must be of equal length" }

        return leftStrand.asSequence().zip(rightStrand.asSequence()).filter({pair -> pair.first != pair.second}).count()
    }
}
