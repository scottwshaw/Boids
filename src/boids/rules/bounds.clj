(ns boids.rules.bounds
  (:use boids.spatial-vector))

(defn bounds-adjustment [the-boid the-boid-space]
  (let [x-adjust (if (< (:x (:location the-boid)) (:xmin the-boid-space)) 1.0
		     (if (> (:x (:location the-boid)) (:xmax the-boid-space)) -1.0 0.0))
	y-adjust (if (< (:y (:location the-boid)) (:ymin the-boid-space)) 1.0
		     (if (> (:y (:location the-boid)) (:ymax the-boid-space)) -1.0 0.0))]
    (struct spatial-vector x-adjust y-adjust)))
