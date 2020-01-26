(require '[clojure.string])

(defn output [msg] (println msg) (flush))

(let [n (long 71339)
      n-power-2 (long (Math/pow n 2))
      n-x-and-y (for [s (range 1 (inc n))
                      :when (= 0 (rem n-power-2 s))]
                  (format "1/%d = 1/%d + 1/%d" n (+ n (quot n-power-2 s)) (+ n s)))]
  (->> n-x-and-y
       (clojure.string/join "\n")
       (output)))