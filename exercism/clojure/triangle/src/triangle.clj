(ns triangle)

(defn type [side1 side2 side3]
  (cond
    (or
     (or (<= (+ side2 side3) side1)
         (<= (+ side1 side3) side2)
         (<= (+ side1 side2) side3))
     (= 0 side1 side2 side3)) :illogical
    (= side1 side2 side3) :equilateral
    (or (= side1 side2)
        (= side1 side3)
        (= side2 side3)) :isosceles
    :else :scalene))
