(ns word-count
  (:require [clojure.string :as str]))

(defn word-count
  "Returns a map for which each word in the given parameter string 's' is a key and the corresponding value is the number of occurrences of this word
   A word here is an alphanumeric sequence of 1 character or more. All non-alphanumeric characters are ignored and the case of each word is normalized to lowercase.
   For example 'word' 'WORD' 'WorD' 'word!,' are considered the same word occurence."
  [s]
  (as-> s input
    (str/replace input #"\W+" " ")
    (str/split  input #"\s")
    (map str/lower-case input)
    (frequencies input)))