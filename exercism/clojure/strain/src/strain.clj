(ns strain)

(defn retain [pred v] 
  (reduce #(if (pred %2) (conj %1 %2) %1) [] v)
  )

(defn discard [pred v] 
  (reduce #(if (pred %2) %1 (conj %1 %2)) [] v)
)
