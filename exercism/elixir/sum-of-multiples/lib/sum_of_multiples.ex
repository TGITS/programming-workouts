defmodule SumOfMultiples do
  @doc """
  Adds up all numbers from 1 to a given end number that are multiples of the factors provided.
  """
  @spec to(non_neg_integer, [non_neg_integer]) :: non_neg_integer
  def to(1, _), do: 0
  def to(limit, factors), do: 1..(limit-1) |> Enum.filter(&any_factors?(&1, factors)) |> Enum.sum()

  @spec any_factors?(non_neg_integer, [non_neg_integer]) :: boolean
  def any_factors?(num, factors), do: factors |> Enum.any?(&(rem(num, &1) == 0))
end
