(ns grains)

(defn square [case-number] 
    (bigint (Math/pow 2 (dec case-number)))
)

(defn total []  
    (reduce + (map square (range 1 65)))
)
