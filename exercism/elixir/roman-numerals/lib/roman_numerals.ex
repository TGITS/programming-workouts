defmodule RomanNumerals do
  @doc """
  Convert the number to a roman number.
  """
  @spec numeral(pos_integer) :: String.t()
  # def numeral(number) do
  # end
  def numeral(0), do: ""
  def numeral(number) when number > 0 and number < 4, do: "I" <> numeral(number - 1)
  def numeral(4), do: "IV"
  def numeral(number) when number > 4 and number < 9, do: "V" <> numeral(number - 5)
  def numeral(9), do: "IX"
  def numeral(number) when number > 9 and number < 40, do: "X" <> numeral(number - 10)
  def numeral(number) when number > 39 and number < 50, do: "XL" <> numeral(number - 40)
  def numeral(number) when number > 49 and number < 90, do: "L" <> numeral(number - 50)
  def numeral(number) when number > 89 and number < 100, do: "XC" <> numeral(number - 90)
  def numeral(number) when number > 99 and number < 400, do: "C" <> numeral(number - 100)
  def numeral(number) when number > 399 and number < 500, do: "CD" <> numeral(number - 400)
  def numeral(number) when number > 499 and number < 900, do: "D" <> numeral(number - 500)
  def numeral(number) when number > 899 and number < 1000, do: "CM" <> numeral(number - 900)
  def numeral(number) when number > 999 and number < 3999, do: "M" <> numeral(number - 1000)
end
