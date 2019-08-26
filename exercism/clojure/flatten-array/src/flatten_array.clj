(ns flatten-array)

(declare flatten)

(defn manage-element [element result-array]
  (cond 
    (nil? element) result-array
    (atom? element) (cons element result-array)
    (seq? element) (concat (flatten element) result-array)
    )
  )

(defn flatten [arr]
  (loop [process-array arr result-array []]
    (if (nil? process-array)
      (vec result-array)
      (recur (rest process-array) (manage-element (first process-array) result-array)))))
