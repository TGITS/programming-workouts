(ns sublist)

(defn search-sublist-in-list [sublist list inclusion-type]
  (let [size (count sublist)]
    (loop [list list]
      (cond
        (or
         (empty? sublist)
         (= sublist (take size list))) inclusion-type
        (< size (count list)) (recur (rest list))
        :else :unequal))))

(defn classify [list1 list2]
  (cond
    (or
     (and (empty? list1) (empty? list2))
     (= list1 list2)) :equal
    (<= (count list1) (count list2)) (search-sublist-in-list list1 list2 :sublist)
    (> (count list1) (count list2)) (search-sublist-in-list list2 list1 :superlist)))
