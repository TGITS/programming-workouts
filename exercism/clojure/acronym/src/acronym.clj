(ns acronym
  (:require [clojure.string :as str]))

(defn acronym [phrase]
  (if (str/blank? phrase)
    ""
    (->>
     (str/split phrase #"\s|\-")
     (map #(str (str/upper-case (subs %1 0 1)) (subs %1 1)))
     (map #(str/replace %1 #",|:" ""))
     (map #(str/split %1 #"[a-z]+"))
     (flatten)
     (map #(subs %1 0 1))
     (str/join))))
