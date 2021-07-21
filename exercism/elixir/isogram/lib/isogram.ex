defmodule Isogram do
  @doc """
  Determines if a word or sentence is an isogram
  """
  @spec isogram?(String.t()) :: boolean
  def isogram?(sentence) do
    sentence
    |> String.downcase()
    |> String.replace(~r/[\-\s]+/, "")
    |> String.to_char_list()
    |> check_charlist([])
  end

  defp check_charlist(charlist, char_already_found) do
    case charlist do
      [head | tail] ->
        if head in char_already_found do
          false
        else
          check_charlist(tail, [head | char_already_found])
        end

      [] ->
        true
    end
  end
end
