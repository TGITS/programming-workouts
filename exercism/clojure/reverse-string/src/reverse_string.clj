(ns reverse-string)
(require '[clojure.string :as str])

(defn reverse-string [s]
  (loop [char-seq (seq s) rs ""]
    (if (empty? char-seq)
      rs
      (recur (rest char-seq) (str (first char-seq) rs)))
    )
  )