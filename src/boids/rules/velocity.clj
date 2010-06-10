(ns boids.rules.velocity
  (:use boids.boid))

(defn velocity-adjustment [the-boid blist]
  (let [the-other-boids (remove #(= the-boid %) blist)
	avg-vel (average-velocity the-other-boids)]
    (take-velocity-from avg-vel the-boid)))
    

    

    