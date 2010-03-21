(ns boids.spatial-vector)

(defstruct spatial-vector :x :y)

(defn distance-between [p1 p2]
  (let [dx (- (:x p1) (:x p2))
	dy (- (:y p1) (:y p2))]
    (Math/sqrt (+ (* dx dx) (* dy dy)))))