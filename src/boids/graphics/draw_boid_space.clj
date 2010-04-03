(ns boids.graphics.draw-boid-space
  (:use boids.boid-space boids.spatial-vector)
  (:import 
   (java.awt Dimension)
   (java.awt Color)
   (java.awt.image BufferedImage)
   (javax.swing JPanel)))

(def red (new Color 255 0 0 255))

(defn bpanel [bspace]
  (let [d (new Dimension (- (:xmax bspace) (:xmin bspace)) (- (:ymax bspace) (:ymin bspace)))]
    (doto (proxy [JPanel] []) (.setPreferredSize d))))

(defn buffered-render-boid [b g]
  (let [p0 (sv-diff (:location b) (sv-div (:velocity b) 2.0))
	p1 (sv-sum (:location b) (sv-div (:velocity b) 2.0))
        img (new BufferedImage 500 500 (. BufferedImage TYPE_INT_ARGB))
        bg (. img (getGraphics))]
    (doto bg
      (.setColor (. Color white))
      (.fillRect 0 0 (. img (getWidth)) (. img (getHeight))))
    (doto bg
      (.setColor (. Color red))
      (.drawLine (:x p0) (:y p0) (:x p1) (:y p1)))
    (. g (drawImage img 0 0 nil))
    (. bg (dispose))))

(defn render-boid [b g]
  (let [p0 (sv-diff (:location b) (sv-div (:velocity b) 2.0))
	p1 (sv-sum (:location b) (sv-div (:velocity b) 2.0))]
    (doto g
      (.setColor (. Color white))
      (.fillRect 0 0 500 500))
    (doto g
      (.setColor (. Color red))
      (.drawLine (:x p0) (:y p0) (:x p1) (:y p1)))))