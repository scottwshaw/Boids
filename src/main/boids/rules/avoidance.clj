(ns boids.rules.avoidance
  (:use boids.boid-space boids.spatial-vector))

(def avoidance-radius 2.0)

(defn avoidance-adjustment [the-boid bspace] 
  (let [points-to-avoid
	(map :location
	     (remove #(= the-boid %) 
		     (boids-in-radius bspace 
				      (:location the-boid) 
				      avoidance-radius)))
	zero-point (struct-map spatial-vector :x 0 :y 0)
	the-point (:location the-boid)]
    (apply sv-diff zero-point (map #(sv-diff % the-point) points-to-avoid))))
    

    