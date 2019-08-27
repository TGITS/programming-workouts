(ns binary)

(defn to-decimal [binary-as-string]
  (if (re-matches #"(0|1)+" binary-as-string)
    (->>
     (seq binary-as-string)
     (map #(Character/digit %1 2))
     (reverse)
     (map vector (range 0 (count binary-as-string)))
     (map #(* (nth %1 1) (Math/pow 2 (nth %1 0))))
     (reduce +)
     (int)
     )
    0))
