(ns nucleotide-count)

(def possible-nucleotides #{\A \C \G \T})

(defn count [nucleotide strand] 
  (if (possible-nucleotides nucleotide)
    (clojure.core/count (filter #(= nucleotide %) (seq strand)))
    (throw (IllegalArgumentException. "The provided nucleotide does not exist"))
  )
)

;; We could have just built and returned directly the following map
;;{
;; \A (nucleotide-count/count \A strand)
;; \C (nucleotide-count/count \C strand)
;; \G (nucleotide-count/count \G strand)
;; \T (nucleotide-count/count \T strand)
;; }
(defn nucleotide-counts [strand] 
  (apply hash-map (flatten (map #(list % (nucleotide-count/count % strand)) possible-nucleotides)))
)
