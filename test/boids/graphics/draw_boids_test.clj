(ns boids.graphics.draw-boids-test
  (:use clojure.test
	boids.boid
	boids.bounds
	boids.graphics.draw-boids
	boids.rules.total
	boids.spatial-vector)
  (:import  (java.awt Dimension)
	    (javax.swing JFrame)
	    (javax.swing JPanel)))

(defn new-boid [x y vx vy]
  (struct boid (struct spatial-vector x y) (struct spatial-vector vx vy)))

(def db1 (new-boid 100 200 4 2))
(def db2 (new-boid 200 100 3 5))
(def db3 (new-boid 150 250 5 4))
(def db4 (new-boid 10 300 15 10))

(def drawable-bounds (struct bounds 0 500 0 500))
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
    (dotimes [nframes 10]
      (swap! drawable-boids move-all-boids-one-step drawable-bounds)
      (. Thread (sleep 500))
      (. p (repaint)))
    (. f (dispose))))

(defn render-frame-sequence-with-overrides []
  (let [d (new Dimension
	       (- (:xmax drawable-bounds) (:xmin drawable-bounds)) 
	       (- (:ymax drawable-bounds) (:ymin drawable-bounds)))
	p (doto (proxy [JPanel] [] 
		  (paint [g] 
			 (binding [*velocity-weight* 0.01
				   *center-of-mass-weight* 0.005
				   *avoidance-weight* 1.0
				   *bounds-radius* 10.0
				   *bounds-weight* 10.0]
			   (render-boids drawable-bounds drawable-boids g))))
	    (.setPreferredSize d))
	f (doto (new JFrame) (.add p) .pack .show)]
    (dotimes [nframes 100]
      (swap! drawable-boids move-all-boids-one-step drawable-bounds)
      (. Thread (sleep 100))
      (. p (repaint)))
    (. f (dispose))))

(deftest should-render-frame-sequence 
  (render-frame-sequence-with-overrides))

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


    

