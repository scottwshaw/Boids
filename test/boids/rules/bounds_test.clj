(ns boids.rules.bounds-test
  (:use clojure.test 
	boids.boid 
	boids.boid-space-test
	boids.rules.bounds
	boids.spatial-vector))

; is it ok to depend on bounds-adjustment-factor in a test?
(deftest test-should-turn-boid-right-at-left-edge
  (let [loc (struct spatial-vector -11.0 6.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector bounds-adjustment-factor 0.0)]
    (is (= (bounds-adjustment the-boid (initial-boid-space)) expected-adjustment))))

(deftest test-should-turn-boid-down-at-top-edge
  (let [loc (struct spatial-vector 0.0 21.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector 0.0 (- bounds-adjustment-factor))]
    (is (= (bounds-adjustment the-boid (initial-boid-space)) expected-adjustment))))
	
(deftest test-should-turn-boid-left-at-right-edge
  (let [loc (struct spatial-vector 11.0 6.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector (- bounds-adjustment-factor) 0.0)]
    (is (= (bounds-adjustment the-boid (initial-boid-space)) expected-adjustment))))

(deftest test-should-turn-boid-up-at-bottom-edge
  (let [loc (struct spatial-vector 0.0 -1.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector 0.0 bounds-adjustment-factor)]
    (is (= (bounds-adjustment the-boid (initial-boid-space)) expected-adjustment))))

(deftest test-should-turn-boid-up-and-right-at-lower-left-corner
  (let [loc (struct spatial-vector -11.0 -1.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector bounds-adjustment-factor bounds-adjustment-factor)]
    (is (= (bounds-adjustment the-boid (initial-boid-space)) expected-adjustment))))
