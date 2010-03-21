(ns boids.rules.center-of-mass 
  (:use boids.boid boids.spatial-vector))

(def adjustment-factor 100.0)

(defn center-of-mass [the-boids]
  (sv-div (apply sv-sum (map :location the-boids)) (.length the-boids)))

(defn center-of-mass-exclusive-of [the-boids to-exclude]
  (let [the-other-boids (remove #(= to-exclude %) the-boids)]
    (sv-div (apply sv-sum (map :location the-other-boids)) (.length (vec the-other-boids)))))

(defn center-of-mass-adjustment [this-boid bspace] 
  (sv-div (center-of-mass-exclusive-of (:boids bspace) this-boid) adjustment-factor))
  