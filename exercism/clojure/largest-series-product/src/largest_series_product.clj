(ns largest-series-product)

(defn largest-product [size input]
  (cond
    (and (= size 0) (re-matches #"\d*" input)) 1
    (or (> size (count input)) (= 0 (count input)) (<= size 0) (not (re-matches #"\d*" input))) (throw (IllegalArgumentException. "Invalid arguments"))
    :else (->> input
               (map #(Character/digit % 10))
               (partition size 1)
               (map #(reduce * %))
               (apply max))))
