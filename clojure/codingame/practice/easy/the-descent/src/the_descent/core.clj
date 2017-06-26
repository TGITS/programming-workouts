(ns Player
  (:gen-class))

; The while loop represents the game.
; Each iteration represents a turn of the game
; where you are given inputs (the heights of the mountains)
; and where you have to print an output (the index of the mountain to fire on)
; The inputs you are given are automatically updated according to your last actions.

(defn -main [& args]
  (while true
    (def mountain-heights [])
      (loop [i 0]
        (when (< i 8)
            (let [mountainH (read)]
                  (def mountain-heights (conj mountain-heights mountainH))
            ; mountainH: represents the height of one mountain.
            (recur (inc i)))))
    ; (binding [*out* *err*]
    ;   (println "Debug messages..."))
    (binding [*out* *err*]
      (println (str mountain-heights)))
    (def max-height (apply max mountain-heights))
    (binding [*out* *err*]
      (println (str max-height)))
    (def max-height-index (.indexOf mountain-heights max-height))
    (binding [*out* *err*]
      (println (str max-height-index)))
  
    ; The index of the mountain to fire on.
    (println (str max-height-index))))