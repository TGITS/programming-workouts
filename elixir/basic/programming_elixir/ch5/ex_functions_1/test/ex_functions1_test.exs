defmodule ExFunctions1Test do
  use ExUnit.Case
  doctest ExFunctions1

  test "concatenate 2 lists of atoms" do
    assert ExFunctions1.list_concat([:a, :b], [:c, :d]) == [:a, :b, :c, :d]
  end

  test "sum three integers" do
    assert ExFunctions1.sum(1,2,3) == 6
  end

  test "transform a pair of integers into a list of integers" do
    assert ExFunctions1.pair_tuple_to_list({ 1234, 5678 }) == [1234, 5678]
  end
end
