(ns say)

(defn- units [num]
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

(defn- decades-strictly-below-twenty [num]
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
    19 "nineteen"))

(defn- unit-part-of-decade [num]
  (let [num num] (if (> num 0) (str "-" (units num)) "")))

(defn- decades-above-twenty [num]
  (cond
    (>= 99 num 90) (str "ninety" (unit-part-of-decade (- num 90)))
    (>= 89 num 80) (str "eighty" (unit-part-of-decade (- num 80)))
    (>= 79 num 70) (str "seventy" (unit-part-of-decade (- num 70)))
    (>= 69 num 60) (str "sixty" (unit-part-of-decade (- num 60)))
    (>= 59 num 50) (str "fifty" (unit-part-of-decade (- num 50)))
    (>= 49 num 40) (str "fifty" (unit-part-of-decade (- num 40)))
    (>= 39 num 30) (str "fifty" (unit-part-of-decade (- num 30)))
    (>= 29 num 20) (str "twenty" (unit-part-of-decade (- num 20)))))

(defn- decades [num]
  (if (<= 10 num 19)
    (decades-strictly-below-twenty num)
    (decades-above-twenty num)))

(defn- hundreds [num]
  (let [number-of-hundreds (quot num 100) remainder-decades (rem num 100)]
    (str (units number-of-hundreds) " hundred" (if (not= 0 remainder-decades) (str " " (decades remainder-decades)) ""))))

(defn number [num]
  (cond (or (< num 0) (> num 999999999999)) (throw (IllegalArgumentException. "Value out of range"))
        (<= 0 num 9)  (units num)
        (<= 10 num 99)  (decades num)
        (<= 100 num 999) (hundreds num)))
