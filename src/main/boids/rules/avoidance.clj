(ns boids.rules.avoidance
  (:use boids.boid-space boids.spatial-vector))

(def avoidance-radius 2.0)

(defn avoidance-adjustment [the-boid bspace] 
  (let [the-point (:location the-boid)
	points-to-avoid	(map :location
			     (remove #(= the-boid %) 
				     (boids-in-radius bspace 
						      (:location the-boid) 
						      avoidance-radius)))]
    (apply sv-sum (map #(sv-diff the-point %) points-to-avoid))))
    

    