(ns pangram
  (:require [clojure.set :as set])
  (:require [clojure.string :as string]))

(def alphabet #{\a \b \c \d \e \f \g \h \i \j \k \m \n \o \p \q \r \s \t \u \v \w \x \y \z})

(defn pangram? [string-to-test]
  (= alphabet (set/intersection alphabet (set (seq (string/lower-case string-to-test))))))
