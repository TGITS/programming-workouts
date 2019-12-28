(ns isogram
  (:require [clojure.string :as str]))

(defn isogram? [s]
  (every? #(<= % 1) (vals (frequencies (str/lower-case (str/replace s #"[\s\-]+" ""))))))