(ns boids.rules.velocity-test
  (:use clojure.test 
	boids.boid
	boids.boid-space-test 
	boids.rules.velocity
	boids.spatial-vector))

(deftest test-should-match-velocities-of-other-boids
  (let [bspace (initial-boid-space)]
    (is (= (struct-map spatial-vector :x 3.3333333333333335 :y 2.0) (velocity-adjustment b2 bspace)))))
