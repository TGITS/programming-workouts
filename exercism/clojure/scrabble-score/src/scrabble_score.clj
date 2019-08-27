(ns scrabble-score
  (:require [clojure.string :as string]))

(defn score-letter [letter]
  (cond
    (contains? #{"A" "E" "I" "O" "U" "L" "N" "R" "S" "T"} (string/upper-case letter)) 1
    (contains? #{"D" "G"} (string/upper-case letter)) 2
    (contains? #{"B" "C" "M" "P"} (string/upper-case letter)) 3
    (contains? #{"F" "H" "V" "W" "Y"} (string/upper-case letter)) 4
    (= "K" (string/upper-case letter)) 5
    (contains? #{"J" "X"} (string/upper-case letter)) 8
    (contains? #{"Q" "Z"} (string/upper-case letter)) 10))

(defn score-word [word]
  (->>
   (seq word)
   (map str)
   (map scrabble-score/score-letter)
   (reduce +)))
