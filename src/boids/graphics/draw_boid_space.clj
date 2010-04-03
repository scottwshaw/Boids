(ns boids.graphics.draw-boid-space
  (:use boids.boid-space)
  (:import 
   (java.awt Dimension)
   (javax.swing JPanel)))

(defn bpanel [bspace]
  (let [d (new Dimension (- (:xmax bspace) (:xmin bspace)) (- (:ymax bspace) (:ymin bspace)))]
    (doto (proxy [JPanel] []) (.setPreferredSize d))))