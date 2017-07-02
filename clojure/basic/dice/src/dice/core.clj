(ns dice.core
  (:gen-class))

(defn create-dice [side] (fn [] (+ (rand-int side) 1)))

(def d4 (create-dice 4))
(def d6 (create-dice 6))
(def d8 (create-dice 8))
(def d10 (create-dice 10))
(def d12 (create-dice 12))
(def d20 (create-dice 20))
(def d100 (create-dice 100))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World of Dicers!")
  (println "Let's roll a 4-sided die " (str (d4)))
  (println "Let's roll a 6-sided die " (str (d6)))
  (println "Let's roll a 8-sided die " (str (d8)))
  (println "Let's roll a 10-sided die " (str (d10)))
  (println "Let's roll a 12-sided die " (str (d12)))
  (println "Let's roll a 20-sided die " (str (d20))))




