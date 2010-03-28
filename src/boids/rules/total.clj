(ns boids.rules.total
  (:use boids.spatial-vector
	boids.rules.center-of-mass
	boids.rules.velocity
	boids.rules.avoidance
	boids.rules.bounds))

(defn total-adjustment [the-boid bspace]
  (sv-sum (center-of-mass-adjustment the-boid bspace)
	  (velocity-adjustment the-boid bspace)
	  (avoidance-adjustment the-boid bspace)
	  (bounds-adjustment the-boid bspace)))
