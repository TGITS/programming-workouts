(ns fizz-buzz.core
  (:gen-class))


(defn divisible-by-factor?
  "test whether de given integer n strictly positif is divisible by a factor f"
  [f n]
  (= 0 (rem n f))
  )

(def divisible-by-3? (partial divisible-by-factor? 3))
(def divisible-by-5? (partial divisible-by-factor? 5))
(def divisible-by-15? (partial divisible-by-factor? 15))

(defn fizz
  "retourne fizz si multiple de 3 et le nombre sinon"
  [n]
  (if (and (number? n) (divisible-by-3? n)) "fizz" n)
)

(defn buzz
  "retourne fizz si multiple de 5 et le nombre sinon"
  [n]
  (if (and (number? n) (divisible-by-5? n)) "buzz" n)
)

(defn fizzbuzz
  "retourne fizz si multiple de 15 et le nombre sinon"
  [n]
  (if (and (number? n) (divisible-by-15? n)) "fizzbuzz" n)
)


(defn compute-fizzbuzz
  "Compute a list with "
  [numbers]
  (map fizz (map buzz (map fizzbuzz numbers))))

(defn -main
  "FizzBuzz implementation"
  [& args]
  (println (compute-fizzbuzz (range 1 (Integer/parseInt (first args))))))
