(ns Player
  (:gen-class))

(def checkpoint-core-size 100)

(defn inc-atom [curr-val] (inc curr-val))
(defn dec-atom [curr-val] (dec curr-val))

;; norme du vecteur Norme = ||(x, y) || = sqrt(x*x+y*y)
(defn norm [x y] (Math/sqrt (+ (* x x) (* y y))))
;;distance between 2 points
(defn distance [x1 y1 x2 y2] (Integer/valueOf (Math/round (Math/sqrt (+ (Math/pow (- x2 x1) 2) (Math/pow (- y2 y1) 2))))))
;; Conversion degrés en radians :
;; rad  = degrés * M_PI / 180
(defn convert-degree-to-radian [degree]
  (* degree (/ Math/PI 180)))

;; Conversion degrés en radians :
;; rad  = degrés * M_PI / 180
(defn convert-radian-to-degree [radian]
  (* radian (/ 180 Math/PI)))

;; Conversion d'un double en entier avec arrondi
(defn convert-to-int [float-value] 
 (Integer/valueOf (Math/round float-value)))

;;Angle entre 2 points (x1,y1) et (x2,y2)
(defn angle-between-2-points [x1 y1 x2 y2] 
  (let [ d (distance x1 y1 x2 y2) dx (/ (- x2 x1) d) dy (/ (- y2 y1) d) angle (convert-radian-to-degree (Math/acos dx))] 
    (if (< dy 0) (- 360.0 angle) angle)))

(defn after [val1 val2] (if (> (- val2 val1) 0) true false))
(defn before [val1 val2] (if (< (- val2 val1) 0) true false))

;;Compute a boost value between 0 and 100
(defn compute-boost [next-checkpoint-distance next-checkpoint-angle]
  (cond
    (> (Math/abs next-checkpoint-angle) 90) "0"
    (< next-checkpoint-distance 600) "0"
    (and (< next-checkpoint-distance 1000) (> (Math/abs next-checkpoint-angle) 30)) "0"
    (< next-checkpoint-distance 1000) "20"
    (> (Math/abs next-checkpoint-angle) 60) "80"
    ; (< (Math/abs next-checkpoint-angle) 30) "100"
    ; (> next-checkpoint-distance 4000) "100"
    true "100"))

(defn compute-x [x next-checkpoint-x next-checkpoint-angle next-checkpoint-distance] 
  (if (> next-checkpoint-distance 1000)
    (cond
      (and (>= next-checkpoint-angle -90) (<= next-checkpoint-angle 90) (before next-checkpoint-x x)) 
        (convert-to-int (+ next-checkpoint-x (* checkpoint-core-size (Math/cos next-checkpoint-angle))))
      (and (>= next-checkpoint-angle -90) (<= next-checkpoint-angle 90) (after next-checkpoint-x x)) 
        (convert-to-int (- next-checkpoint-x (* checkpoint-core-size (Math/cos next-checkpoint-angle))))  
      (or (<= -180 next-checkpoint-angle -90 ) (>= 180 next-checkpoint-angle 90)) 
        (convert-to-int (- next-checkpoint-x (* checkpoint-core-size (Math/cos next-checkpoint-angle))))) 
    next-checkpoint-x))

(defn compute-y [y next-checkpoint-y next-checkpoint-angle next-checkpoint-distance] 
  (if (cond
        (and (>= next-checkpoint-angle 0)  (<= next-checkpoint-angle 180)) (convert-to-int (- next-checkpoint-y (* checkpoint-core-size (Math/sin next-checkpoint-angle))))
        (and (< next-checkpoint-angle 0) (>= next-checkpoint-angle -180)) (convert-to-int (+ next-checkpoint-y (* checkpoint-core-size (Math/sin next-checkpoint-angle))))) 
   next-checkpoint-y))

(def boost-used? (atom false))
(def game-loop-counter (atom 0))

;;Compute BOOST, SHIELD or boost value between 0 and 100
(defn compute-action [next-checkpoint-x next-checkpoint-y next-checkpoint-distance next-checkpoint-angle opponent-x opponent-y boost-used? game-loop-counter]
      (if (and (not @boost-used?) (> @game-loop-counter 3) (< (Math/abs next-checkpoint-angle) 10) (> next-checkpoint-distance 4000))
          (do (reset! boost-used? true)
              "BOOST")
          (compute-boost next-checkpoint-distance next-checkpoint-angle)))

(defn -main [& args]
      (while true
             (let [x (read) y (read)
                   nextCheckpointX (read) nextCheckpointY (read)
                   nextCheckpointDist (read) nextCheckpointAngle (read)
                   opponentX (read) opponentY (read)
                   opponent-distance (distance x y opponentX opponentY)]
                  ; nextCheckpointX: x position of the next check point
                  ; nextCheckpointY: y position of the next check point
                  ; nextCheckpointDist: distance to the next checkpoint
                  ; nextCheckpointAngle: angle between your pod orientation and the direction of the next checkpoint

                  (swap! game-loop-counter inc-atom)

                  ; (binding [*out* *err*]
                  ;   (println "Debug messages..."))
                  ; (binding [*out* *err*]
                  ;   (println (str "Has the boost been used ? : " @boost-used?))
                  ;   (println (str "Next check point distance coordinates : (" nextCheckpointX "," nextCheckpointY ")"))
                  ;   (println (str "Given next check point distance : " nextCheckpointDist))
                  ;   (println (str "Calculated next check point distance : " (distance x y nextCheckpointX nextCheckpointY)))
                  ;   (println (str "Pod angle with checkpoint : " nextCheckpointAngle))
                  ;   (println (str "Calculated Pod angle with checkpoint : " (angle-between-2-points x y nextCheckpointX nextCheckpointY)))
                  ;   (println (str "Pod Position : (" x "," y ")"))
                  ;   (println (str "Opponent pod Position : (" opponentX "," opponentY ")"))
                  ;   (println (str "Opponent distance : " opponent-distance))
                  ;   (println (str "Calculated Pod angle with opponent : " (angle-between-2-points x y opponentX opponentY)))
                  ;   (println (str "Game loop counter : " @game-loop-counter)))
                    

                  ; You have to output the target position
                  ; followed by the power (0 <= thrust <= 100)
                  ; i.e.: "x y thrust"
                  (print (compute-x x nextCheckpointX nextCheckpointAngle nextCheckpointDist))
                  (print " ")
                  (print (compute-y y nextCheckpointY nextCheckpointAngle nextCheckpointDist))
                  (print " ")
                  (print (compute-action
                           nextCheckpointX nextCheckpointY
                           nextCheckpointDist nextCheckpointAngle
                           opponentX opponentY
                           boost-used? game-loop-counter)))
        (println "")))
