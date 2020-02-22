(ns Player
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn output [msg] (println msg) (flush))
(defn debug [msg] (binding [*out* *err*] (println msg) (flush)))
(defn create-graph-from-read-input [number-of-links]
  (loop [i number-of-links graph {}]
    (if (> i 0)
      (let [n1 (read) n2 (read)]
          ; n1: n1 and n2 defines a link between these nodes
        (recur (dec i) (merge-with {n1  n2, n2 n1} graph))) (graph))))
(defn create-indexes-of-exits-from-read-input [number-of-exits]
  (loop [i number-of-exits index-of-exits #{}]
    (if (> i 0)
      (let [exit-index (read)]
          ; EI: the index of a gateway node
        (recur (dec i) (conj index-of-exits exit-index))) index-of-exits)))

(defn -main [& _]
  (let [number-of-nodes (read)
        number-of-links (read)
        number-of-exits (read)
    ; number-of-nodes: the total number of nodes in the level, including the gateways
    ; number-of-links: the number of links
    ; number-of-exits: the number of exit gateways
        graph (create-graph-from-read-input number-of-links)
        index-of-exits (create-indexes-of-exits-from-read-input number-of-exits)]
    (while true
      (let [skynet-index (read)]
        ; SI: The index of the node on which the Skynet agent is positioned this turn

        ; (debug "Debug messages...")

        ; Example: 0 1 are the indices of the nodes you wish to sever the link between
        (output "0 1")))))
