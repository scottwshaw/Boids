(ns boids.boid
  (:use boids.spatial-vector))

(defstruct boid :location :velocity)

(defn new-boid [x y vx vy]
  (struct boid (struct spatial-vector x y) (struct spatial-vector vx vy)))
