(ns coders-strike-back.core
  (:gen-class))

(defn rand-boolean [] (= (rem (rand-int 10) 2) 0))

(defn use-boost [boost-used?] (if (not @boost-used?) (if (rand-boolean) (do (reset! boost-used? true) "BOOST") "100") "100"))

(defn compute-thrust [next-checkpoint-angle boost-used?]
      (cond
        (or (> next-checkpoint-angle 90) (< next-checkpoint-angle -90)) "0"
         true (use-boost boost-used?)))

(def boost-used? (atom false))

(defn -main [& args]
      (while true
             (let [x (read) y (read) nextCheckpointX (read) nextCheckpointY (read) nextCheckpointDist (read) nextCheckpointAngle (read) opponentX (read) opponentY (read)]
                  ; nextCheckpointX: x position of the next check point
                  ; nextCheckpointY: y position of the next check point
                  ; nextCheckpointDist: distance to the next checkpoint
                  ; nextCheckpointAngle: angle between your pod orientation and the direction of the next checkpoint

                  ; (binding [*out* *err*]
                  ;   (println "Debug messages..."))

                  ; You have to output the target position
                  ; followed by the power (0 <= thrust <= 100)
                  ; i.e.: "x y thrust"
                  (print nextCheckpointX)
                  (print " ")
                  (print nextCheckpointY)
                  (print " ")
                  (print (compute-thrust (Integer/valueOf nextCheckpointAngle) boost-used?))
                  (println ""))))
