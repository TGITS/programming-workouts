(ns shadows-of-the-knight-ep1.core
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn compute-middle [start end]
  (quot (- end start) 2))

(defn compute-jump-coord [width height current-x current-y bomb-dir]
  (cond
    (= "UR" bomb-dir) (do
                        (reset! current-x (compute-middle (min (inc @current-x) (dec width)) (max (inc @current-x) (dec width))))
                        (reset! current-y (compute-middle (min (dec @current-y) 0) (max (dec @current-y) 0))))
    (= "R"  bomb-dir) (reset! current-x (compute-middle (min (inc @current-x) (dec width)) (max (inc @current-x) (dec width))))
    (= "DR" bomb-dir) (do
                        (reset! current-x (compute-middle (min (inc @current-x) (dec width)) (max (inc @current-x) (dec width))))
                        (reset! current-y (compute-middle (min (inc @current-y) (dec height)) (max (inc @current-y) (dec height)))))
    (= "D"  bomb-dir) (reset! current-y (compute-middle (min (inc @current-y) (dec height)) (max (inc @current-y) (dec height))))
    (= "DL" bomb-dir) (do
                        (reset! current-x (compute-middle (min (dec @current-x) 0) (max (dec @current-x) 0)))
                        (reset! current-y (compute-middle (min (inc @current-y) (dec height)) (max (inc @current-y) (dec height)))))
    (= "L"  bomb-dir) (reset! current-x (compute-middle (min (dec @current-x) 0) (max (dec @current-x) 0)))
    (= "UL" bomb-dir) (do
                        (reset! current-x (compute-middle (min (dec @current-x) 0) (max (dec @current-x) 0)))
                        (reset! current-y (compute-middle (min (dec @current-y) 0) (max (dec @current-y) 0))))
    (= "U"  bomb-dir) (reset! current-y (compute-middle (min (dec @current-y) 0) (max (dec @current-y) 0)))))

(defn -main [& args]
  (let [W (read) H (read) N (read) X0 (read) Y0 (read) current-x (atom X0) current-y (atom Y0)]
    ; W: width of the building.
    ; H: height of the building.
    ; N: maximum number of turns before game over.
    (while true
      (let [bombDir (read)]
        ; bombDir: the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

        ; (binding [*out* *err*]
        ;   (println "Debug messages..."))
        (compute-jump-coord
          (Integer/valueOf W) (Integer/valueOf H) current-x current-y bombDir)
        ; the location of the next window Batman should jump to.
        (println (str @current-x " " @current-y))))))
