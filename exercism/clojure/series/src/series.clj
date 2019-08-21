(ns series)

(defn slices [string length] 
  (if (= 0 length) 
    [""]
    (loop [char-seq (seq string) slices []]
      (if (> length (count char-seq)) 
        slices
        (recur (rest char-seq) (conj slices (apply str (take length char-seq)))))))
  )