(ns boids.boid
  (:use boids.spatial-vector
	boids.rules.total))

(defstruct boid :location :velocity)

(defn move-boid-one-step [the-boid all-boids the-bounds]
  (let [adjustment (total-adjustment the-boid all-boids the-bounds)]
    (struct boid (sv-sum (:location the-boid) adjustment) adjustment)))
	  
(defn move-all-boids-one-step [boid-list the-bounds]
  (for [b boid-list] (move-boid-one-step b boid-list the-bounds)))
