(ns perfect-numbers)
(require '[clojure.string :as str])

(defn factors [n] 
  (loop [ factors [] current-factor 1 ]
    (if (= n current-factor)
      factors
      (recur (if (= 0 (mod n current-factor)) (cons current-factor factors) factors) (inc current-factor))
      )
    )
  )

(defn aliquot-sum [n]
  (reduce + (factors n))
  )

(defn classify [n] ;; <- arglist goes here
  (cond
    (<= n 0) (throw (IllegalArgumentException. "The provided number should be strictly positive"))
    (= n (aliquot-sum n)) :perfect
    (< n (aliquot-sum n)) :abundant
    (> n (aliquot-sum n)) :deficient
    )
  )
