(ns boids.boid)

(defstruct boid :location :velocity)
(def location (accessor boid :location))
(def velocity (accessor boid :velocity))

(defstruct spatial-vector :x :y)
(def x (accessor spatial-vector :x))
(def y (accessor spatial-vector :y))
