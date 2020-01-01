(ns say
  (:require [clojure.string :as str]))

(def MAXIMUM-VALUE 999999999999)
(def HUNDRED 100)
(def THOUSAND 1000)
(def MILLION 1000000)
(def BILLION 1000000000)

(defn units
  "Spell out in English the number given in arguments if it is a digit (a 'unit' number, this is a number between 0 and 9 inclusive).
   It throws an IllegalArgumentException otherwise"
  [num]
  (condp = num
    0 "zero"
    1 "one"
    2 "two"
    3 "three"
    4 "four"
    5 "five"
    6 "six"
    7 "seven"
    8 "eight"
    9 "nine"))

(defn decades-strictly-below-twenty
  "Spell out in English the number given in arguments if it is a positive number less or equal to 19.
   It throws an IllegalArgumentException otherwise."
  [num]
  (condp = num
    10 "ten"
    11 "eleven"
    12 "twelve"
    13 "thirteen"
    14 "fourteen"
    15 "fifteen"
    16 "sixteen"
    17 "seventeen"
    18 "eighteen"
    19 "nineteen"
    (units num)))

(defn- units-part-of-a-decade [num]
  (let [num num] (if (> num 0) (str "-" (units num)) "")))

(defn decades-and-units
  "Spell out in English the number given in arguments if it is a positive number less or equal to 99.
   It throws an IllegalArgumentException otherwise."
  [num]
  (cond
    (>= 99 num 90) (str "ninety" (units-part-of-a-decade (- num 90)))
    (>= 89 num 80) (str "eighty" (units-part-of-a-decade (- num 80)))
    (>= 79 num 70) (str "seventy" (units-part-of-a-decade (- num 70)))
    (>= 69 num 60) (str "sixty" (units-part-of-a-decade (- num 60)))
    (>= 59 num 50) (str "fifty" (units-part-of-a-decade (- num 50)))
    (>= 49 num 40) (str "forty" (units-part-of-a-decade (- num 40)))
    (>= 39 num 30) (str "thirty" (units-part-of-a-decade (- num 30)))
    (>= 29 num 20) (str "twenty" (units-part-of-a-decade (- num 20)))
    :else (decades-strictly-below-twenty num)))

(defn hundreds
  "Spell out in English the number given in arguments if it is a strictly positive number less or equal to 999.
   For the value 0, the empty string is returned.
   An IllegalArgumentException is thrown otherwise"
  [num]
  (let [number-of-hundreds (quot num HUNDRED) decades-remainder (rem num HUNDRED)]
    (str/trim (str (if (not= 0 number-of-hundreds) (str (units number-of-hundreds) " hundred") "") (if (not= 0 decades-remainder) (str " " (decades-and-units decades-remainder)) "")))))

(defn thousands
  "Spell out in English the number given in arguments if it is a strictly positive number less or equal to 999999.
   For the value 0, the empty string is returned.
   An IllegalArgumentException is thrown otherwise"
  [num]
  (let [number-of-thousands (quot num THOUSAND) thousands-remainder (rem num THOUSAND)]
    (str/trim (str (if (not= 0 number-of-thousands) (str (hundreds number-of-thousands) " thousand") "") (if (not= 0 thousands-remainder) (str " " (hundreds thousands-remainder)) "")))))

(defn millions
  "Spell out in English the number given in arguments if it is a strictly positive number less or equal to 999999999.
   For the value 0, the empty string is returned.
   An IllegalArgumentException is thrown otherwise"
  [num]
  (let [number-of-millions (quot num MILLION) millions-remainder (rem num MILLION)]
    (str/trim (str (if (not= 0 number-of-millions) (str (hundreds number-of-millions) " million") "") (if (not= 0 millions-remainder) (str " " (thousands millions-remainder)) "")))))

(defn billions
  "Spell out in English the number given in arguments if it is a strictly positive number less or equal to 999999999999.
   For the value 0, the empty string is returned.
   An IllegalArgumentException is thrown otherwise"
  [num]
  (let [number-of-billions (quot num BILLION) billions-remainder (rem num BILLION)]
    (str/trim (str (if (not= 0 number-of-billions) (str (hundreds number-of-billions) " billion") "") (if (not= 0 billions-remainder) (str " " (millions billions-remainder)) "")))))

(defn number
  "Spell out in English the number given in arguments if it is a positive number less or equal to 999999999999.
   Otherwise an IllegalArgumentException is thrown for a number that is out of the defined range."
  [num]
  (cond
    (= 0 num) (units num)
    (< 0 num MAXIMUM-VALUE) (billions num)
    :else (throw (IllegalArgumentException.))))