
(ns temperatures.core
  (:gen-class))

(require '[clojure.string :as string])

(defn by-first-asc-and-second-desc [x y]
  (compare [(first x) (second y)]
           [(first y) (second x)]))

(defn -main [& args]
  (let [_ (read) _ (read-line) temps (read-line)]
    (if (string/blank? temps) (println "0")
        (let [temperatures (sort (map #(Integer/parseInt %) (clojure.string/split temps #" ")))
              absolute-temperatures (map #(Math/abs %) temperatures)
              temperatures-by-pair (sort by-first-asc-and-second-desc (zipmap absolute-temperatures temperatures))]
          (println (str (second (first temperatures-by-pair))))))))

