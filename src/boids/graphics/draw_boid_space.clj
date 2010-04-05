(ns boids.graphics.draw-boid-space
  (:use boids.boid-space boids.spatial-vector)
  (:import 
   (java.awt Dimension)
   (java.awt Color)
   (java.awt.image BufferedImage)
   (javax.swing JPanel)))

(defn bpanel [bspace]
  (let [d (new Dimension (- (:xmax bspace) (:xmin bspace)) (- (:ymax bspace) (:ymin bspace)))]
    (doto (proxy [JPanel] []) (.setPreferredSize d))))

(defn draw-boid [b g]
  (let [p0 (sv-diff (:location b) (:velocity b))
	p1 (sv-sum (:location b) (:velocity b))]
    (doto g
      (.setColor (. Color red))
      (.drawLine (:x p0) (:y p0) (:x p1) (:y p1)))))

(defn render-boid-space [bspace g]
  (let [xdim (- (:xmax bspace) (:xmin bspace))
	ydim (- (:ymax bspace) (:ymin bspace))
	img (new BufferedImage xdim ydim (. BufferedImage TYPE_INT_ARGB))
        bg (. img (getGraphics))]
    (doto bg
      (.setColor (. Color white))
      (.fillRect 0 0 (. img (getWidth)) (. img (getHeight))))
    (dorun (map #(draw-boid % bg) (:boids bspace)))
    (. g (drawImage img 0 0 nil))
    (. bg (dispose))))

(defn render-boid [b bspace g]
  (doto g
    (.setColor (. Color white))
    (.fillRect 0 0 (- (:xmax bspace) (:xmin bspace)) (- (:ymax bspace) (:ymin bspace))))
  (draw-boid b g))