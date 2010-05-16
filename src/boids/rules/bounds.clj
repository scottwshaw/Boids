(ns boids.rules.bounds
  (:use boids.spatial-vector))

(def *bounds-radius* 0.0)

(defn bounds-adjustment [the-boid the-bounds]
  (let [xb (:x (:location the-boid))
	yb (:y (:location the-boid))
	x-min-d (- xb (:xmin the-bounds))
	x-max-d (- (:xmax the-bounds) xb)
	y-min-d (- yb (:ymin the-bounds))
	y-max-d (- (:ymax the-bounds) yb)
	x-adjust (if (< x-min-d *bounds-radius*) 1.0
		     (if (< x-max-d *bounds-radius*) -1.0 0.0))
	y-adjust (if (< y-min-d *bounds-radius*) 1.0
		     (if (< y-max-d *bounds-radius*) -1.0 0.0))]



    (struct spatial-vector x-adjust y-adjust)))
