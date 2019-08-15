(ns collatz-conjecture)

(defn collatz [num]
  (if (<= num 0) (throw (IllegalArgumentException. "The provided number should be strictly positive"))
      (loop [n num step 0]
        (if (= 1 n)
          step
          (recur (if (= 0 (mod n 2)) (/ n 2) (+ (* n 3) 1))
                 (inc step))
          )
        )
      )
  )
