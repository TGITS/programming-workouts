(ns acronym
  (:require [clojure.string :as str]))

(defn acronym [phrase]
  (if (str/blank? phrase)
    ""
    (str/join
     (map #(subs %1 0 1)
          (flatten
           (map #(str/split %1 #"[a-z]+")
                (map #(str/replace %1 #",|:" "")
                     (map  #(str (str/upper-case (subs %1 0 1)) (subs %1 1))
                           (str/split phrase #"\s|\-")))))))))
