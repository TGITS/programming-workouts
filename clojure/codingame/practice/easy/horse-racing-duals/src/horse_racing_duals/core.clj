(ns horse-racing-duals.core
  (:gen-class))

(defn -main [& args]
  (let [N (read)
        powers (repeatedly N read)
        sorted-powers (sort powers)
        sorted-powers-by-pairs (partition 2 1 sorted-powers)
        result (str (apply min (map #(- (second %) (first %)) sorted-powers-by-pairs)))]
    (println result)))
