object SumOfMultiples {
  def sum(factors: Set[Int], limit: Int): Int = {
    (1 until limit).toList.map { i => if (factors exists { f => (i % f == 0)} ) i else 0 }.sum
  }
}

