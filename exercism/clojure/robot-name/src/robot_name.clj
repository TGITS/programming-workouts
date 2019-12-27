(ns robot-name
  (:require [clojure.string :as str]))

(def existing-robot-names (atom #{}))

(defrecord Robot [name])

(defn generate-random-string [size alphabet]
  (str/join (repeatedly size #(rand-nth (shuffle (random-sample 0.5  alphabet))))))

(defn generate-random-int-as-string [size int-max]
  (str/join (repeatedly size #(str (rand-int int-max)))))

(defn generate-random-robot-name [prefix-size suffix-size]
  (str
   (generate-random-string prefix-size "ABCDEFGHIJKLMNOPKRSTUVWXYZ")
   (generate-random-int-as-string suffix-size 10)))

(defn generate-robot-name []
  (loop [robot-name (generate-random-robot-name 2 3)]
    (if (contains? (deref existing-robot-names) robot-name)
      (recur (generate-random-robot-name 2 3))
      (do (swap! existing-robot-names conj robot-name) robot-name))))

(defn robot []
  (Robot.  (atom (generate-robot-name))))

(defn robot-name [robot]
  (deref (:name robot)))

(defn reset-name [robot]
  (reset! (:name robot) (generate-robot-name)))
