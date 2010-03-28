(ns boids.rules.total-test
  (:use clojure.test 
	boids.boid 
	boids.boid-space-test
	boids.rules.total
	boids.spatial-vector))

(deftest test-should-compute-correct-adjustment-in-bounds
  (is (= (total-adjustment b2 (initial-boid-space)) (struct spatial-vector 4.825 -0.46499999999999986))))
