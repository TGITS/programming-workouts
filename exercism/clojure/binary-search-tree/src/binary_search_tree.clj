(ns binary-search-tree)

(defrecord BinarySearchTree [value left right])

(defn value [binary-search-tree]
  (:value binary-search-tree))

(defn singleton [value]
  (BinarySearchTree. value nil nil))

(defn left [binary-search-tree]
  (:left binary-search-tree))

(defn right [binary-search-tree]
  (:right binary-search-tree))

(defn insert [val binary-search-tree]
  (cond
    (<= val (value binary-search-tree)) (if (nil? (left binary-search-tree)) (assoc binary-search-tree :left (singleton val)) (insert val (left binary-search-tree)))
    (> val (value binary-search-tree)) (if (nil? (right binary-search-tree)) (assoc binary-search-tree :right (singleton val)) (insert val (right binary-search-tree)))))

(defn from-list [seq]
    (doseq [root (singleton (first seq)) value (rest seq)]
      (insert value root)))

(defn to-list [] ;; <- arglist goes here
  ;; your code goes here
  )


