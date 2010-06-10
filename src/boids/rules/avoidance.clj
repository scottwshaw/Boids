(ns boids.rules.avoidance
  (:use boids.boid
	boids.spatial-vector))

(defn boids-in-radius [blist the-boid radius]
  (filter #(<= (absolute-distance-between-boids the-boid %) radius) blist))

(defn avoidance-adjustment [the-boid blist avoidance-radius] 
  (let [boids-to-avoid (remove #(= the-boid %) 
			       (boids-in-radius blist the-boid avoidance-radius))]
    (sum-of-distances-between-boids the-boid boids-to-avoid)))
