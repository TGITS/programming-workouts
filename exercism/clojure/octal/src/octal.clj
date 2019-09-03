(ns octal)

(defn to-decimal [octal-as-string]
  (if (re-matches #"[0-7]+" octal-as-string)
    (->>
     (seq octal-as-string)
     (map #(Character/digit %1 8))
     (reverse)
     (map vector (range 0 (count octal-as-string)))
     (map #(* (nth %1 1) (Math/pow 8 (nth %1 0))))
     (reduce +)
     (int))
    0))
