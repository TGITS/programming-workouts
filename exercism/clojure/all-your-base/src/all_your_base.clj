(ns all-your-base
  (:require [clojure.string :as str]))

(defn to-base-10 [input-base number-as-seq]
  (->>
   number-as-seq
   (reverse)
   (map vector (range 0 (count number-as-seq)))
   (map #(int (* (nth %1 1) (Math/pow input-base (nth %1 0)))))
   (reduce +)))

(defn from-base-10 [output-base number-in-base-10]
  (loop [number number-in-base-10 remainder-list '()]
    (if (= 0 number)
      remainder-list
      (recur (quot number output-base) (cons (rem number output-base) remainder-list)))))

(defn convert [input-base number-as-seq output-base]
  (cond
    (or (>= 1 input-base) (>= 1 output-base)) nil
    (empty? number-as-seq) '()
    (= 0 (reduce + number-as-seq)) '(0)
    (> (count (filter #(< %1 0) number-as-seq)) 0) nil
    (> (count (filter #(>= %1 input-base) number-as-seq)) 0) nil
    :else (from-base-10 output-base (to-base-10 input-base number-as-seq))))
