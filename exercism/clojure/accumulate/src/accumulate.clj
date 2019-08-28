(ns accumulate)

; A simple solution using reduce
; (defn accumulate [func v]
;   (reduce #(conj %1 (func %2)) [] v))

; A recursive solution ; probably not the most effective with a vector
(defn accumulate [func v]
  (if
   (or (nil? v) (empty? v)) []
   (cons (func (first v)) (accumulate func (rest v)))))
