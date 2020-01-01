(ns luhn
  (:require [clojure.string :as str]))

(defn clean
  "Remove all spaces from the string arguments 'id'"
  [id]
  (str/replace id #"\s+" ""))

(defn calculate-luhn-sum
  "Caculate the sum of all the digit of the given argument 'id' following the Luhn formula"
  [id]
  (->> id
       (map #(Character/getNumericValue %1))
       (reverse)
       (map-indexed vector)
       (map #(if (not= 0 (rem (first %1) 2)) (let [product (* 2 (second %1))] (if (> product 9) (- product 9) product)) (second %1)))
       (reduce + )))

(defn check 
  "Check whether the well-formed argument 'id' (composed only of numbers) respect the Luhn Formula"
  [id]
  (= 0 (rem (calculate-luhn-sum id) 10)))

(defn valid? 
  "Check whether the given argument 'id' is valid per the Luhn formula"
  [id]
  (let [id (clean id)]
    (cond
      (or (= 1 (count id)) (nil? (re-matches #"^\d+$" id))) false
      :else (check id))))
