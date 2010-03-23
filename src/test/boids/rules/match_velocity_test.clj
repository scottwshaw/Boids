(ns boids.rules.match-velocity-test
  (:use clojure.test 
	boids.boid
	boids.boid-space-test 
	boids.rules.match-velocity
	boids.spatial-vector))

(deftest test-should-match-velocities-of-other-boids
  (let [bspace (initial-boid-space)]
    (is (= (struct-map spatial-vector :x 3.33333333333 :y 2.0) (velocity-adjustment b2 bspace)))))
    