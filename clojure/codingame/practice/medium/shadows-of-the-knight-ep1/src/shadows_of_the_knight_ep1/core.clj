(ns shadows-of-the-knight-ep1.core
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn difference [current-value to-substract]
  (Math/abs(- current-value to-substract)))

(defn compute-middle [value]
  (quot value 2))

(defn move-forward [curr-pos delta max-val]
  (min max-val (+ curr-pos delta)))


(defn compute-jump-coord [width height current-x current-y min-x min-y max-x max-y bomb-dir]
  (let [direction (str bomb-dir)]
  (cond
    (= "UR" direction) (do
                          (reset! min-x (inc @current-x))
                          (reset! max-y (dec @current-y))
                          (reset! width (- @max-x @min-x))
                          (reset! height (- @max-y @min-y))
                          (reset! current-x (+ @min-x (compute-middle @width)))
                          (reset! current-y (- @max-y (compute-middle @height ))))
    (= "R"  direction) (do
                          (reset! min-x (inc @current-x))
                          (reset! width (- @max-x @min-x))
                          (reset! current-x (+ @min-x (compute-middle @width) )))
    (= "DR" direction) (do
                          (reset! min-x (inc @current-x))
                          (reset! min-y (inc @current-y))
                          (reset! width (- @max-x @min-x))
                          (reset! height (- @max-y @min-y))
                          (reset! current-x (+ @min-x (compute-middle @width)))
                          (reset! current-y (+ @min-y (compute-middle @height))))
    (= "D"  direction) (do
                          (reset! min-y (inc @current-y))
                          (reset! height (- @max-y @min-y))
                          (reset! current-y (+ @min-y (compute-middle @height))))
    (= "DL" direction) (do
                          (reset! max-x (dec @current-x))
                          (reset! min-y (inc @current-y))
                          (reset! width (- @max-x @min-x))
                          (reset! height (- @max-y @min-y))
                          (reset! current-x (- @max-x (compute-middle @width)))
                          (reset! current-y (+ @min-y (compute-middle @height))))
    (= "L"  direction) (do
                          (reset! max-x (dec @current-x))
                          (reset! width (- @max-x @min-x))
                          (reset! current-x (- @max-x (compute-middle @width))))
    (= "UL" direction) (do
                          (reset! max-x (dec @current-x))
                          (reset! max-y (dec @current-y))
                          (reset! width (- @max-x @min-x))
                          (reset! height (- @max-y @min-y))
                          (reset! current-x (- @max-x (compute-middle @width)))
                          (reset! current-y (- @max-y (compute-middle @height ))))
    (= "U"  direction) (do
                          (reset! max-y (dec @current-y))
                          (reset! height (- @max-y @min-y))
                          (reset! current-y (- @max-y (compute-middle @height )))))))

(defn -main [& args]
  (let [W (read) H (read) N (read) X0 (read) Y0 (read)
        current-width (atom (Integer/valueOf W)) current-height (atom (Integer/valueOf H))
        current-x (atom (Integer/valueOf X0)) current-y (atom (Integer/valueOf Y0))
        min-x (atom 0) min-y (atom 0)
        max-x (atom (dec @current-width)) max-y (atom(dec @current-height))]
    ; W: width of the building.
    ; H: height of the building.
    ; N: maximum number of turns before game over.
    (while true
      (let [bombDir (read)]
        ; bombDir: the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

        ; (binding [*out* *err*]
        ;   (println "Debug messages..."))
        (binding [*out* *err*]
           (println (str "width : " @current-width " height : " @current-height " current-x : " @current-x " current-y : " @current-y)))
        (compute-jump-coord
          current-width current-height current-x current-y min-x min-y max-x max-y bombDir)
        ; the location of the next window Batman should jump to.
        (println (str @current-x " " @current-y))))))

