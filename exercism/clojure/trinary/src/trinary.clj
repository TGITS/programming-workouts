(ns trinary)

(defn to-decimal [trinary-as-string]
  (if (re-matches #"(0|1|2)+" trinary-as-string)
    (->>
     (seq trinary-as-string)
     (map #(Character/digit %1 3))
     (reverse)
     (map vector (range 0 (count trinary-as-string)))
     (map #(* (nth %1 1) (Math/pow 3 (nth %1 0))))
     (reduce +)
     (int))
    0))
