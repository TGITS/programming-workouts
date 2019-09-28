(ns rna-transcription)

(def nucleotide-transcription-map {\A \U \T \A \C \G \G \C})

(defn transcribe [nucleotide]
  (if (contains? nucleotide-transcription-map nucleotide )
    (get nucleotide-transcription-map nucleotide)
    (throw (AssertionError. "The provided nucleotide does not exist"))
    )
  )

(defn to-rna [dna]
  (apply str (map rna-transcription/transcribe dna)))

