(ns dice.core-test
  (:require [clojure.test :refer :all]
            [dice.core :refer :all]))

(deftest a-test
  (testing "The value of a dice throw is out of bound"
    (dotimes [_ 100]
      (is (<= 1 (d4) 4))
      (is (<= 1 (d6) 6))
      (is (<= 1 (d8) 8))
      (is (<= 1 (d10) 10))
      (is (<= 1 (d12) 12))
      (is (<= 1 (d20) 20))
      (is (<= 1 (d100) 100)))))



