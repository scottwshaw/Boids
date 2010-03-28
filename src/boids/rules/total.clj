(ns boids.rules.total
  (:use boids.spatial-vector
	boids.rules.center-of-mass
	boids.rules.velocity
	boids.rules.avoidance
	boids.rules.bounds))

(defn total-adjustment [the-boid bspace]
  (let [c (center-of-mass-adjustment the-boid bspace)
	v (velocity-adjustment the-boid bspace)
	a (avoidance-adjustment the-boid bspace)
	b (bounds-adjustment the-boid bspace)]
    (sv-sum c v a b)))
