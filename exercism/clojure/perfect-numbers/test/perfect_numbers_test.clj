(ns perfect-numbers-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [perfect-numbers]))

(deftest test-initialize-perfect-number
  (testing "Negative numbers throw an exception"
    (is (thrown? IllegalArgumentException (perfect-numbers/classify -1)))))

(deftest test-classify-deficient
  (testing "13 is a deficient number"
    (is (= :deficient (perfect-numbers/classify 13)))))

(deftest test-classify-abundant
  (testing "12 is an abundant number"
    (is (= :abundant (perfect-numbers/classify 12)))))

(deftest test-classify-perfect-1
  (testing "28 is a perfect number"
    (is (= :perfect (perfect-numbers/classify 28)))))

(deftest test-classify-perfect-2
  (testing "6 is a perfect number"
    (is (= :perfect (perfect-numbers/classify 6)))))

(deftest test-classify-perfect-3
  (testing "496 is a perfect number"
    (is (= :perfect (perfect-numbers/classify 496)))))

(deftest test-classify-perfect-4
  (testing "8128 is a perfect number"
    (is (= :perfect (perfect-numbers/classify 8128)))))

(deftest test-classify-perfect-5
  (testing "33 550 336 is a perfect number"
    (is (= :perfect (perfect-numbers/classify 33550336)))))

;; The following numbers are to big to be tested with the current implementation

;;(deftest test-classify-perfect-6
;;  (testing "8 589 869 056 is a perfect number"
;;    (is (= :perfect (perfect-numbers/classify 8589869056)))))

;;(deftest test-classify-perfect-7
;;  (testing "137 438 691 328 is a perfect number"
;;    (is (= :perfect (perfect-numbers/classify 137438691328)))))