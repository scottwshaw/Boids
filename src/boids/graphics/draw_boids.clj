(ns boids.graphics.draw-boids
  (:use boids.spatial-vector)
  (:import 
   (java.awt Dimension)
   (java.awt Color)
   (java.awt.image BufferedImage)
   (javax.swing JPanel)))

(defn- bpanel [bounds]
  (let [d (new Dimension (- (:xmax bounds) (:xmin bounds)) (- (:ymax bounds) (:ymin bounds)))]
    (doto (proxy [JPanel] []) (.setPreferredSize d))))

(defn- translate-location [the-boid the-bounds]
  (let [p0 (struct spatial-vector (:xmin the-bounds) (:ymin the-bounds))]
    (sv-diff (:location the-boid) p0)))
	
(defn- draw-boid [b bnds g]
  (let [t (translate-location b bnds)
	p0 (sv-diff t (:velocity b))
	p1 (sv-sum t (:velocity b))]
    (doto g
      (.setColor (. Color red))
      (.drawLine (:x p0) (:y p0) (:x p1) (:y p1)))))

;; expects boids to be in the form of an atom
(defn render-boids [bounds boids g]
  (let [xdim (- (:xmax bounds) (:xmin bounds))
	ydim (- (:ymax bounds) (:ymin bounds))
	img (new BufferedImage xdim ydim (. BufferedImage TYPE_INT_ARGB))
        bg (. img (getGraphics))]
    (doto bg
      (.setColor (. Color white))
      (.fillRect 0 0 (. img (getWidth)) (. img (getHeight))))
    (dorun (map #(draw-boid % bounds bg) @boids))
    (. g (drawImage img 0 0 nil))
    (. bg (dispose))))
