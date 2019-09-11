(ns phone-number
  (:require [clojure.string :as string]))

(defn number [num-string]
  (let [clean-string (string/replace num-string #"\(|\)|\.|\-|\s+" "")]
    (cond
      (= 10 (count clean-string)) clean-string
      (and (= 11 (count clean-string)) (string/starts-with? clean-string "1")) (subs clean-string 1)
      :else "0000000000")))

(defn area-code [num-string]
  (string/join (take 3 (seq (phone-number/number num-string)))))

(defn pretty-print [num-string]
  (let [clean-string (seq (phone-number/number num-string))]
    (str "(" (string/join (take 3 clean-string)) ") " (string/join (take 3 (drop 3 clean-string))) "-" (string/join (drop 6 clean-string)))))
