(ns mean-max.core
  (:gen-class))

;;(ns Player
;;  (:gen-class)

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.
(require '[clojure.string :as string])

;;unitType 0 (Reaper), 1 (Destroyer), 2 (Doof), 3 (Tanker), 4 (Wreck)
(def unit-types [:reaper :destroyer :doof :tanker :wreck])

(defrecord Unit
  [unit-id unit-type player-id mass radius x y vx vy extra extra2])

(defrecord PlayerData
  [player-id score rage])

;; Conversion d'un double en entier avec arrondi
(defn convert-to-int [float-value]
  (Integer/valueOf (Math/round float-value)))

;; norme du vecteur Norme = ||(x, y) || = sqrt(x*x+y*y)
(defn norm [x y] (Math/sqrt (+ (* x x) (* y y))))

;;distance between 2 points
(defn distance [x1 y1 x2 y2] (convert-to-int (Math/sqrt (+ (Math/pow (- x2 x1) 2) (Math/pow (- y2 y1) 2)))))

(defn in? [my-unit other-unit]
  "Test whether 'my-unit' is 'in' the 'other-unit'"
  (< (distance (:x my-unit) (:y my-unit) (:x other-unit) (:y other-unit)) (:radius other-unit)))

(defn near? [my-unit other-unit]
  "Test whether 'my-unit' is 'near' an 'other-unit'"
  (< (distance (:x my-unit) (:y my-unit) (:x other-unit) (:y other-unit)) (+ (:radius other-unit) (:radius my-unit))))

(defn in-game-area? [unit]
  "Test whether the unit in parameter is in the circular area of 3000 of radius"
  (<= (distance (:x unit) (:y unit) 0 0) 3000))

(defn filter-reapers [units]
  (filter #(= (:unit-type %) :reaper) units))

(defn filter-destroyers [units]
  (filter #(= (:unit-type %) :destroyer) units))

(defn filter-doofs [units]
  (filter #(= (:unit-type %) :doof) units))

(defn filter-tankers [units]
  (filter #(= (:unit-type %) :tanker) units))

(defn filter-wrecks [units]
  (filter #(= (:unit-type %) :wreck) units))

(defn filter-my-units [units]
  (filter #(= (:player-id %) 0) units))

(defn filter-enemy-units [units]
  (filter #(not= (:player-id %) 0) units))

(defmulti compute-action (fn [my-unit & _] (:unit-type my-unit)))

(defmethod compute-action :reaper [my-unit units game-data]
  (let [wrecks (filter-wrecks units)
        wrecks-with-water (filter #(> (:extra %) 0) wrecks)
        wrecks-sorted-by-distance (sort-by #(distance (:x my-unit) (:y my-unit) (:x %) (:y %)) wrecks-with-water)
        wrecks-sorted-by-water (sort-by :extra > wrecks-with-water)
        nearest-wrecks (first wrecks-sorted-by-distance)
        fullest-wrecks (first wrecks-sorted-by-water)
        tankers (filter in-game-area? (filter-tankers units))
        tankers-sorted-by-distance (sort-by #(distance (:x my-unit) (:y my-unit) (:x %) (:y %)) tankers)
        tankers-sorted-by-water (sort-by :extra > tankers)
        nearest-tanker (first tankers-sorted-by-distance)
        fullest-tanker (first tankers-sorted-by-water)]
    (cond
      (and (empty? wrecks-with-water) (empty? tankers)) "WAIT"
      (empty? wrecks-with-water) (str (:x fullest-tanker) " " (:y fullest-tanker) " 300")
      (reduce #(or %1 %2) false (map #(in? my-unit %) wrecks-with-water)) "WAIT"
      true (str (:x nearest-wrecks) " " (:y nearest-wrecks) " 300"))))

(defmethod compute-action :destroyer [my-unit units game-data]
  (let [reapers (filter-reapers units)
        enemy-reapers (filter-enemy-units reapers)
        nearest-enemy-reaper (first (sort-by #(distance (:x my-unit) (:y my-unit) (:x %) (:y %)) enemy-reapers))
        wrecks (filter-wrecks units)
        wrecks-with-water (filter #(> (:extra %) 0) wrecks)
        tankers (filter-tankers units)
        tankers-sorted-by-distance (sort-by #(distance (:x my-unit) (:y my-unit) (:x %) (:y %)) tankers)
        tankers-sorted-by-water (sort-by :extra > tankers)
        next-tanker (first tankers-sorted-by-distance)
        last-tanker (last tankers-sorted-by-distance)]
    (cond
      (reduce #(or %1 %2) false (map #(in? my-unit %) wrecks-with-water)) (str (:x nearest-enemy-reaper) " " (:y nearest-enemy-reaper) " 300")
      (seq tankers) (str (:x next-tanker) " " (:y next-tanker) " 300")
      true (str (:x nearest-enemy-reaper) " " (:y nearest-enemy-reaper) " 300"))))

(defmethod compute-action :doof [my-unit units game-data]
  (let [enemy-units (filter-enemy-units units)
        nearest-enemy-unit (first (sort-by #(distance (:x my-unit) (:y my-unit) (:x %) (:y %)) enemy-units))]
    (str (:x nearest-enemy-unit) " " (:y nearest-enemy-unit) " 300")))

(defn -main [& args]
  (while true
    (let [my-score (read)
          enemy-score1 (read)
          enemy-score2 (read)
          my-rage (read)
          enemy-rage1 (read)
          enemy-rage2 (read)
          unit-count (read)
          game-data [(PlayerData. 0 my-score my-rage) (PlayerData. 1 enemy-score1 enemy-rage1) (PlayerData. 2 enemy-score2 enemy-rage2)]
          units (atom [])]
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
      (let [my-reaper (first (filter-my-units (filter-reapers @units)))
            my-destroyer (first (filter-my-units (filter-destroyers @units)))
            my-doof (first (filter-my-units (filter-doofs @units)))]
        ;(binding [*out* *err*] (println "wrecks : " (string/join " " (map #(into {} %) wrecks))))
        ;                       (println "wrecks with water: " (string/join " " (map #(into {} %) wrecks-with-water)))
        ;                       (println "tankers : " (string/join " " (map #(into {} %) tankers)))
        ;                       (println "reapers : " (string/join " " (map #(into {} %) reapers)))
        ;                       (println "destroyers : " (string/join " " (map #(into {} %) destroyers)))
        ;                       (println "enemy reapers: " (string/join " " (map #(into {} %) enemy-reapers)))
        ;                       (println "enemy destroyers: " (string/join " " (map #(into {} %) enemy-destroyers)))
        ;                       (println "my reaper : " (string/join " " my-reaper))
        ;                       (println "my destroyer : " (string/join " " my-destroyer))
        ;                       (println "my doof : " (string/join " " my-doof)))
        ;;(println (compute-reaper-action my-reaper wrecks-with-water tankers))
        ;;(println (compute-destroyer-action my-destroyer tankers enemy-reapers my-rage))
        ;;(println (compute-doof-action my-doof enemy-reapers my-rage))
        ;(binding [*out* *err*] (println "my reaper : " (string/join " " my-reaper))
        ;                       (println "my destroyer : " (string/join " " my-destroyer))
        ;                       (println "my doof : " (string/join " " my-doof)))
        (println (compute-action my-reaper @units game-data))
        (println (compute-action my-destroyer @units game-data))
        (println (compute-action my-doof @units game-data))))))
