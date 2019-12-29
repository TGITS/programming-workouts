(ns anagram
  (:require [clojure.string :as str]))

(defn is-anagram
  "Test if the given argument 'candidate-anagram' is an anagram of the given argument 'word'. The function is case-insensitive. 
   Furthermore a word is not considered as its own anagram : if the 2 parameters are the same word (potentially with different case), the function returns false"
  [word candidate-anagram]
  (let [to-lower-case (str/lower-case word)
        as-sorted-char-seq (sort to-lower-case)
        prospect-to-lower-case (str/lower-case candidate-anagram)]
    (and (not= to-lower-case prospect-to-lower-case) ;; A word is not its own anagrame
         (= as-sorted-char-seq (sort prospect-to-lower-case))))) ;; if the 2 words are anagrams, they have the same sorted sequence of characters

(defn anagrams-for
  "Returns the sequence of anagrams of the given argument 'word' that are in the 'prospect_list' argument. The function is case-insensitive. 
   Furthermore a word is not considered as its own anagram."
  [word prospect-list]
  (filter #(is-anagram word %1) prospect-list))