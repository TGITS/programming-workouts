(ns etl)

(defn keys-list-to-hash [value keys-list]
  (map #(hash-map % value) keys-list))

(defn transform [source] 
  (reduce merge {} (flatten (map #(etl/keys-list-to-hash % (map clojure.string/lower-case (get source %))) (keys source))))
  )