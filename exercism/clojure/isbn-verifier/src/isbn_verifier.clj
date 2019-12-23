(ns isbn-verifier)

(defn filter-dashes
  "Remove the dashes from a ISBN"
  [isbn]
  (filter #(or (Character/isDigit %) (= \X %)) isbn))

(defn transform-char-as-digit
  "Transform a char that is a digit or that is the character 'X' in the corresponding number. X is translate as 10"
  [digit-as-char-or-x]
  (cond
    (Character/isDigit digit-as-char-or-x) (Character/digit digit-as-char-or-x 10)
    (= \X digit-as-char-or-x) 10))

(defn indexed-from
  "Taken a first index value and a sequence return a sequence of vector [index value].
   For example (indexed-form 1 [a b c]) returns [[1 a] [2 b] [3 c]]"
  [from sequence]
  (map vector (iterate inc from) sequence))

(defn multiply-by-index-and-add
  "Taken acc as an integer value and current-element as a 2 elements vector of integer.
   the acc value is added to the result of multiplying the 2 elements of the vector"
  [acc current-element]
  (+ acc (* (nth current-element 1) (nth current-element 0))))

(defn compute-isbn-numeric-value-from-formula
  "Compute the value of a well-formed isbn"
  [isbn]
  (->> isbn
       (filter-dashes)
       (map transform-char-as-digit)
       (reverse)
       (indexed-from 1)
       (reduce multiply-by-index-and-add 0)))

(defn isbn-well-formed?
  "check whether an isbn is well-formed. The isbn can be well-formed but invalid"
  [isbn]
  (not (nil? (re-matches #"\d\-?\d{3}\-?\d{5}\-?(\d|X)" isbn))))

(defn isbn-valid?
  "check whether a well-formed isbn is valid"
  [isbn]
  (= 0 (mod  (compute-isbn-numeric-value-from-formula isbn) 11)))

(defn isbn?
  "check whether the provided string is a correct isbn"
  [isbn]
  (and (isbn-well-formed? isbn) (isbn-valid? isbn)))
