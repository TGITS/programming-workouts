(ns planets.modeling-test
  (:require [clojure.test :refer :all]
            [planets.modeling :refer :all]))

(deftest a-test
  (println earth-with-map)
  (testing "The Earth with a Map"
    (is (and 
         (= "Earth" (:name earth-with-map))
         (= 1 (:moons earth-with-map))
         (= 1.08321e12 (:volume earth-with-map))
         (= 5.97219e24 (:mass earth-with-map))
         (= 152098232 (:aphelion earth-with-map))
         (= 147098290 (:perihelion earth-with-map))
         (nil? (:type earth-with-map))
         )
        )))
