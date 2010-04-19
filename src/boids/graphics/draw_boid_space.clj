(ns boids.graphics.draw-boid-space
  (:use boids.spatial-vector)
  (:import 
   (java.awt Dimension)
   (java.awt Color)
   (java.awt.image BufferedImage)
   (javax.swing JPanel)))

(defn bpanel [bounds]
  (let [d (new Dimension (- (:xmax bounds) (:xmin bounds)) (- (:ymax bounds) (:ymin bounds)))]
    (doto (proxy [JPanel] []) (.setPreferredSize d))))

(defn draw-boid [b g]
  (let [p0 (sv-diff (:location b) (:velocity b))
	p1 (sv-sum (:location b) (:velocity b))]
    (doto g
      (.setColor (. Color red))
      (.drawLine (:x p0) (:y p0) (:x p1) (:y p1)))))

(defn render-boid-space [bounds boids g]
  (let [xdim (- (:xmax bounds) (:xmin bounds))
	ydim (- (:ymax bounds) (:ymin bounds))
	img (new BufferedImage xdim ydim (. BufferedImage TYPE_INT_ARGB))
        bg (. img (getGraphics))]
    (doto bg
      (.setColor (. Color white))
      (.fillRect 0 0 (. img (getWidth)) (. img (getHeight))))
    (dorun (map #(draw-boid % bg) boids))
    (. g (drawImage img 0 0 nil))
    (. bg (dispose))))

(defn render-boid [b bounds g]
  (doto g
    (.setColor (. Color white))
    (.fillRect 0 0 (- (:xmax bounds) (:xmin bounds)) (- (:ymax bounds) (:ymin bounds))))
  (draw-boid b g))