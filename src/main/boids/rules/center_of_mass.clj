(ns boids.rules.center-of-mass 
  (:use boids.boid boids.spatial-vector))

(defn center-of-mass [the-boids] 
  (struct-map spatial-vector :x 0.0 :y 0.0))