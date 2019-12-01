(ns run-length-encoding)

;; Pensez à faire une version avec reduce
;; Pensez à changer les noms des fonctions qui sont vraiment pourris
;; Penser à utilise le threading opératoir plutôt que les maps imbriqués
;; Changer les noms des variables c ==> current-char
;; Variabiliser (peek (peek acc)) dans un let
(defn string-to-vector-of-char
  "transform a string in a vector of vector of characters"
  [s]
  (loop [acc [] char-seq s]
    (let [c (first char-seq)]
      (if (nil? c) acc
          (cond
            (empty? acc) (recur (conj acc (vector c)) (rest char-seq))
            (= c (peek (peek acc))) (recur (conj (vec (butlast acc)) (conj (peek acc) c)) (rest char-seq))
            :else (recur (conj acc (vector c)) (rest char-seq)))))))

(defn transform-vector-of-char
  "transform a vector of vector of characters in a vector of 2 dimension vector with the number of character and the associated character"
  [vector-of-char]
  (clojure.string/join (map clojure.string/join (map #(vector (let [number-of-chars (count %)] (if (= 1 number-of-chars) "" (str number-of-chars))) (str (peek %))) vector-of-char))))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text])

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text])
