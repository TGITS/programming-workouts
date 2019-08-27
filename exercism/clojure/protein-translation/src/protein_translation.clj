(ns protein-translation)

(defn translate-codon [codon]
  (cond
    (= "AUG" codon) "Methionine"
    (or (= "UUU" codon) (= "UUC" codon)) "Phenylalanine"
    (or (= "UUA" codon) (= "UUG" codon)) "Leucine"
    (or (= "UCU" codon) (= "UCC" codon) (= "UCA" codon) (= "UCG" codon)) "Serine"
    (or (= "UAU" codon) (= "UAC" codon)) "Tyrosine"
    (or (= "UGU" codon) (= "UGC" codon)) "Cysteine"
    (= "UGG" codon) "Tryptophan"
    (or (= "UAA" codon) (= "UAG" codon) (= "UGA" codon)) "STOP"))

(defn translate-rna [rna]
  (->>
   (seq rna)
   (partition 3)
   (map clojure.string/join)
   (map protein-translation/translate-codon)
   (take-while #(not (= "STOP" %1)))))
