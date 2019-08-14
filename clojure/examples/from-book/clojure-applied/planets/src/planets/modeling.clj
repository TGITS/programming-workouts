(ns planets.modeling)

(def earth-with-map 
  {:name "Earth"
   :moons 1
   :volume 1.08321e12 ;; km^3
   :mass 5.97219e24 ;; kg
   :aphelion 152098232 ;; km, farthest from sun
   :perihelion 147098290 ;; km, closest to sun
   })

(def earth-with-map-and-type
  {:name "Earth"
   :moons 1
   :volume 1.08321e12 ;; km^3
   :mass 5.97219e24 ;; kg
   :aphelion 152098232 ;; km, farthest from sun
   :perihelion 147098290 ;; km, closest to sun
   :type :Planet ;; entity type
   })

(defrecord Planet [name
                   moons
                   volume ;; km^3
                   mass ;; kg
                   aphelion ;; km, farthest from sun
                   perihelion ;; km, closest to sun
                   ])

;; Positional factory function
(def earth-with-positional-factory
  (->Planet "Earth" 1 1.08321e12 5.97219e24 152098232 147098290))

;; Map factory function
(def earth-with-factory
  (map->Planet {:name "Earth"
                :moons 1
                :volume 1.08321e12
                :mass 5.97219e24
                :aphelion 152098232
                :perihelion 147098290}))