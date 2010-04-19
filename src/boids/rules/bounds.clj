(ns boids.rules.bounds
  (:use boids.spatial-vector))

(defn bounds-adjustment [the-boid the-bounds]
  (let [x-adjust (if (< (:x (:location the-boid)) (:xmin the-bounds)) 1.0
		     (if (> (:x (:location the-boid)) (:xmax the-bounds)) -1.0 0.0))
	y-adjust (if (< (:y (:location the-boid)) (:ymin the-bounds)) 1.0
		     (if (> (:y (:location the-boid)) (:ymax the-bounds)) -1.0 0.0))]
    (struct spatial-vector x-adjust y-adjust)))
