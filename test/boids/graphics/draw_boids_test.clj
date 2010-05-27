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

(defn- render-boids-and-move 
  ([boids-a g]
     (binding [*velocity-weight* 0.125
	       *bounds-radius* 20
	       *bounds-weight* 0.8
	       *avoidance-radius* 30.0
	       *center-of-mass-weight* 0.001
	       *avoidance-weight* 0.1]
       (render-boids drawable-bounds boids-a g)
       (swap! boids-a move-all-boids-one-step drawable-bounds)))
  ([boids-a the-goal g]
     (binding [*velocity-weight* 0.125
	       *bounds-radius* 20
	       *bounds-weight* 0.8
	       *avoidance-radius* 30.0
	       *center-of-mass-weight* 0.001
	       *avoidance-weight* 0.1
	       *goal-weight* 0.001]
       (render-boids drawable-bounds boids-a g)
       (swap! boids-a move-all-boids-one-step drawable-bounds the-goal))))

(defn- render-random-frame-sequence-with-overrides []
  (let [d (new Dimension
	       (- (:xmax drawable-bounds) (:xmin drawable-bounds)) 
	       (- (:ymax drawable-bounds) (:ymin drawable-bounds)))
	boids-a (atom (random-boids 50 drawable-bounds 10.0))
	p (doto (proxy [JPanel] [] 
		  (paint [g] (render-boids-and-move boids-a {:x 100 :y 300} g)))
	    (.setPreferredSize d))
	f (doto (new JFrame) (.add p) .pack .show)]
    (dotimes [nframes 50]
      (. Thread (sleep 50))
      (. p (repaint)))
    (. f (dispose))))

(deftest should-render-frame-sequence 
  (render-random-frame-sequence-with-overrides))

    

