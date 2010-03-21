(ns boids.rules.center-of-mass 
  (:use boids.boid boids.spatial-vector))

(defn center-of-mass [the-boids]
  (sv-div (apply sv-sum (map :location the-boids)) (.length the-boids)))
