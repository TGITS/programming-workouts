(ns say)

(declare number)

(def MINIMUM-VALUE 0)
(def MAXIMUM-VALUE 999999999999)
(def DECADE 10)
(def HUNDRED 100)
(def THOUSAND 1000)
(def MILLION 1000000)
(def BILLION 1000000000)

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
    (>= 49 num 40) (str "forty" (unit-part-of-decade (- num 40)))
    (>= 39 num 30) (str "thirty" (unit-part-of-decade (- num 30)))
    (>= 29 num 20) (str "twenty" (unit-part-of-decade (- num 20)))))

(defn- decades [num]
  (if (<= 10 num 19)
    (decades-strictly-below-twenty num)
    (decades-above-twenty num)))

(defn- hundreds [num]
  (let [number-of-hundreds (quot num HUNDRED) decades-remainder (rem num HUNDRED)]
    (str (units number-of-hundreds) " hundred" (if (not= 0 decades-remainder) (str " " (decades decades-remainder)) ""))))

(defn- thousands [num]
  (let [number-of-thousands (quot num THOUSAND) thousands-remainder (rem num THOUSAND)]
    (str (number number-of-thousands) " thousand" (if (not= 0 thousands-remainder) (str " " (hundreds thousands-remainder)) ""))))

(defn- millions [num]
  (let [number-of-millions (quot num MILLION) millions-remainder (rem num MILLION)]
    (str (number number-of-millions) " million" (if (not= 0 millions-remainder) (str " " (thousands millions-remainder)) ""))))

(defn- billions [num]
  (let [number-of-billions (quot num BILLION) billions-remainder (rem num BILLION)]
    (str (number number-of-billions) " billion" (if (not= 0 billions-remainder) (str " " (millions billions-remainder)) ""))))

(defn number [num]
  (cond (or (< num MINIMUM-VALUE) (> num MAXIMUM-VALUE)) (throw (IllegalArgumentException. "Value out of range"))
        (<= MINIMUM-VALUE num 9)  (units num)
        (<= DECADE num 99)  (decades num)
        (<= HUNDRED num 999) (hundreds num)
        (<= THOUSAND num 999999) (thousands num)
        (<= MILLION num 999999999) (millions num)
        (<= BILLION num MAXIMUM-VALUE) (billions num)))