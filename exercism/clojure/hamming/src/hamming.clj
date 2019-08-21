(ns hamming)

(defn distance [strand1 strand2]
  (if (not (= (count strand1) (count strand2)))
    nil
    (loop [char-seq1 (seq strand1) char-seq2 (seq strand2) counter 0]
      (if (empty? char-seq1)
        counter
        (recur (rest char-seq1) (rest char-seq2) (if (= (first char-seq1) (first char-seq2)) counter (inc counter)))))))
