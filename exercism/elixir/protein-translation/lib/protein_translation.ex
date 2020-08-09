defmodule ProteinTranslation do
  @moduledoc """
  Provides functions to translate a RNA codons sequence into the corresponding proteins sequence :

    - `of_codon\1` translate a given codon in the corresponding protein
    - `of_rna\1` translate a given strand of codon in the corresponding protein sequence
  """

  @doc """
  Given an RNA string, return a list of proteins specified by codons, in order.

  ## Parameters

    - rna : String that represents the rna strand 

  ## Examples

    iex> ProteinTranslation.of_rna("AUGUUUUAA")
    ["Methionine", "Phenylalanine", "Tryptophan"]

  """
  @spec of_rna(String.t()) :: {atom, list(String.t())}
  def of_rna(rna) do
    raw_translation =
      String.graphemes(rna)
      |> Enum.chunk_every(3)
      |> Enum.map(&Enum.join(&1, ""))
      |> Enum.map(&of_codon/1)

    if Enum.any?(raw_translation, fn val -> elem(val, 0) == :error end) do
      {:error, "invalid RNA"}
    else
      {:ok,
       Enum.map(raw_translation, &elem(&1, 1))
       |> Enum.take_while(fn protein -> protein != "STOP" end)}
    end
  end

  defp of_rna()

  @doc """
  Given a codon, return a tuple with the corresponding protein if the codon is correct, a tuple with an error message otherwise.

  The mapping between codon and the corresponding protein is the following :
    - UGU -> Cysteine
    - UGC -> Cysteine
    - UUA -> Leucine
    - UUG -> Leucine
    - AUG -> Methionine
    - UUU -> Phenylalanine
    - UUC -> Phenylalanine
    - UCU -> Serine
    - UCC -> Serine
    - UCA -> Serine
    - UCG -> Serine
    - UGG -> Tryptophan
    - UAU -> Tyrosine
    - UAC -> Tyrosine
    - UAA -> STOP
    - UAG -> STOP
    - UGA -> STOP

  ## Parameters

  ## Examples

    iex> ProteinTranslation.of_codon("UGC")
    {:ok, "Cysteine"}

    iex> ProteinTranslation.of_codon("UAA")
    {:ok, "STOP"}

    iex> ProteinTranslation.of_codon("AAA")
    {:error, "invalid codon"}

  """
  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UGU"), do: {:ok, "Cysteine"}
  def of_codon("UGC"), do: {:ok, "Cysteine"}
  def of_codon("UUA"), do: {:ok, "Leucine"}
  def of_codon("UUG"), do: {:ok, "Leucine"}
  def of_codon("AUG"), do: {:ok, "Methionine"}
  def of_codon("UUU"), do: {:ok, "Phenylalanine"}
  def of_codon("UUC"), do: {:ok, "Phenylalanine"}
  def of_codon("UCU"), do: {:ok, "Serine"}
  def of_codon("UCC"), do: {:ok, "Serine"}
  def of_codon("UCA"), do: {:ok, "Serine"}
  def of_codon("UCG"), do: {:ok, "Serine"}
  def of_codon("UGG"), do: {:ok, "Tryptophan"}
  def of_codon("UAU"), do: {:ok, "Tyrosine"}
  def of_codon("UAC"), do: {:ok, "Tyrosine"}
  def of_codon("UAA"), do: {:ok, "STOP"}
  def of_codon("UAG"), do: {:ok, "STOP"}
  def of_codon("UGA"), do: {:ok, "STOP"}
  def of_codon(_), do: {:error, "invalid codon"}
end
