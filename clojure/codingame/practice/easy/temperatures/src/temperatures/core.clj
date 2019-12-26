
(ns temperatures.core
  (:gen-class))

(require '[clojure.string :as string])

(defn by-first-asc-and-second-desc [x y]
  (compare [(first x) (second y)]
           [(first y) (second x)]))

(defn -main [& args]
  (let [n (read) _ (read-line) temps (read-line)]
    ; n: the number of temperatures to analyse
    ; temps: the n temperatures expressed as integers ranging from -273 to 5526
    (if (string/blank? temps) (println "0")
        (let [temp-list (sort (map #(Integer/parseInt %) (clojure.string/split temps #" ")))
              abs-temp-list (map #(Math/abs %) temp-list)
              sorted-abs-temp-list (sort abs-temp-list)
              temp-tuples-list (sort by-first-asc-and-second-desc (zipmap abs-temp-list temp-list))]

          (binding [*out* *err*]
            (println "n : " (str n)))

          (binding [*out* *err*]
            (println "temperatures : " temps))

          (binding [*out* *err*]
            (println "abs-temp-list : " (string/join " " abs-temp-list)))

          (binding [*out* *err*]
            (println "temp-list : " (string/join " " temp-list)))

          (binding [*out* *err*]
            (println "temp-tuples-list : " (string/join " " temp-tuples-list)))

          (binding [*out* *err*]
            (println (str (second (first temp-tuples-list)))))

          (println (str (second (first temp-tuples-list))))))))

