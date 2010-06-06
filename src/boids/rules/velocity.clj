(ns boids.rules.velocity
  (:use boids.spatial-vector))

(defn velocity-adjustment [the-boid blist]
  (let [the-other-boids (remove #(= the-boid %) blist)
	avg-vel (sv-div (apply sv-sum (map :velocity the-other-boids)) 
			(.length (vec the-other-boids)))]
    (sv-diff avg-vel (:velocity the-boid))))
    

    

    