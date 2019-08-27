(ns hexadecimal)

(defn hex-to-int [hexadecimal-as-string]
  (if (re-matches #"([0-9]|[A-F]|[a-f])+" hexadecimal-as-string)
    (->>
     (seq hexadecimal-as-string)
     (map #(Character/digit %1 16))
     (reverse)
     (map vector (range 0 (count hexadecimal-as-string)))
     (map #(* (nth %1 1) (Math/pow 16 (nth %1 0))))
     (reduce +)
     (int))
    0))
