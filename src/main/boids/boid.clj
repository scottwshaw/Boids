(ns boids.boid)

(defstruct boid :location :velocity)
(def location (accessor boid :location))
(def velocity (accessor boid :velocity))

(defstruct spatial-vector :x :y)
(def x (accessor spatial-vector :x))
(def y (accessor spatial-vector :y))

(defn distance-between [p1 p2]
  (let [dx (- (x p1) (x p2))
	dy (- (y p1) (y p2))]
    (Math/sqrt (+ (* dx dx) (* dy dy)))))