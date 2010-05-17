(ns boids.rules.velocity
  (:use boids.spatial-vector))

(defn velocity-adjustment [the-boid blist tolerance]
  (let [the-other-boids (remove #(= the-boid %) blist)
	avg-vel (sv-div (apply sv-sum (map :velocity the-other-boids)) (.length (vec the-other-boids)))
	v-diff (distance-between (:velocity the-boid) avg-vel)]
    (if (> v-diff tolerance) avg-vel 0.0)))
    

    

    