(ns armstrong-numbers
  (:require [clojure.math.numeric-tower :as math]))

(defn count-number-of-digits [num]
  (count (str num)))

(defn number-to-digits-list [num] 
  (map #(Integer/parseInt (str %)) (seq (str num))))

(defn exponentiate-all-digits [exponent num]
  (apply + (map #(math/expt % exponent) (number-to-digits-list num))))

(defn armstrong? [num] ;; <- arglist goes here
  (= num (exponentiate-all-digits (count-number-of-digits num) num)))