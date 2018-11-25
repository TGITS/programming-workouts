object SumOfMultiples {
  def sum(factors: Set[Int], limit: Int): Int = {
    (1 to limit).toList.flatmap( i => factors.map(f => if (i % f == 0) f else 0).sum
  }
}

