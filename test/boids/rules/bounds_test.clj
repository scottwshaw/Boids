(ns boids.rules.bounds-test
  (:use clojure.test 
	boids.boid 
	boids.boid-space-test
	boids.rules.bounds
	boids.spatial-vector))

; is it ok to depend on bounds-adjustment-factor in a test?
(deftest test-should-turn-boid-at-left-edge
  (let [loc (struct spatial-vector -11.0 6.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector bounds-adjustment-factor 0.0)]
    (is (= (bounds-adjustment the-boid (initial-boid-space)) expected-adjustment))))
	
