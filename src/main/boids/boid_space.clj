(ns boids.boid-space
  (:use boids.boid))

(defstruct boid-space :xmin :xmax :ymin :ymax :boids)
(def xmin (accessor boid-space :xmin))
(def xmax (accessor boid-space :xmax))
(def ymin (accessor boid-space :ymin))
(def ymax (accessor boid-space :ymax))
(def boids (accessor boid-space :boids))

(defn space-contains-point? [s p]
  (and (<= (xmin s) (x p) (xmax s))
       (<= (ymin s) (y p) (ymax s))))

