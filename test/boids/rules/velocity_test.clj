(ns boids.rules.velocity-test
  (:use clojure.test 
	boids.boid
	boids.boid-test
	boids.rules.velocity
	boids.spatial-vector))

;; send with a tolerance of 0.0 so all boids are updated
(deftest test-should-match-velocities-of-other-boids
  (is (= (struct-map spatial-vector :x 3.3333333333333335 :y 2.0) (velocity-adjustment b2 initial-boid-list 0.0))))
