(ns move-zeros.core
  (:gen-class))

(defn move-zeros
  "Given a list of integers that have zeros interspersed
   throughout, move all the zeros to the end. Name the function move-zeros; it accepts
   a list as an argument and returns a new list with the zeros at the end."
  [ aSequence ]
  (concat  (filter #(not(= 0 % )) aSequence) (filter #(= 0 % ) aSequence)))

(defn string-seq-to-int-seq
  "Transforming a sequence of String (representing integers) in a sequence of integers"
  [ sequenceOfStrings ]
  (map #(Integer/parseInt %) sequenceOfStrings))

; example usage with leiningen : lein run 1 2 0 0 0 0 3 4 5
; expected result : (1 2 3 4 5 0 0 0 0
(defn -main
  "You must provide several strings that represent integers - the zeros are moves to the back"
  [& args]
  (let [ aSequence *command-line-args* ]
    (println aSequence)
    (println (move-zeros (string-seq-to-int-seq aSequence)))))
