(ns boids.boid-space
  (:use boids.boid
	boids.spatial-vector))

; to deprecate
(defstruct boid-space :xmin :xmax :ymin :ymax :boids)

; to deprecate
(defn space-contains-point? [s p]
  (and (<= (:xmin s) (:x p) (:xmax s))
       (<= (:ymin s) (:y p) (:ymax s))))

(defn boids-in-radius [bspace point radius]
  (filter #(<= (distance-between point (:location %)) radius) (:boids bspace)))

(defn add-boid-to [b s]
  (let [new-boids (conj (:boids s) b)]
    (struct-map boid-space 
      :xmin (:xmin s) 
      :xmax (:xmax s) 
      :ymin (:ymin s) 
      :ymax (:ymax s) 
      :boids new-boids)))

;(defn move-all-boids-one-step [bspace]
;  (let [new-boids (for [b (:boids bspace)] (move-boid-once b bspace))]
;    (struct-map boid-space 
;      :xmin (:xmin bspace) 
;      :xmax (:xmax bspace) 
;      :ymin (:ymin bspace) 
;      :ymax (:ymax bspace) 
;      :boids new-boids)))


