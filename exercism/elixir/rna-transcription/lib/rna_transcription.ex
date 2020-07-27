defmodule RnaTranscription do
  @doc """
  Transcribes a character list representing DNA nucleotides to RNA

  ## Examples

  iex> RnaTranscription.to_rna('ACTG')
  'UGAC'
  """
  @spec to_rna([char]) :: [char]
  def to_rna(dna) do
    Enum.map(dna, &transcript/1)
  end

  @spec transcript(char) :: char
  def transcript(?G), do: ?C
  def transcript(?C), do: ?G
  def transcript(?T), do: ?A
  def transcript(?A), do: ?U
  def transcript(c),  do: raise RuntimeError, message: "The value #{c} is not valid"

end
