(ns boids.boid
  (:use boids.spatial-vector
	boids.rules.total))

(defstruct boid :location :velocity)

(defn boids-in-radius [blist point radius]
  (filter #(<= (distance-between point (:location %)) radius) blist))

(defn move-boid-one-step [the-boid bspace]
  (let [adjustment (total-adjustment the-boid bspace)]
    (struct boid (sv-sum (:location the-boid) adjustment) adjustment)))
	  