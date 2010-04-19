(ns boids.boid
  (:use boids.spatial-vector
	boids.rules.total))

(defstruct boid :location :velocity)

(defn move-boid-one-step [the-boid bspace]
  (let [adjustment (total-adjustment the-boid bspace)]
    (struct boid (sv-sum (:location the-boid) adjustment) adjustment)))
	  