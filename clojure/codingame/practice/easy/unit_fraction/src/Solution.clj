(ns Solution
  (:require [clojure.string])
  (:gen-class))

;; For the idea of algorithm, I borrow much from this blog post : https://www.mathblog.dk/project-euler-108-diophantine-equation/

(defn output [msg] (println msg) (flush))
(defn debug [msg] (binding [*out* *err*] (println msg) (flush)))

(defn -main [& _]
  (let [n (long (read))
        n-power-2 (long (Math/pow n 2))
        n-x-and-y (for [s (range 1 (inc n))
                        :when (= 0 (rem n-power-2 s))]
                    (format "1/%d = 1/%d + 1/%d" n (+ n (quot n-power-2 s)) (+ n s)))]
    (->> n-x-and-y
         (clojure.string/join "\n")
         (output))))
