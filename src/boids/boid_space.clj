(ns boids.boid-space
  (:use boids.boid boids.spatial-vector))

(defstruct boid-space :xmin :xmax :ymin :ymax :boids)

(defn space-contains-point? [s p]
  (and (<= (:xmin s) (:x p) (:xmax s))
       (<= (:ymin s) (:y p) (:ymax s))))

(defn boids-in-radius [bspace point radius]
  (filter #(<= (distance-between point (:location %)) radius) (:boids bspace)))


