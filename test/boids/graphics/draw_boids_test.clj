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

(def db1 (new-boid 100 200 4 2))
(def db2 (new-boid 200 100 3 5))
(def db3 (new-boid 150 250 5 4))
(def db4 (new-boid 10 300 15 10))

(def drawable-bounds (struct bounds 0 1000 0 750))
(def drawable-boids (atom [db1 db2 db3 db4]))

(deftest test-should-render-single-boid
  (let [d (new Dimension 
	       (- (:xmax drawable-bounds) (:xmin drawable-bounds)) 
	       (- (:ymax drawable-bounds) (:ymin drawable-bounds)))
	p (doto (proxy [JPanel] [] 
		  (paint [g] (render-boid db1 drawable-bounds g))) (.setPreferredSize d))
	f (doto (new JFrame) (.add p) .pack .show)]
    (. Thread (sleep 500))
    (. f (dispose))))

(deftest should-render-boids
  (let [d (new Dimension
	       (- (:xmax drawable-bounds) (:xmin drawable-bounds)) 
	       (- (:ymax drawable-bounds) (:ymin drawable-bounds)))
	p (doto (proxy [JPanel] [] 
		  (paint [g] (render-boids drawable-bounds drawable-boids g)))
	    (.setPreferredSize d))
	f (doto (new JFrame) (.add p) .pack .show)]
    (. Thread (sleep 500))
    (. f (dispose))))

(defn render-frame-sequence []
  (let [d (new Dimension
	       (- (:xmax drawable-bounds) (:xmin drawable-bounds)) 
	       (- (:ymax drawable-bounds) (:ymin drawable-bounds)))
	p (doto (proxy [JPanel] [] 
		  (paint [g] (render-boids drawable-bounds drawable-boids g)))
	    (.setPreferredSize d))
	f (doto (new JFrame) (.add p) .pack .show)]
    (dotimes [nframes 50]
      (swap! drawable-boids move-all-boids-one-step drawable-bounds)
      (. Thread (sleep 100))
      (. p (repaint)))
    (. f (dispose))))

(defn render-boids-and-move [boids-a g]
  (binding [*velocity-weight* 0.01
	    *bounds-radius* 100
	    *bounds-weight* 20
	    *avoidance-radius* 10.0
	    *center-of-mass-weight* 0.008
	    *avoidance-weight* 2.0]
    (render-boids drawable-bounds boids-a g)
    (swap! boids-a move-all-boids-one-step drawable-bounds)))

(defn render-boids-and-move [boids-a g]
  (binding [*velocity-weight* 0.01
	    *bounds-radius* 100
	    *bounds-weight* 20
	    *avoidance-radius* 10.0
	    *center-of-mass-weight* 0.008
	    *avoidance-weight* 2.0]
    (render-boids drawable-bounds boids-a g)
    (swap! boids-a move-all-boids-one-step drawable-bounds)))

(defn render-frame-sequence-with-overrides []
  (let [d (new Dimension
	       (- (:xmax drawable-bounds) (:xmin drawable-bounds)) 
	       (- (:ymax drawable-bounds) (:ymin drawable-bounds)))
	p (doto (proxy [JPanel] [] 
		  (paint [g] (render-boids-and-move drawable-boids g)))
	    (.setPreferredSize d))
	f (doto (new JFrame) (.add p) .pack .show)]
    (dotimes [nframes 10]
      (. Thread (sleep 100))
      (. p (repaint)))
    (. f (dispose))))

(defn render-random-frame-sequence-with-overrides []
  (let [d (new Dimension
	       (- (:xmax drawable-bounds) (:xmin drawable-bounds)) 
	       (- (:ymax drawable-bounds) (:ymin drawable-bounds)))
	boids-a (atom (random-boids 10 drawable-bounds 5.0))
	p (doto (proxy [JPanel] [] 
		  (paint [g] (render-boids-and-move boids-a g)))
	    (.setPreferredSize d))
	f (doto (new JFrame) (.add p) .pack .show)]
    (dotimes [nframes 500]
      (. Thread (sleep 100))
      (. p (repaint)))
    (. f (dispose))))

(deftest should-render-frame-sequence 
  (render-random-frame-sequence-with-overrides))

(deftest test-should-create-panel-with-correct-size
  (let [p (bpanel drawable-bounds) d (.getPreferredSize p)]
    (is (= d (new Dimension 
		  (- 
		   (:xmax drawable-bounds) 
		   (:xmin drawable-bounds)) 
		  (- 
		   (:ymax drawable-bounds)
		   (:ymin drawable-bounds)))))))

;(deftest test-should-create-a-frame-with-embedded-panel
;  (let [f (doto (new JFrame) (.add (bpanel drawable-boids)) .pack .show)]
;    (. Thread (sleep 1000))
;    (. f (dispose))))


    

