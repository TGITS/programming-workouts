(ns horse-racing-duals.core
  (:gen-class))

(defn -main [& args]
      (let [N (read) powers (atom (list))]
           (loop [i N]
                 (when (> i 0)
                       (let [Pi (read)]
                            (reset! powers (cons Pi @powers))
                            (recur (dec i)))))

           ; (binding [*out* *err*]
           ;   (println "Debug messages..."))
           (binding [*out* *err*]
                    (println (str "powers : " @powers)))
           ; Write answer to stdout
           (let [sorted-powers (sort @powers)
                 reverse-sorted-powers (reverse sorted-powers)
                 shorten-sorted-powers (rest sorted-powers)
                 shorten-reverse-sorted-powers (rest reverse-sorted-powers)]
                (binding [*out* *err*]
                         (println (str "sorted powers : " sorted-powers)))
                (binding [*out* *err*]
                         (println (str "reverse sorted powers : " reverse-sorted-powers)))
                (binding [*out* *err*]
                         (println (str "shorten sorted powers : " shorten-sorted-powers)))
                (binding [*out* *err*]
                         (println (str "shorten reverse sorted powers : " shorten-reverse-sorted-powers)))
                (println (str (first (sort (map - shorten-sorted-powers (reverse shorten-reverse-sorted-powers)))))))))


