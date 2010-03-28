(ns boids.rules.bounds
  (:use boids.spatial-vector))

(def bounds-adjustment-factor 1.0)

(defn bounds-adjustment [the-boid the-boid-space]
  (let [x-adjust (if (< (:x (:location the-boid)) (:xmin the-boid-space)) bounds-adjustment-factor 
		     (if (> (:x (:location the-boid)) (:xmax the-boid-space)) (- bounds-adjustment-factor) 0.0))
	y-adjust (if (< (:y (:location the-boid)) (:ymin the-boid-space)) bounds-adjustment-factor 
		     (if (> (:y (:location the-boid)) (:ymax the-boid-space)) (- bounds-adjustment-factor) 0.0))]
    (struct spatial-vector x-adjust y-adjust)))
