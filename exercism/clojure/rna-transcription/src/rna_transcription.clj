(ns rna-transcription)

(defn transcribe [nucleotide]
  (cond
    (= nucleotide \A) \U
    (= nucleotide \T) \A
    (= nucleotide \C) \G
    (= nucleotide \G) \C
    :else (throw (AssertionError. "The provided nucleotide does not exist"))))

(defn to-rna [dna]
  (apply str (map rna-transcription/transcribe (seq dna))))

