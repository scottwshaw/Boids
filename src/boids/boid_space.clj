(ns boids.boid-space
  (:use boids.boid boids.spatial-vector))

(defstruct boid-space :xmin :xmax :ymin :ymax :boids)

(defn space-contains-point? [s p]
  (and (<= (:xmin s) (:x p) (:xmax s))
       (<= (:ymin s) (:y p) (:ymax s))))

(defn boids-in-radius [bspace point radius]
  (filter #(<= (distance-between point (:location %)) radius) (:boids bspace)))

;; This is so awkward there must be a better way.  Why is it so hard to simply copy
;; the struct with a new value for one member?
;(defn add-boid-to [b bspace]
;  (let [new-boids (conj (:boids bspace) b)]
;    (apply struct boid-space (conj (subvec (vec (vals bspace)) 1 5) new-boids))))

;; Could do it this way instead.  It's easier to read, but what if you had a lot more members?
(defn add-boid-to [b s]
  (let [new-boids (conj (:boids s) b)]
    (struct-map boid-space 
      :xmin (:xmin s) 
      :xmax (:xmax s) 
      :ymin (:ymin s) 
      :ymax (:ymax s) 
      :boids new-boids)))

