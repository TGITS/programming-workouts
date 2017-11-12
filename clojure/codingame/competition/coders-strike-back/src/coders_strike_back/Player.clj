(ns Player
  (:gen-class))

(def checkpoint-core-size 200)
(def delay-between-shield-use 10)
(def min-distance-to-activate-shield 950)

(defn inc-atom [curr-val] (inc curr-val))
(defn dec-atom [curr-val] (dec curr-val))

(defn sgn [val]
  (cond
    (< val 0) -1
    (> val 0) 1
    (= val 0) 0))

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

;; norme du vecteur Norme = ||(x, y) || = sqrt(x*x+y*y)
(defn norm [x y] (Math/sqrt (+ (* x x) (* y y))))
;;distance between 2 points
(defn distance [x1 y1 x2 y2] (convert-to-int (Math/sqrt (+ (Math/pow (- x2 x1) 2) (Math/pow (- y2 y1) 2)))))

;;Angle entre 2 points (x1,y1) et (x2,y2)
(defn angle-between-2-points [x1 y1 x2 y2]
  (let [d (distance x1 y1 x2 y2) dx (/ (- x2 x1) d) dy (/ (- y2 y1) d) angle (convert-radian-to-degree (Math/acos dx))]
    (if (< dy 0) (- 360.0 angle) angle)))

(defn adjustment-from-angle [next-checkpoint-angle]
  (* checkpoint-core-size (convert-to-int (Math/signum (Math/cos next-checkpoint-angle)))))


(defn compute-x-y [x y next-checkpoint-x next-checkpoint-y next-checkpoint-angle next-checkpoint-distance]
  (cond
    (and (< x next-checkpoint-x) (< y next-checkpoint-y)) (str (- next-checkpoint-x checkpoint-core-size) " " (- next-checkpoint-y checkpoint-core-size))
    (and (> x next-checkpoint-x) (< y next-checkpoint-y)) (str (+ next-checkpoint-x checkpoint-core-size) " " (- next-checkpoint-y checkpoint-core-size))
    (and (> x next-checkpoint-x) (> y next-checkpoint-y)) (str (+ next-checkpoint-x checkpoint-core-size) " " (+ next-checkpoint-y checkpoint-core-size))
    (and (< x next-checkpoint-x) (> y next-checkpoint-y)) (str (- next-checkpoint-x checkpoint-core-size) " " (+ next-checkpoint-y checkpoint-core-size))
    ;(and (< x next-checkpoint-x) (< y next-checkpoint-y)) (str (- next-checkpoint-x (adjustment-from-angle next-checkpoint-angle)) " " (- next-checkpoint-y (adjustment-from-angle next-checkpoint-angle)))
    ;(and (> x next-checkpoint-x) (< y next-checkpoint-y)) (str (+ next-checkpoint-x (adjustment-from-angle next-checkpoint-angle)) " " (- next-checkpoint-y (adjustment-from-angle next-checkpoint-angle)))
    ;(and (> x next-checkpoint-x) (> y next-checkpoint-y)) (str (+ next-checkpoint-x (adjustment-from-angle next-checkpoint-angle)) " " (+ next-checkpoint-y (adjustment-from-angle next-checkpoint-angle)))
    ;(and (< x next-checkpoint-x) (> y next-checkpoint-y)) (str (- next-checkpoint-x (adjustment-from-angle next-checkpoint-angle)) " " (+ next-checkpoint-y (adjustment-from-angle next-checkpoint-angle)))
    true (str next-checkpoint-x " " next-checkpoint-y)))

(defn compute-boost-from-angle [next-checkpoint-angle]
  (str (convert-to-int (* (Math/sin (convert-degree-to-radian (Math/abs next-checkpoint-angle))) 100))))

;;Compute a boost value between 0 and 100
(defn compute-boost [next-checkpoint-distance next-checkpoint-angle]
  (cond
    (<= next-checkpoint-distance 1000) "40"
    (<= (Math/abs next-checkpoint-angle) 18) "100"
    true (compute-boost-from-angle (Math/abs next-checkpoint-angle))))

(defn use-boost? [next-checkpoint-distance next-checkpoint-angle boost-used? game-loop-counter last-shield-usage last-boost-value]
  (and
    (not @boost-used?)
    (< (Math/abs next-checkpoint-angle) 18)
    (> next-checkpoint-distance 4000)
    (and (not (= @last-boost-value "SHIELD")) (not (= @last-boost-value "BOOST")) (= @last-shield-usage 0))))

(defn use-shield? [x y opponent-x opponent-y boost-used? game-loop-counter max-shield-usage last-shield-usage last-boost-value]
  (and (= @last-shield-usage 0) (> @game-loop-counter 10) (> @max-shield-usage 0) (< (distance opponent-x opponent-y x y) min-distance-to-activate-shield)))

;;Compute BOOST, SHIELD or boost value between 0 and 100
(defn compute-action [next-checkpoint-x next-checkpoint-y next-checkpoint-distance next-checkpoint-angle x y opponent-x opponent-y boost-used? game-loop-counter max-shield-usage last-shield-usage last-boost-value]
  (cond
    (use-boost? next-checkpoint-distance next-checkpoint-angle boost-used? game-loop-counter last-shield-usage last-boost-value) (do (reset! boost-used? true) "BOOST")
    (use-shield? x y opponent-x opponent-y boost-used? game-loop-counter max-shield-usage last-shield-usage last-boost-value) (do (reset! last-shield-usage delay-between-shield-use) (swap! max-shield-usage dec-atom) "SHIELD")
    true (compute-boost next-checkpoint-distance next-checkpoint-angle)))

(defn -main [& args]
  (let [max-shield-usage (atom 5)
        last-shield-usage (atom 0)
        boost-used? (atom false)
        game-loop-counter (atom 0)
        last-boost-value (atom "0")]
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
        (if (> @last-shield-usage 0)
          (swap! last-shield-usage dec-atom))

        ; (binding [*out* *err*]
        ;   (println "Debug messages..."))
        ;(binding [*out* *err*]
        ;  (println (str "Has the boost been used ? : " @boost-used?))
        ;  (println (str "Next check point distance coordinates : (" nextCheckpointX "," nextCheckpointY ")"))
        ;  (println (str "Given next check point distance : " nextCheckpointDist))
        ;  (println (str "Calculated next check point distance : " (distance x y nextCheckpointX nextCheckpointY)))
        ;  (println (str "Pod angle with checkpoint : " nextCheckpointAngle))
        ;  ;(println (str "Calculated Pod angle with checkpoint : " (angle-between-2-points x y nextCheckpointX nextCheckpointY)))
        ;  (println (str "Pod Position : (" x "," y ")"))
        ;  (println (str "Opponent pod Position : (" opponentX "," opponentY ")"))
        ;  (println (str "Opponent distance : " opponent-distance))
        ;  ;(println (str "Calculated Pod angle with opponent : " (angle-between-2-points x y opponentX opponentY)))
        ;  (println (str "Max Shield Usage : " @max-shield-usage))
        ;  (println (str "Last Shield Usage : " @last-shield-usage))
        ;  (println (str "Game loop counter : " @game-loop-counter))
        ;  (println (str "Last boost value : " @last-boost-value)))



        ; You have to output the target position
        ; followed by the power (0 <= thrust <= 100)
        ; i.e.: "x y thrust"
        ;(print (compute-x x nextCheckpointX nextCheckpointAngle nextCheckpointDist))
        ;(print " ")
        ;(print (compute-y y nextCheckpointY nextCheckpointAngle nextCheckpointDist))
        (print (compute-x-y x y nextCheckpointX nextCheckpointY nextCheckpointAngle nextCheckpointDist))
        (print " ")
        (reset! last-boost-value
                (compute-action
                  nextCheckpointX nextCheckpointY
                  nextCheckpointDist nextCheckpointAngle
                  x y
                  opponentX opponentY
                  boost-used?
                  game-loop-counter
                  max-shield-usage
                  last-shield-usage
                  last-boost-value))
        (print @last-boost-value))
      (println ""))))