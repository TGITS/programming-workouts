(ns difference-of-squares)

(defn sum-of-squares [upper-bound]
  (reduce + (map #(* %1 %1) (range 1 (+ 1 upper-bound)))))

(defn square-of-sum [upper-bound]
  (int (Math/pow (reduce + (range 1 (+ 1 upper-bound))) 2)))

(defn difference [upper-bound]
  (- (square-of-sum upper-bound) (sum-of-squares upper-bound)))