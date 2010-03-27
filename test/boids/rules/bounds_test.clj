(ns boids.rules.bounds-test
  (:use clojure.test 
	boids.boid 
	boids.boid-space-test
	boids.rules.bounds
	boids.spatial-vector))

(deftest test-should-turn-boid-at-left-edge
  (let [the-boid (struct boid (struct spatial-vector -9.0 6.0) (struct spatial-vector 0.0 0.0))
; is it ok to dependon  bounds-adjustment-factor in a test?
	expected-bounds-adjustment (struct spatial-vector bounds-adjustment-factor 0.0)]
    (is (= (bounds-adjustment the-boid (initial-boid-space)) (expected-bounds-adjustment)))))
	
