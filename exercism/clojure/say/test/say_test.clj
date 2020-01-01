(ns say-test
  (:require [clojure.test :refer [deftest is]]
            say))

(deftest below-zero-is-out-of-range-for-units-test
  (is (thrown? IllegalArgumentException (say/units -1))))

(deftest zero-for-units-test
  (is (= "zero" (say/units 0))))

(deftest one-for-units-test
  (is (= "one" (say/units 1))))

(deftest two-for-units-test
  (is (= "two" (say/units 2))))

(deftest three-for-units-test
  (is (= "three" (say/units 3))))

(deftest four-for-units-test
  (is (= "four" (say/units 4))))

(deftest five-for-units-test
  (is (= "five" (say/units 5))))

(deftest six-for-units-test
  (is (= "six" (say/units 6))))

(deftest seven-for-units-test
  (is (= "seven" (say/units 7))))

(deftest eight-for-units-test
  (is (= "eight" (say/units 8))))

(deftest nine-for-units-test
  (is (= "nine" (say/units 9))))

(deftest above-nine-is-out-of-range-for-units-test-1
  (is (thrown? IllegalArgumentException (say/units 10))))

(deftest above-nine-is-out-of-range-for-units-test-2
  (is (thrown? IllegalArgumentException (say/units 154))))

(deftest ten-for-decades-strictly-below-twenty-test
  (is (= "ten" (say/decades-strictly-below-twenty 10))))

(deftest eleven-for-decades-strictly-below-twenty-test
  (is (= "eleven" (say/decades-strictly-below-twenty 11))))

(deftest twelve-for-decades-strictly-below-twenty-test
  (is (= "twelve" (say/decades-strictly-below-twenty 12))))

(deftest thirteen-for-decades-strictly-below-twenty-test
  (is (= "thirteen" (say/decades-strictly-below-twenty 13))))

(deftest fourteen-for-decades-strictly-below-twenty-test
  (is (= "fourteen" (say/decades-strictly-below-twenty 14))))

(deftest fifteen-for-decades-strictly-below-twenty-test
  (is (= "fifteen" (say/decades-strictly-below-twenty 15))))

(deftest sixteen-for-decades-strictly-below-twenty-test
  (is (= "sixteen" (say/decades-strictly-below-twenty 16))))

(deftest seventeen-for-decades-strictly-below-twenty-test
  (is (= "seventeen" (say/decades-strictly-below-twenty 17))))

(deftest eighteen-for-decades-strictly-below-twenty-test
  (is (= "eighteen" (say/decades-strictly-below-twenty 18))))

(deftest nineteen-for-decades-strictly-below-twenty-test
  (is (= "nineteen" (say/decades-strictly-below-twenty 19))))

(deftest above-nineteen-for-decades-strictly-below-twenty-test-1
  (is (thrown? IllegalArgumentException (say/decades-strictly-below-twenty 20))))

(deftest above-nineteen-for-decades-strictly-below-twenty-test-2
  (is (thrown? IllegalArgumentException (say/decades-strictly-below-twenty 123456789))))

(deftest zero-for-decades-and-units-test
  (is (= "zero" (say/decades-and-units 0))))

(deftest eight-for-decades-and-units-test
  (is (= "eight" (say/decades-and-units 8))))

(deftest nine-for-decades-and-units-test
  (is (= "nine" (say/decades-and-units 9))))

(deftest ten-for-decades-and-units-test
  (is (= "ten" (say/decades-and-units 10))))

(deftest eleven-for-decades-and-units-test
  (is (= "eleven" (say/decades-and-units 11))))

(deftest twelve-for-decades-and-units-test
  (is (= "twelve" (say/decades-and-units 12))))

(deftest thirteen-for-decades-and-units-test
  (is (= "thirteen" (say/decades-and-units 13))))

(deftest fourteen-for-decades-and-units-test
  (is (= "fourteen" (say/decades-and-units 14))))

(deftest fifteen-for-decades-and-units-test
  (is (= "fifteen" (say/decades-and-units 15))))

(deftest sixteen-for-decades-and-units-test
  (is (= "sixteen" (say/decades-and-units 16))))

(deftest seventeen-for-decades-and-units-test
  (is (= "seventeen" (say/decades-and-units 17))))

(deftest eighteen-for-decades-and-units-test
  (is (= "eighteen" (say/decades-and-units 18))))

(deftest nineteen-for-decades-and-units-test
  (is (= "nineteen" (say/decades-and-units 19))))

(deftest twenty-for-decades-and-units-test
  (is (= "twenty" (say/decades-and-units 20))))

(deftest twenty-one-for-decades-and-units-test
  (is (= "twenty-one" (say/decades-and-units 21))))

(deftest thirty-for-decades-and-units-test
  (is (= "thirty" (say/decades-and-units 30))))

(deftest forty-for-decades-and-units-test
  (is (= "forty" (say/decades-and-units 40))))

(deftest zero-for-hundreds-test
  (is (= "" (say/hundreds 0))))

(deftest seven-for-hundreds-test
  (is (= "seven" (say/hundreds 7))))

(deftest eleven-for-hundreds-test
  (is (= "eleven" (say/hundreds 11))))

(deftest fifty-seven-for-hundreds-test
  (is (= "fifty-seven" (say/hundreds 57))))

(deftest one-hundred-for-hundreds-test
  (is (= "one hundred" (say/hundreds 100))))

(deftest eight-hundred-and-fifty-nine-for-hundreds-test
  (is (= "eight hundred fifty-nine" (say/hundreds 859))))

(deftest zero-test
  (is (= "zero" (say/number 0))))

(deftest one-test
  (is (= "one" (say/number 1))))

(deftest fourteen-test
  (is (= "fourteen" (say/number 14))))

(deftest twenty-test
  (is (= "twenty" (say/number 20))))

(deftest twenty-two-test
  (is (= "twenty-two" (say/number 22))))

(deftest one-hundred-test
  (is (= "one hundred" (say/number 100))))

(deftest one-hundred-twenty-three-test
  (is (= "one hundred twenty-three" (say/number 123))))

(deftest one-thousand-test
  (is (= "one thousand" (say/number 1000))))

(deftest one-thousand-two-hundred-thirty-four-test
  (is (= "one thousand two hundred thirty-four" (say/number 1234))))

(deftest one-million-test
  (is (= "one million" (say/number 1000000))))

(deftest one-million-two-thousand-three-hundred-forty-five-test
  (is (= "one million two thousand three hundred forty-five" (say/number 1002345))))

(deftest one-billion-test
  (is (= "one billion" (say/number 1000000000))))

(deftest a-big-number-test
  (is (= "nine hundred eighty-seven billion six hundred fifty-four million three hundred twenty-one thousand one hundred twenty-three" (say/number 987654321123))))

(deftest below-zero-is-out-of-range-test
  (is (thrown? IllegalArgumentException (say/number -1))))

(deftest numbers-above-999999999999-out-of-range-test
  (is (thrown? IllegalArgumentException (say/number 1000000000000))))
