(ns bob)

(defn response-for [s] 
  (cond
    (or (= 0 (count s)) (re-matches #"\s+" s)) "Fine. Be that way!"
    (re-matches #"([A-Z]|\s)+\?" s) "Calm down, I know what I'm doing!"
    (re-matches #"([1-9,]|\s)+" s) "Whatever."
    (re-matches #"([1-9A-Z]|\s|[,%\^\*@#\$\(\)\!])+\!?" s) "Whoa, chill out!"
    (re-matches #"([1-9a-zA-Z,]|\s)+\?" s) "Sure."
    :else "Whatever.")
)
