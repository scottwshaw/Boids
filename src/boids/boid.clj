(ns boids.boid
  (:use boids.spatial-vector))

(defstruct boid :location :velocity)

(defn new-boid [x y vx vy]
  (struct boid (struct spatial-vector x y) (struct spatial-vector vx vy)))

(defn absolute-distance-between-boids [boid-1 boid-2]
  (distance-between (:location boid-1) (:location boid-2)))

(defn distance-between-boids [boid-1 boid-2]
  (sv-diff (:location boid-1) (:location boid-2)))

(defn sum-of-distances-between-boids [the-boid boid-list]
  (apply sv-sum (map #(distance-between-boids the-boid %) boid-list)))

(defn average-velocity [blist]
  (let [vsum (apply sv-sum (map :velocity blist))
	len (.length (vec blist))]
    (sv-div vsum len)))

(defn take-velocity-from [sv-value the-boid]
  (sv-diff sv-value (:velocity the-boid)))
