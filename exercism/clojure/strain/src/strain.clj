(ns strain)

;; A solution with reduce 
;; (defn retain [pred v] 
;;   (reduce #(if (pred %2) (conj %1 %2) %1) [] v))

;; (defn discard [pred v] 
;;   (reduce #(if (pred %2) %1 (conj %1 %2)) [] v))

;; A recursive solution 
(defn retain [pred v]
  (if
   (or (nil? v) (empty? v)) []
   (if (pred (first v)) (cons  (first v) (retain pred (rest v))) (retain pred (rest v)))))

(defn discard [pred v]
  (if
   (or (nil? v) (empty? v)) []
   (if (pred (first v)) (discard pred (rest v)) (cons  (first v) (discard pred (rest v))))))