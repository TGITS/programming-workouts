(ns binary-search-tree)

(defrecord BinarySearchTree [value left right])

(defn value [bst]
  (:value bst))

(defn singleton [value]
  (BinarySearchTree. value nil nil))

(defn left [bst]
  (:left bst))

(defn right [bst]
  (:right bst))

(defn left-empty? [bst]
  (nil? (left bst)))

(defn right-empty? [bst]
  (nil? (right bst)))

(defn insert [val bst]
  (cond
    (<= val (value bst)) (if (left-empty? bst)
                                          (assoc bst :left (singleton val))
                                          (BinarySearchTree. (value bst) (insert val (left bst)) (right bst)))
    (> val (value bst)) (if (right-empty? bst)
                                         (assoc bst :right (singleton val))
                                         (BinarySearchTree. (value bst) (left bst) (insert val (right bst))))))

(defn from-list [seq]
  (let [root (singleton (first seq)) values (rest seq)]
    (reduce #(insert %2 %1) root values)))

(defn to-list [bst]
  (if (nil? bst)
    nil
    (concat (to-list (left bst)) (list (value bst)) (to-list (right bst)))))