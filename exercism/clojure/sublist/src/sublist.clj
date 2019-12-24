(ns sublist)

(defn search-list1-in-list2 [list1 list2]
  (let [size1 (count list1)]
    (loop [list2 list2]
      (cond
        (= list1 (take size1 list2)) :sublist
        (< size1 (count list2)) (recur (rest list2))
        :else :unequal))))

(defn search-list2-in-list1 [list1 list2]
  (let [size2 (count list2)]
    (loop [list1 list1]
      (cond
        (= list2 (take size2 list1)) :superlist
        (< size2 (count list1)) (recur (rest list1))
        :else :unequal))))

(defn search-sublist-in-list [sublist list inclusion-type]
  (let [size (count sublist)]
    (loop [list list]
      (cond
        (= sublist (take size list)) inclusion-type
        (< size (count list)) (recur (rest list))
        :else :unequal))))

(defn classify [list1 list2]
  (cond
    (or (and (empty? list1) (empty? list2)) (= list1 list2)) :equal
    (empty? list1) :sublist
    (empty? list2) :superlist
    (<= (count list1) (count list2)) (search-sublist-in-list list1 list2 :sublist)
    (> (count list1) (count list2)) (search-sublist-in-list list2 list1 :superlist)))
