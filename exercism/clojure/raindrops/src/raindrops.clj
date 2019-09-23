(ns raindrops)

(defn is-divisible-by-3 [number]
  (= 0 (rem number 3)))

(defn is-divisible-by-5 [number]
  (= 0 (rem number 5)))

(defn is-divisible-by-7 [number]
  (= 0 (rem number 7)))

(defn convert [number]
  (str (if (is-divisible-by-3 number) "Pling" "")
       (if (is-divisible-by-5 number) "Plang" "")
       (if (is-divisible-by-7 number) "Plong" "")
       (if (and (not (is-divisible-by-3 number)) (not (is-divisible-by-5 number)) (not (is-divisible-by-7 number))) (str number) "")))