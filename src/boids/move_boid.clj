(ns boids.move-boid
  (:use boids.boid
	boids.spatial-vector
	boids.rules.total))

(defn move-boid-one-step [the-boid all-boids the-bounds the-goal]
     (let [adjustment (total-adjustment the-boid all-boids the-bounds the-goal)]
       (struct boid (sv-sum (:location the-boid) adjustment) adjustment)))
	  
(defn move-all-boids-one-step [boid-list the-bounds the-goal]
     (for [b boid-list] (move-boid-one-step b boid-list the-bounds the-goal)))
