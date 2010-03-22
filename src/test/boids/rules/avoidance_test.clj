(ns boids.rules.avoidance-test
  (:use clojure.test 
	boids.boid
	boids.boid-space-test 
	boids.rules.avoidance
	boids.spatial-vector))

(deftest test-should-move-away-from-other-boid
  (let [bspace (initial-boid-space)]
    (is (= (struct-map spatial-vector :x 1.5 :y -2.5) (avoidance-adjustment b2 bspace)))))
    