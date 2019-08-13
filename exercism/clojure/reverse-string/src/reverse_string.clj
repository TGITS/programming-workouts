(ns reverse-string)
(require '[clojure.string :as str])

(defn- reverse-string-as-sequence [sequence reverse-string]
  (loop [seq sequence rs reverse-string]
    (if (empty? seq)
      rs
      (recur (rest seq) (str (first seq) rs)))
    )
  )

(defn reverse-string [s] 
  (reverse-string-as-sequence (str/split s #"") "")
)
