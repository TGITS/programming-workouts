(ns beer-song
  (:require [clojure.string :as str]))

(defn bottle-or-bottles [num]
  (if (> num 1)
    (str num " bottles")
    (str num " bottle")))

(defn verse
  "Returns the nth verse of the song."
  [num]
  (cond
    (and (<= num 99) (>= num 2)) (str (bottle-or-bottles num) " of beer on the wall, " (bottle-or-bottles num) " of beer.\nTake one down and pass it around, " (bottle-or-bottles (dec num)) " of beer on the wall.\n")
    (= num 1) (str (bottle-or-bottles num) " of beer on the wall, " (bottle-or-bottles num) " of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n")
    (= num 0) "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start] (sing start 0))
  ([start end]
   (->>
    (range end (inc start))
    (reverse)
    (map #(verse %1))
    (str/join "\n"))))
