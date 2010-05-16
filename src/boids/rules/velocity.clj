(ns boids.rules.velocity
  (:use boids.spatial-vector))

(defn velocity-adjustment [the-boid blist]
  (let [the-other-boids (remove #(= the-boid %) blist)]
    (sv-div (apply sv-sum (map :velocity the-other-boids)) (.length (vec the-other-boids)))))
    

    