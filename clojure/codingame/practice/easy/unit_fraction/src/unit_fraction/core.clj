(ns unit-fraction.core
  (:gen-class))

(defn output [msg] (println msg) (flush))
(defn debug [msg] (binding [*out* *err*] (println msg) (flush)))

(defn -main [& args]
  (let [n (read)]
    (take 100 (for [x (range n 1000000)
          y (range n 1000000)
          :when (= (/ 1 n) (+ (/ 1 x) (/ 1 y)))]
      [(/ 1 n) (/ 1 x) (/ 1 y)]))
    (output "1/n = 1/x + 1/y")))
