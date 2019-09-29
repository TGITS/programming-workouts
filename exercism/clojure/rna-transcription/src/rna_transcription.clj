(ns rna-transcription)

(def nucleotide-transcription-map {\A \U \T \A \C \G \G \C})

(defn transcribe [nucleotide]
  (doto
   (nucleotide-transcription-map nucleotide)
    (assert)))

(defn to-rna [dna]
  (apply str (map rna-transcription/transcribe dna)))