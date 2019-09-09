(ns space-age)

(def earth-orbital-period-in-seconds 31557600.0)

(defn on-earth [seconds]
  (/ (double seconds) earth-orbital-period-in-seconds))

(defn on-mercury [seconds]
  (* (on-earth seconds) 0.2408467))

(defn on-venus [seconds]
  seconds)

(defn on-mars [seconds]
  seconds)

(defn on-jupiter [seconds]
  seconds)

(defn on-saturn [seconds]
  seconds)

(defn on-neptune [seconds]
  seconds)

(defn on-uranus [seconds]
  seconds)
