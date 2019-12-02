(ns run-length-encoding
  (:require [clojure.string :as string :only join]))

;; Pensez à faire une version avec reduce
;; Changer les noms des variables c ==> current-char
;; Variabiliser (peek (peek acc)) dans un let
(defn plain-text-to-vector
  "transform the plain text in a vector of vector of characters"
  [plain-text]
  (loop [acc [] char-seq plain-text]
    (let [current-char (first char-seq) current-vector (peek acc)]
      (if (nil? current-char) acc
          (cond
            (or (empty? acc) (not (= current-char (peek current-vector)))) (recur (conj acc (vector current-char)) (rest char-seq))
            (= current-char (peek current-vector)) (recur (conj (vec (butlast acc)) (conj current-vector current-char)) (rest char-seq)))))))

(defn vector-to-cipher-text
  "transform a vector of vector of characters in the run-length encoded string"
  [char-seq]
  (->> char-seq
       (map #(vector (let [number-of-chars (count %)]
                       (if (= 1 number-of-chars)
                         "" (str number-of-chars))) (str (peek %))))
       (map string/join)
       (string/join)))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> plain-text
       (plain-text-to-vector)
       (vector-to-cipher-text)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text])
