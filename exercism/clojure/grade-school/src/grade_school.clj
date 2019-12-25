(ns grade-school)

(defn grade [school grade]
  (get school grade []))

(defn add [school name grade]
  (update school grade concat (vector name)))

(defn sorted [school]
  (let [sorted-school (into (sorted-map) (sort-by key school))] (reduce-kv #(assoc %1 %2 (sort (vec %3))) (empty sorted-school) sorted-school)))
