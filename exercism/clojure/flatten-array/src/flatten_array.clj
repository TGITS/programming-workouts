(ns flatten-array
  (:require [clojure.inspector :as inspector]))

; A simple solution that use clojure.core/flatten 
; (defn flatten [arr] 
;   (filter (complement nil?) (clojure.core/flatten arr))
;  )

; A solution that do not use clojure.core/flatten
(declare flatten)

(defn flatten-element [element flatten-array]
  (cond
    (nil? element) flatten-array
    (inspector/atom? element) (conj flatten-array element )
    (empty? element) flatten-array
    (vector? element) (into [] (concat flatten-array (flatten-array/flatten element)))))

(defn flatten [arr]
  (loop [nested-array arr flatten-array []]
    (if (empty? nested-array)
      flatten-array
      (recur (rest nested-array) (flatten-element (first nested-array) flatten-array)))))
