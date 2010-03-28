(ns boids.rules.bounds
  (:use boids.spatial-vector))

(def bounds-adjustment-factor 1.0)

(defn bounds-adjustment [the-boid the-boid-space]
  (let [xmin-adjust (if (< (:x (:location the-boid)) (:xmin the-boid-space)) bounds-adjustment-factor 0.0)
	xmax-adjust (if (> (:x (:location the-boid)) (:xmax the-boid-space)) (- bounds-adjustment-factor) 0.0)
	ymin-adjust (if (< (:y (:location the-boid)) (:ymin the-boid-space)) bounds-adjustment-factor 0.0)
	ymax-adjust (if (> (:y (:location the-boid)) (:ymax the-boid-space)) (- bounds-adjustment-factor) 0.0)]
    (sv-sum (struct spatial-vector xmin-adjust 0.0)
	    (struct spatial-vector xmax-adjust 0.0)
	    (struct spatial-vector 0.0 ymin-adjust)
	    (struct spatial-vector 0.0 ymax-adjust))))
