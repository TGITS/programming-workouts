(ns coders-strike-back.core
  (:gen-class))

(defn inc-atom [curr-val] (inc curr-val))
(defn dec-atom [curr-val] (dec curr-val))

;; norme du vecteur Norme = ||(x, y) || = sqrt(x*x+y*y)
(defn norm [x y] (Math/sqrt (+ (* x x) (* y y))))
;;distance between 2 points
(defn distance [x1 y1 x2 y2] (Math/sqrt (+ (Math/pow (- x2 x1) 2) (Math/pow (- y2 y1) 2))))
;; Conversion degrés en radians :
;; rad  = degrés * M_PI / 180
(defn convert-degree-to-radian [degree]
  (* degree (/ Math/PI 180)))

;;Compute boost value between 0 and 100
(defn compute-boost [next-checkpoint-distance next-checkpoint-angle]
  (cond
    (> (Math/abs next-checkpoint-angle) 90) "0"
    (< (Math/abs next-checkpoint-angle) 30) "100"
    (and (< next-checkpoint-distance 1000) (> (Math/abs next-checkpoint-angle) 30)) "0"
    (and (< next-checkpoint-distance 1000) (< (Math/abs next-checkpoint-angle) 30)) "50"
    (> next-checkpoint-distance 4000) "100"
    true (str (Integer/valueOf (Math/round (* 100 (Math/abs (Math/cos (convert-degree-to-radian next-checkpoint-angle)))))))))

(defn compute-x [next-checkpoint-x] next-checkpoint-x)

(defn compute-y [next-checkpoint-y] next-checkpoint-y)

(def boost-used? (atom false))
(def turn-counter (atom 0))

;;Compute BOOST, SHIELD or boost value between 0 and 100
(defn compute-action [next-checkpoint-x next-checkpoint-y next-checkpoint-distance next-checkpoint-angle opponent-x opponent-y boost-used? turn-counter]
      (if (and (not @boost-used?) (> @turn-counter 3) (< (Math/abs next-checkpoint-angle) 10) (> next-checkpoint-distance 4000))
          (do (reset! boost-used? true)
              "BOOST")
          (compute-boost next-checkpoint-distance next-checkpoint-angle)))

(defn -main [& args]
      (while true
             (let [x (read) y (read) nextCheckpointX (read) nextCheckpointY (read) nextCheckpointDist (read) nextCheckpointAngle (read) opponentX (read) opponentY (read)]
                  ; nextCheckpointX: x position of the next check point
                  ; nextCheckpointY: y position of the next check point
                  ; nextCheckpointDist: distance to the next checkpoint
                  ; nextCheckpointAngle: angle between your pod orientation and the direction of the next checkpoint

                  (swap! turn-counter inc-atom)

                  ; (binding [*out* *err*]
                  ;   (println "Debug messages..."))
                  (binding [*out* *err*]
                    (println (str "Has the boost be used ? : " @boost-used? ))
                    (println (str "Given next check point distance : " nextCheckpointDist))
                    (println (str "Calculated next check point distance : " (distance x y opponentX opponentY)))
                    (println (str "Pod angle with checkpoint : " nextCheckpointAngle))
                    (println (str "Turn : " @turn-counter)))

                  ; You have to output the target position
                  ; followed by the power (0 <= thrust <= 100)
                  ; i.e.: "x y thrust"
                  (print (compute-x nextCheckpointX))
                  (print " ")
                  (print (compute-y nextCheckpointY))
                  (print " ")
                  (print (compute-action
                           (Integer/valueOf nextCheckpointX) (Integer/valueOf nextCheckpointY)
                           (Integer/valueOf nextCheckpointDist) (Integer/valueOf nextCheckpointAngle)
                           (Integer/valueOf opponentX) (Integer/valueOf opponentY)
                           boost-used? turn-counter))
                  (println ""))))
