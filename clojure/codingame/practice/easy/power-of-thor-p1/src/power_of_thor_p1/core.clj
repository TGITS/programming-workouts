(ns power-of-thor-p1.core
    (:gen-class))

(defn inc-atom [curr-val] (inc curr-val))
(defn dec-atom [curr-val] (dec curr-val))

(defn -main [& args]
      (let [lightX (read) lightY (read) initialTX (read) initialTY (read)]
           ; lightX: the X position of the light of power
           ; lightY: the Y position of the light of power
           ; initialTX: Thor's starting X position
           ; initialTY: Thor's starting Y position
           (def dx (atom (- initialTX lightX)))
           (def dy (atom (- initialTY lightY)))
           (def horizontal-move (atom ""))
           (def vertical-move (atom ""))

           (while true
                  (let [remainingTurns (read)]
                       ; remainingTurns: The remaining amount of turns Thor can move. Do not remove this line.
                       (cond
                         (> @dx 0) (do (reset! horizontal-move "W") (swap! dx inc-atom))
                         (< @dx 0) (do (reset! horizontal-move "E") (swap! dx dec-atom))
                         (= @dx 0) (reset! horizontal-move ""))

                       (cond
                         (> @dy 0) (do (reset! vertical-move "N") (swap! dy dec-atom))
                         (< @dy 0) (do (reset! vertical-move "S") (swap! dy inc-atom))
                         (= @dy 0) (reset! vertical-move ""))

                       ; (binding [*out* *err*]
                       ;   (println "Debug messages..."))

                       ; A single line providing the move to be made: N NE E SE S SW W or NW
                       (println (str @vertical-move @horizontal-move))))))
