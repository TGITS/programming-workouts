defmodule ProteinTranslation do
  @doc """
  Given an RNA string, return a list of proteins specified by codons, in order.
  """
  @spec of_rna(String.t()) :: {atom, list(String.t())}
  def of_rna(rna) do
    rna
    |> Enum.map(&of_codon/1)
  end

  @doc """
  Given a codon, return the corresponding protein

  UGU -> Cysteine
  UGC -> Cysteine
  UUA -> Leucine
  UUG -> Leucine
  AUG -> Methionine
  UUU -> Phenylalanine
  UUC -> Phenylalanine
  UCU -> Serine
  UCC -> Serine
  UCA -> Serine
  UCG -> Serine
  UGG -> Tryptophan
  UAU -> Tyrosine
  UAC -> Tyrosine
  UAA -> STOP
  UAG -> STOP
  UGA -> STOP
  """
  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UGU"), do: {:ok, "Cysteine"}
  def of_codon("UGC"), do: {:ok, "Cysteine"}
  def of_codon("UUA"), do: {:ok, "Leucine"}

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UUG") do
    {:ok, "Leucine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("AUG") do
    {:ok, "Methionine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UUU") do
    {:ok, "Phenylalanine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UUC") do
    {:ok, "Phenylalanine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UCU") do
    {:ok, "Serine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UCC") do
    {:ok, "Serine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UCA") do
    {:ok, "Serine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UCG") do
    {:ok, "Serine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UGG") do
    {:ok, "Tryptophan"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UAU") do
    {:ok, "Tyrosine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UAC") do
    {:ok, "Tyrosine"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UAA") do
    {:ok, "STOP"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UAG") do
    {:ok, "STOP"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon("UGA") do
    {:ok, "STOP"}
  end

  @spec of_codon(String.t()) :: {atom, String.t()}
  def of_codon(_) do
    {:error, "invalid codon"}
  end
end

