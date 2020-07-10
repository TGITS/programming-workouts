defmodule ExFunctions1 do
  @moduledoc """
  Documentation for ExFunctions1.
  """

  @doc """
  list_concat concatenates 2 lists


  ## Examples

      iex> ExFunctions1.list_concat([:a, :b], [:c, :d])
      [:a, :b, :c, :d]

  """
  @spec list_concat(list(), list()) :: list()
  def list_concat(list_1, list_2) do
    list_1 ++ list_2
  end

  @doc """
  sum sums 3 numbers


  ## Examples

      iex> ExFunctions1.sum(1,2,3)
      6

  """
  @spec sum(number(), number(), number()) :: number()
  def sum(x, y, z) do
    x + y + z
  end

  @doc """
  pair_tuple_to_list transform a pair tuple in a list of 2 elements


  ## Examples

      iex> ExFunctions1.pair_tuple_to_list({ 1234, 5678 })
      [1234, 5678]

  """
  @spec pair_tuple_to_list(tuple()) :: list()
  def pair_tuple_to_list({p_1, p_2}) do
    [p_1, p_2]
  end
end
