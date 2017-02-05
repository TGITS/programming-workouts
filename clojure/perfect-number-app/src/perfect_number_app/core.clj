(ns perfect-number-app.core
  (:gen-class))

(defn divisors
  "Retourne la liste des diviseurs d'un nombre n donné en paramètre"
  [n]
  (filter #(= 0 (rem n %1 )) (range 1 n)))

(defn perfect-number?
  "Test si un nombre n donné en paramètre est parfait. Pour rappel, en arithmétique,
  un nombre parfait est un entier naturel n tel que somme(n) = 2n où somme(n)
  est la somme des diviseurs positifs de n.
  Voir https://fr.wikipedia.org/wiki/Nombre_parfait pour plus de détail."
  [n]
  (= n (reduce + (divisors n))))

  (defn -main
    "Vous n'auriez pas trouver tout seul : point d'entrée du programme
    Imprime la liste des nombres parfaits entre 0 et le premier argument"
    [& args]
    (if (and (> 0 (count args) (number? (first args)))
      (println (filter perfect-number? (range 0 (Integer/parseInt (first args)))))
      (println "You should provide a number argument"))))
