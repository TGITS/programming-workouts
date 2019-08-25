(ns seq-to-map.core
  (:gen-class))

(defn map-to-seq-of-vec [hashtable]
  (map
   (fn [[k v]] [k v]) hashtable))

(defn seq-of-vec-to-map [seq-of-vec]
  (into {} seq-of-vec)
  )

(defn -main
  [& args]
  (println (seq-to-map.core/map-to-seq-of-vec {:a 1 :b 2 :c 3}))
  (println (seq-of-vec-to-map (seq-to-map.core/map-to-seq-of-vec {:a 1 :b 2 :c 3})))
  )


