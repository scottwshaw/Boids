(ns boids.graphics.draw-boid-space-test
  (:use clojure.test
	boids.boid
	boids.boid-space
	boids.boid-space-test
	boids.graphics.draw-boid-space
	boids.spatial-vector)
  (:import  (java.awt Dimension)
	    (javax.swing JFrame)))

(defn new-boid [x y vx vy]
  (struct boid (struct spatial-vector x y) (struct spatial-vector x vy)))

(def db1 (new-boid 100 200 4 2))
(def db2 (new-boid 200 100 3 5))
(def db3 (new-boid 150 250 5 4))
(def db4 (new-boid 10 600 1 0))

(def drawable-boid-space (struct boid-space 0 500 0 500 [db1 db2 db3 db4]))
     
(deftest test-should-create-panel-with-correct-size
  (let [b drawable-boid-space
	p (bpanel b)
	d (.getPreferredSize p)]
    (is (= d (new Dimension (- (:xmax b) (:xmin b)) (- (:ymax b) (:ymin b)))))))

(deftest test-should-create-a-frame-with-embedded-panel
  (let [f (doto (new JFrame) (.add (bpanel drawable-boid-space)) .pack .show)]))

    

