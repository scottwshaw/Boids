(ns boids.rules.avoidance
  (:use boids.boid boids.spatial-vector))

(defn boids-in-radius [blist point radius]
  (filter #(<= (distance-between point (:location %)) radius) blist))

(defn avoidance-adjustment [the-boid blist avoidance-radius] 
  (let [the-point (:location the-boid)
	points-to-avoid	(map :location
			     (remove #(= the-boid %) 
				     (boids-in-radius 
				      blist 
				      (:location the-boid) avoidance-radius)))]
    (apply sv-sum (map #(sv-diff the-point %) points-to-avoid))))
    

    



