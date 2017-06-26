(require 'clojure.string)

(ns temperatures.core
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn -main [& args]
  (let [n (read) _ (read-line) temps (read-line)]
    ; n: the number of temperatures to analyse
    ; temps: the n temperatures expressed as integers ranging from -273 to 5526
    (if (clojure.string/blank? temps) (println "0")
                  (let [temp-list (sort (map #(Integer/parseInt %) (clojure.string/split temps #" ")))
                        abs-temp-list (map #(Math/abs %) temp-list)
                        sorted-abs-temp-list (sort (map #(Math/abs %) temp-list))
                        temp-tuples-list (sort-by first (map vector abs-temp-list temp-list))
                        temp-map (into (sorted-map) (zipmap abs-temp-list temp-list))]

                    ; (binding [*out* *err*]
                    ;   (println "Debug messages..."))
                    (binding [*out* *err*]
                      (println "n : " (str n)))

                    (binding [*out* *err*]
                      (println "temperatures : " temps))

                    (binding [*out* *err*]
                      (println "abs-temp-list : " (str abs-temp-list)))

                    (binding [*out* *err*]
                      (println "temp-list : " (str temp-list)))

                    (binding [*out* *err*]
                      (println "temp-tuples-list : " (str (seq temp-tuples-list))))

                    (binding [*out* *err*]
                      (println "temp-map : " (str (seq temp-map))))

                    (binding [*out* *err*]
                      (println (str (nth (first temp-tuples-list) 1))))

                    (println (get temp-map (first sorted-abs-temp-list)))))))

