defmodule WordCount do
  @doc """
  Count the number of words in the sentence.

  Words are compared case-insensitively.
  """
  @spec count(String.t()) :: map
  def count(sentence) do
    String.split(sentence,~r{\s|_},trim: true) 
    |> Enum.map(&(String.replace(&1,~r{[\s,:;!&@%\?\$\^]},""))) 
    |> Enum.filter(&(String.length(&1) > 0)) 
    |> Enum.map(&(String.downcase(&1))) 
    |> Enum.frequencies()
  end
end
