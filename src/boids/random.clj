(ns boids.random
  (:use	boids.boid
	boids.bounds))

(defn random-boid [the-bounds max-v] 
  (let [loc-x (+ (:xmin the-bounds) (rand (- (:xmax the-bounds) (:xmin the-bounds))))
	loc-y (+ (:ymin the-bounds) (rand (- (:ymax the-bounds) (:ymin the-bounds))))
	vel-x (- (rand (* 2.0 max-v)) max-v)
	vel-y (- (rand (* 2.0 max-v)) max-v)]
    (new-boid loc-x loc-y vel-x vel-y)))

(defn random-boids [n the-bounds max-v] 
  (take n (repeatedly #(random-boid the-bounds max-v))))