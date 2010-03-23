(ns boids.rules.match-velocity
  (:use boids.boid-space boids.spatial-vector))

(def velocity-factor (/ 1.0 8.0))

(defn velocity-adjustment [the-boid bspace]
  (let [the-other-boids (remove #(= the-boid %) (:boids bspace))]
    (sv-div (apply sv-sum (map :velocity the-other-boids)) (.length (vec the-other-boids)))))
    

    