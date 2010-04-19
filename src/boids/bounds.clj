(ns boids.bounds
  (:use boids.spatial-vector))

(defstruct bounds :xmin :xmax :ymin :ymax)

(defn bounds-contain-point? [s p]
  (and (<= (:xmin s) (:x p) (:xmax s))
       (<= (:ymin s) (:y p) (:ymax s))))
