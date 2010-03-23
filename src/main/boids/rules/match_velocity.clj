(ns boids.rules.match-velocity
  (:use boids.boid-space boids.spatial-vector))

(def velocity-factor (/ 1.0 8.0))

(defn velocity-adjustment [the-boid bspace]
  (struct-map spatial-vector :x 0 :y 0))
    

    