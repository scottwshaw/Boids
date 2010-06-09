(ns boids.rules.avoidance
  (:use boids.boid
	boids.spatial-vector))

(defn distance-between-boids [boid-1 boid-2]
  (distance-between (:location boid-1) (:location boid-2)))

(defn boids-in-radius [blist the-boid radius]
  (filter #(<= (distance-between-boids the-boid %) radius) blist))

(defn avoidance-adjustment [the-boid blist avoidance-radius] 
  (let [the-point (:location the-boid)
	boids-to-avoid (remove #(= the-boid %) 
			       (boids-in-radius blist the-boid avoidance-radius))]
    (apply sv-sum (map #(sv-diff the-point %) (map :location boids-to-avoid)))))
