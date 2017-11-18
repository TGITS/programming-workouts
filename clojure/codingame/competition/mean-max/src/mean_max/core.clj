(ns mean-max.core
  (:gen-class))

;;(ns Player
;;  (:gen-class)

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(require '[clojure.string :as string])

(defrecord Unit
  [unit-id unit-type player-id mass radius x y vx vy extra extra2])

;; Conversion d'un double en entier avec arrondi
(defn convert-to-int [float-value]
  (Integer/valueOf (Math/round float-value)))

;; norme du vecteur Norme = ||(x, y) || = sqrt(x*x+y*y)
(defn norm [x y] (Math/sqrt (+ (* x x) (* y y))))

;;distance between 2 points
(defn distance [x1 y1 x2 y2] (convert-to-int (Math/sqrt (+ (Math/pow (- x2 x1) 2) (Math/pow (- y2 y1) 2)))))

(defn in [my-unit other-unit]
  "Test whether 'my-unit' is 'in' the 'other-unit'"
  (< (distance (:x my-unit) (:y my-unit) (:x other-unit) (:y other-unit)) (:radius other-unit)))

(defn compute-action [my-unit next-wreck]
  (cond
    (in my-unit next-wreck) "WAIT"
    true (str (:x next-wreck) " " (:y next-wreck) " 150")))

(defn -main [& args]
  (while true
    (let [my-score (read) enemy-score1 (read) enemy-score2 (read) my-rage (read) enemy-rage1 (read) enemy-rage2 (read) unit-count (read) units (atom [])]
      (loop [i unit-count]
        (when (> i 0)
          (let [unit-id (read)
                unit-type (read)
                player-id (read)
                mass (read)
                radius (read)
                x (read)
                y (read)
                vx (read)
                vy (read)
                extra (read)
                extra2 (read)
                unit (Unit. unit-id unit-type player-id mass radius x y vx vy extra extra2)]
            (swap! units conj unit)
            (recur (dec i)))))
      (let [wrecks (filter #(= (:unit-type %) 4) @units)
            wrecks-with-water (filter #(> (:extra %) 0) wrecks)
            mobile-units (filter #(= (:unit-type %) 0) @units)
            enemy-mobile-units (filter #(not= (:player-id %) 0) mobile-units)
            my-unit (first (filter #(= (:player-id %) 0) mobile-units))
            next-wreck (first (sort-by #(distance (:x my-unit) (:y my-unit) (:x %) (:y %)) wrecks-with-water))]
        (binding [*out* *err*] (println "wrecks : " (string/join " " (map #(into {} %) wrecks)))
                               (println "mobile-units : " (string/join " " (map #(into {} %) mobile-units)))
                               (println "enemy-mobile-units : " (string/join " " (map #(into {} %) enemy-mobile-units)))
                               (println "my-unit : " (string/join " " my-unit))
                               (println "next-wreck : " (string/join " " next-wreck)))
        (println (compute-action my-unit next-wreck))
        (println "WAIT")
        (println "WAIT")))))

      ; (binding [*out* *err*]
      ;   (println "Debug messages..."))

      ; Write action to stdout
      ;(println "WAIT")
      ;(println "WAIT")
      ;(println "WAIT"))))
