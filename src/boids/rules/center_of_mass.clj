(ns boids.rules.center-of-mass 
  (:use boids.boid boids.spatial-vector))

;; I'm assuming this will be defined globally or bound in a thread-local way 
(def center-of-mass-adjustment-factor 100.0)

(defn center-of-mass [the-boids]
  (sv-div (apply sv-sum (map :location the-boids)) (.length the-boids)))

(defn center-of-mass-exclusive-of [the-boids to-exclude]
  (let [the-other-boids (remove #(= to-exclude %) the-boids)]
    (sv-div (apply sv-sum (map :location the-other-boids)) (.length (vec the-other-boids)))))

(defn center-of-mass-adjustment [this-boid bspace] 
  (sv-div (center-of-mass-exclusive-of (:boids bspace) this-boid) center-of-mass-adjustment-factor))
  