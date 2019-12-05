(ns grains)

(defn square [case-number]
  (.pow BigInteger/TWO (dec case-number)))

(defn total []
  (reduce + (map square (range 1 65))))
