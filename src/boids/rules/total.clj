(ns boids.rules.total
  (:use boids.spatial-vector
	boids.rules.center-of-mass
	boids.rules.velocity
	boids.rules.avoidance
	boids.rules.bounds))

(def *center-of-mass-weight* 0.01)
(def *velocity-weight* 1.0)
(def *avoidance-weight* 1.0)
(def *bounds-weight* 1.0)

(defn total-adjustment [the-boid all-boids bounds]
  (let [c (sv-mul *center-of-mass-weight* (center-of-mass-adjustment the-boid all-boids))
	v (sv-mul *velocity-weight* (velocity-adjustment the-boid all-boids))
	a (sv-mul *avoidance-weight* (avoidance-adjustment the-boid all-boids))
	b (sv-mul *bounds-weight* (bounds-adjustment the-boid bounds))]
    (sv-sum c v a b (:velocity the-boid))))
