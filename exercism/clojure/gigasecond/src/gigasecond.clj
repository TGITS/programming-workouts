(ns gigasecond)

(def ONE_BILLION 1000000000)

(defn create-local-date-time [year month days]
  (java.time.LocalDateTime/of year month days 0 0 0))

(defn extract-vector-from-local-date-time [local-date-time]
  [(.getYear local-date-time) (.getMonthValue local-date-time) (.getDayOfMonth local-date-time)])

(defn from [year month days]
  (extract-vector-from-local-date-time (.plusSeconds (create-local-date-time year month days) ONE_BILLION)))
