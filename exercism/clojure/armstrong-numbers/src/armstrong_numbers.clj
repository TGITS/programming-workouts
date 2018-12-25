(ns armstrong-numbers
  (:require [clojure.math.numeric-tower :as math]))

(defn count-number-of-digits [num]
  (count (str num)))

(defn number-to-digits-list [num] 
  (map #(Integer/parseInt %) (map str (seq (str num)))))

(defn my-expt [exponent num]
  (math/expt num exponent))

(defn exponentiate-all-digits [exponent num]
  (reduce + (map (partial my-expt exponent) (number-to-digits-list num))))

(defn armstrong? [num] ;; <- arglist goes here
  (= num (exponentiate-all-digits (count-number-of-digits num) num)))