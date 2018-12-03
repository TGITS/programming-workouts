object SumOfMultiples {
  def sum(factors: Set[Int], limit: Int): Int = {
    (1 to (limit-1)).toList.flatMap {i => factors.map{f => if (i % f == 0) i else 0}}.sum
  }
}

