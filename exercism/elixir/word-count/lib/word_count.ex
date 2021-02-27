defmodule WordCount do
  @doc """
  Count the number of words in the sentence.

  Words are compared case-insensitively.
  """
  @spec count(String.t()) :: map
  def count(sentence) do
    String.split(String.downcase(sentence),~r/[^[:alnum:]-]+/u, trim: true)
    |> Enum.frequencies()
  end
end
