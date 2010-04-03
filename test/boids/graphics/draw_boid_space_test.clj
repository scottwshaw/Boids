(ns boids.graphics.draw-boid-space-test
  (:use clojure.test
	boids.boid-space-test
	boids.graphics.draw-boid-space)
  (:import  (java.awt Dimension)))

(deftest test-should-create-panel-with-correct-size
  (let [b (initial-boid-space)
	p (bpanel b)
	d (.getPreferredSize p)]
    (is (= d (new Dimension (- (:xmax b) (:xmin b)) (- (:ymax b) (:ymin b)))))))
    

