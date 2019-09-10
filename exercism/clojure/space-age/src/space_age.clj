(ns space-age)

(def earth-orbital-period-in-seconds 31557600.0)

(def mercury-orbital-period-in-seconds (* earth-orbital-period-in-seconds 0.2408467))

(def venus-orbital-period-in-seconds (* earth-orbital-period-in-seconds 0.61519726))

(def mars-orbital-period-in-seconds (* earth-orbital-period-in-seconds 1.8808158))

(def jupiter-orbital-period-in-seconds (* earth-orbital-period-in-seconds 11.862615))

(def saturn-orbital-period-in-seconds (* earth-orbital-period-in-seconds 29.447498))

(def neptune-orbital-period-in-seconds (* earth-orbital-period-in-seconds 164.79132))

(def uranus-orbital-period-in-seconds (* earth-orbital-period-in-seconds 84.016846))

(defn on-earth [seconds]
  (/ (double seconds) earth-orbital-period-in-seconds))

(defn on-mercury [seconds]
  (/ (double seconds) mercury-orbital-period-in-seconds))

(defn on-venus [seconds]
  (/ (double seconds) venus-orbital-period-in-seconds))

(defn on-mars [seconds]
  (/ (double seconds) mars-orbital-period-in-seconds))

(defn on-jupiter [seconds]
  (/ (double seconds) jupiter-orbital-period-in-seconds))

(defn on-saturn [seconds]
  (/ (double seconds) saturn-orbital-period-in-seconds))

(defn on-neptune [seconds]
  (/ (double seconds) neptune-orbital-period-in-seconds))

(defn on-uranus [seconds]
  (/ (double seconds) uranus-orbital-period-in-seconds))
