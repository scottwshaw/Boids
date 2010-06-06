(ns boids.rules.center-of-mass 
  (:use boids.boid boids.spatial-vector))

(defn center-of-mass-exclusive-of [the-boids to-exclude]
  (let [the-other-boids (remove #(= to-exclude %) the-boids)]
    (sv-div (apply sv-sum (map :location the-other-boids)) (.length (vec the-other-boids)))))

(defn center-of-mass-adjustment [this-boid blist]
  (sv-diff (center-of-mass-exclusive-of blist this-boid) (:location this-boid)))
  