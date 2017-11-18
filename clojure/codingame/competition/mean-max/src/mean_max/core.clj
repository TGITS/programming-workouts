(ns mean-max.core
  (:gen-class))

;;(ns Player
;;  (:gen-class)

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(require '[clojure.string :as string])

;;unitType 0 (Reaper), 1 (Destroyer), 3 (Tanker), 4 (Wreck)
(def unit-types [:reaper :destroyer :placeholder :tanker :wreck])

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

(defn compute-reaper-action [my-reaper next-wreck]
  (cond
    (nil? next-wreck) "WAIT"
    (in my-reaper next-wreck) "WAIT"
    true (str (:x next-wreck) " " (:y next-wreck) " 200")))

(defn compute-destroyer-action [my-destroyer next-tanker]
  (cond
    (nil? next-tanker) "WAIT"
    (in my-destroyer next-tanker) (str (:x next-tanker) " " (:y next-tanker) " 0")
    true (str (:x next-tanker) " " (:y next-tanker) " 300")))

(defn -main [& args]
  (while true
    (let [my-score (read) enemy-score1 (read) enemy-score2 (read) my-rage (read) enemy-rage1 (read) enemy-rage2 (read) unit-count (read) units (atom [])]
      (loop [i unit-count]
        (when (> i 0)
          (let [unit-id (read)
                unit-type (nth unit-types (read))
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
      (let [wrecks (filter #(= (:unit-type %) :wreck) @units)
            wrecks-with-water (filter #(> (:extra %) 0) wrecks)
            tankers (filter #(= (:unit-type %) :tanker) @units)
            reapers (filter #(= (:unit-type %) :reaper) @units)
            destroyers (filter #(= (:unit-type %) :destroyer) @units)
            enemy-reapers (filter #(not= (:player-id %) 0) reapers)
            enemy-destroyers (filter #(not= (:player-id %) 0) destroyers)
            my-reaper (first (filter #(= (:player-id %) 0) reapers))
            my-destroyer (first (filter #(= (:player-id %) 0) destroyers))
            next-wreck (first (sort-by #(distance (:x my-reaper) (:y my-reaper) (:x %) (:y %)) wrecks-with-water))
            next-tanker (first (sort-by #(distance (:x my-destroyer) (:y my-destroyer) (:x %) (:y %)) tankers))]
        (binding [*out* *err*] (println "wrecks : " (string/join " " (map #(into {} %) wrecks)))
                               (println "wrecks with water: " (string/join " " (map #(into {} %) wrecks-with-water)))
                               (println "tankers : " (string/join " " (map #(into {} %) tankers)))
                               (println "reapers : " (string/join " " (map #(into {} %) reapers)))
                               (println "destroyers : " (string/join " " (map #(into {} %) destroyers)))
                               (println "enemy reapers: " (string/join " " (map #(into {} %) enemy-reapers)))
                               (println "enemy destroyers: " (string/join " " (map #(into {} %) enemy-destroyers)))
                               (println "my reaper : " (string/join " " my-reaper))
                               (println "my-destroyer : " (string/join " " my-destroyer))
                               (println "next-wreck : " (string/join " " next-wreck))
                               (println "next-tanker : " (string/join " " next-tanker)))
        (println (compute-reaper-action my-reaper next-wreck))
        (println (compute-destroyer-action my-destroyer next-tanker))
        (println "WAIT")))))

      ; (binding [*out* *err*]
      ;   (println "Debug messages..."))

      ; Write action to stdout
      ;(println "WAIT")
      ;(println "WAIT")
      ;(println "WAIT"))))
