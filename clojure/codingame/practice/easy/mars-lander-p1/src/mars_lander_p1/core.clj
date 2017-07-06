(ns mars-lander-p1.core
  (:gen-class))

(defn compute-thrust-power
      "Compute the thrust power that need to be applied"
      [current-thrust-power current-vertical-speed]
      (binding [*out* *err*]
               (println (str "current-thrust-power : " (str current-thrust-power) " current-vertical-speed : " (str current-vertical-speed))))
      (cond
        (> current-vertical-speed 39) (str (max (dec current-thrust-power) 0))
        (< current-vertical-speed -39) (str (min (inc current-thrust-power) 4))
        true "0"))


(defn -main [& args]
      (let [surfaceN (read)]
           ; surfaceN: the number of points used to draw the surface of Mars.
           (loop [i surfaceN]
                 (when (> i 0)
                       (let [landX (read) landY (read)]
                            ; landX: X coordinate of a surface point. (0 to 6999)
                            ; landY: Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
                            (recur (dec i)))))
           (while true
                  (let [X (read) Y (read) hSpeed (read) vSpeed (read) fuel (read) rotate (read) power (read)]
                       ; hSpeed: the horizontal speed (in m/s), can be negative.
                       ; vSpeed: the vertical speed (in m/s), can be negative.
                       ; fuel: the quantity of remaining fuel in liters.
                       ; rotate: the rotation angle in degrees (-90 to 90).
                       ; power: the thrust power (0 to 4).

                       ; (binding [*out* *err*]
                       ;   (println "Debug messages..."))

                       ; 2 integers: rotate power. rotate is the desired rotation angle (should be 0 for level 1), power is the desired thrust power (0 to 4).
                       (println (str "0 " (compute-thrust-power (Integer/valueOf power) (Integer/valueOf vSpeed))))))))
