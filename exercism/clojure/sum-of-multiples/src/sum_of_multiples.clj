(ns sum-of-multiples)

(defn compute-modulos [seq-of-multiples number]
  (map (partial mod number) seq-of-multiples))

(defn has-some-multiples? [seq-of-multiples number]
  (some (partial = 0) (compute-modulos seq-of-multiples number)))

(defn sum-of-multiples [seq-of-multiples number]
  (->>
   (range 1 number)
   (filter (partial sum-of-multiples/has-some-multiples? seq-of-multiples))
   (distinct)
   (reduce +)))