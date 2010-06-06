(ns boids.rules.velocity
  (:use boids.spatial-vector))

(defn velocity-adjustment [the-boid blist]
  (let [the-other-boids (remove #(= the-boid %) blist)
	vsum (apply sv-sum (map :velocity the-other-boids))
	len (.length (vec the-other-boids))
	avg-vel (sv-div vsum len)]
    (sv-diff avg-vel (:velocity the-boid))))
    

    

    