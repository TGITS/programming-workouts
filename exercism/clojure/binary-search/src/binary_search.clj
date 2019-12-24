(ns binary-search)

(defn middle [sequence]
  (quot (count sequence) 2))

(defn index [sequence]
  (map vector (iterate inc 0) sequence))

(defn search-for [elem sorted-sequence]
  (let [sequence-of-vector (index sorted-sequence)]
    (loop [sequence sequence-of-vector]
      (let [middle-index (middle sequence)
            middle-vector-value (nth sequence middle-index)
            middle-value (second middle-vector-value)]
        (cond
          (= elem middle-value) (first middle-vector-value)
          (or (empty? sequence) (= 1 (count sequence))) (throw (Exception. "Value not found"))
          (< elem middle-value) (recur (take middle-index sequence))
          (> elem middle-value) (recur (drop middle-index sequence)))))))
