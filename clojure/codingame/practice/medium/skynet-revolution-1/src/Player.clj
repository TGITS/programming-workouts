(ns Player
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn output [msg] (println msg) (flush))
(defn debug [msg] (binding [*out* *err*] (println msg) (flush)))

(defn -main [& args]
  (let [N (read) L (read) E (read)]
    ; N: the total number of nodes in the level, including the gateways
    ; L: the number of links
    ; E: the number of exit gateways
    (loop [i L]
      (when (> i 0)
        (let [N1 (read) N2 (read)]
          ; N1: N1 and N2 defines a link between these nodes
          (recur (dec i)))))
    (loop [i E]
      (when (> i 0)
        (let [EI (read)]
          ; EI: the index of a gateway node
          (recur (dec i)))))
    (while true
      (let [SI (read)]
        ; SI: The index of the node on which the Skynet agent is positioned this turn

        ; (debug "Debug messages...")

        ; Example: 0 1 are the indices of the nodes you wish to sever the link between
        (output "0 1")))))
