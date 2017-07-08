(ns coders-strike-back.core
  (:gen-class))

;(defn rand-boolean [] (= (rem (rand-int 10) 2) 0))

;; Random boost using
;(defn rand-boost [boost-used?] (if (not @boost-used?) (if (rand-boolean) (do (reset! boost-used? true) "BOOST") "100") "100"))

;; Distance entre 2 points ou norme du vecteur Norme = ||(x, y) || = sqrt(x*x+y*y)
(defn distance [x y] (Math/sqrt (+ (* x x) (* y y))))
;; Conversion degrés en radians :
;; rad  = degrés * M_PI / 180
(defn convert-degree-to-radian [degree]
  (* degree (/ Math/PI 180) )
  )

(defn calculate-thrust-from-angle [angle] (str (Integer/valueOf (Math/round (* 100 (Math/abs (Math/cos (convert-degree-to-radian angle))))))))

;; Ajouter le boost quand l’angle est bon et la distance assez grande
;; Si il reste un boost
;; Si l’angle est < 10
;; Si la distance à la cible est > 4000
;;Alors « BOOST »
;;(defn use-boost [next-checkpoint-angle next-checkpoint-x next-checkpoint-y boost-used?]
;;      (if (and (not @boost-used?) (or (< next-checkpoint-angle 10) (> next-checkpoint-angle -10)) (> (distance next-checkpoint-x next-checkpoint-y) 4000))
;;          (do (reset! boost-used? true)
;;              "BOOST"
;;              (calculate-thrust-from-angle next-checkpoint-angle)) (calculate-thrust-from-angle next-checkpoint-angle)))

;;(defn compute-action [next-checkpoint-angle next-checkpoint-x next-checkpoint-y boost-used?]
;;      (cond
;;        (>= (Math/abs next-checkpoint-angle) 90) "0"
;;        (<= 90 (Math/abs next-checkpoint-angle)  60) "25"
;;        (<= 60 (Math/abs next-checkpoint-angle)  45) "50"
;;        (<= 45 (Math/abs next-checkpoint-angle)  30) "75"
;;         true (use-boost next-checkpoint-angle next-checkpoint-x next-checkpoint-y boost-used?)))

(def boost-used? (atom false))

(defn compute-action [next-checkpoint-angle next-checkpoint-x next-checkpoint-y boost-used?]
      (binding [*out* *err*]
          (println (str
                     "distance next-checkpoint-x next-checkpoint-y : " (str (distance next-checkpoint-x next-checkpoint-y))
                     " ; pod angle with checkpoint : " (str next-checkpoint-angle))))
      (binding [*out* *err*]
                    (println (str "Boost used ? (in compute-action) : " @boost-used? )))
      (if (and (not @boost-used?) (< (Math/abs next-checkpoint-angle) 10) (> (distance next-checkpoint-x next-checkpoint-y) 4000))
          (do (reset! boost-used? true)
              "BOOST"
              (calculate-thrust-from-angle next-checkpoint-angle)) (calculate-thrust-from-angle next-checkpoint-angle)))

(defn -main [& args]
      (while true
             (let [x (read) y (read) nextCheckpointX (read) nextCheckpointY (read) nextCheckpointDist (read) nextCheckpointAngle (read) opponentX (read) opponentY (read)]
                  ; nextCheckpointX: x position of the next check point
                  ; nextCheckpointY: y position of the next check point
                  ; nextCheckpointDist: distance to the next checkpoint
                  ; nextCheckpointAngle: angle between your pod orientation and the direction of the next checkpoint

                  ; (binding [*out* *err*]
                  ;   (println "Debug messages..."))
                  (binding [*out* *err*]
                    (println (str "Boost used ?" @boost-used? )))
                  ; You have to output the target position
                  ; followed by the power (0 <= thrust <= 100)
                  ; i.e.: "x y thrust"
                  (print nextCheckpointX)
                  (print " ")
                  (print nextCheckpointY)
                  (print " ")
                  (print (compute-action (Integer/valueOf nextCheckpointAngle) (Integer/valueOf nextCheckpointX) (Integer/valueOf nextCheckpointY) boost-used?))
                  (println ""))))
