(ns boids.rules.goal
  (:use boids.boid
	boids.spatial-vector))

(defn goal-adjustment [the-boid the-goal]
  (sv-diff the-goal (:location the-boid)))