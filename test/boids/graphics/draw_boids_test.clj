(ns boids.graphics.draw-boids-test
  (:use clojure.test
	boids.boid
	boids.bounds
	boids.graphics.draw-boids
	boids.random
	boids.rules.total
	boids.spatial-vector)
  (:import  (java.awt Dimension)
	    (javax.swing JFrame)
	    (javax.swing JPanel)))

(def drawable-bounds (struct bounds -500 500 -350 350))

(defn- render-boids-and-move [boids-a g]
  (binding [*velocity-weight* 0.01
	    *bounds-radius* 100
	    *bounds-weight* 20
	    *avoidance-radius* 10.0
	    *center-of-mass-weight* 0.01
	    *avoidance-weight* 2.0]
    (render-boids drawable-bounds boids-a g)
    (swap! boids-a move-all-boids-one-step drawable-bounds)))

(defn- render-random-frame-sequence-with-overrides []
  (let [d (new Dimension
	       (- (:xmax drawable-bounds) (:xmin drawable-bounds)) 
	       (- (:ymax drawable-bounds) (:ymin drawable-bounds)))
	boids-a (atom (random-boids 40 drawable-bounds 5.0))
	p (doto (proxy [JPanel] [] 
		  (paint [g] (render-boids-and-move boids-a g)))
	    (.setPreferredSize d))
	f (doto (new JFrame) (.add p) .pack .show)]
    (dotimes [nframes 50]
      (. Thread (sleep 100))
      (. p (repaint)))
    (. f (dispose))))

(deftest should-render-frame-sequence 
  (render-random-frame-sequence-with-overrides))

    

