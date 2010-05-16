(ns boids.rules.bounds-test
  (:use clojure.test
	boids.boid
	boids.boid-test
	boids.bounds-test
	boids.rules.bounds
	boids.spatial-vector))

(deftest test-should-turn-boid-right-at-left-edge
  (let [loc (struct spatial-vector -11.0 6.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector 1.0 0.0)]
    (is (= (bounds-adjustment the-boid initial-bounds 0.0) expected-adjustment))))

(deftest test-should-turn-boid-down-at-top-edge
  (let [loc (struct spatial-vector 0.0 21.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector 0.0 -1.0)]
    (is (= (bounds-adjustment the-boid initial-bounds 0.0) expected-adjustment))))
	
(deftest test-should-turn-boid-left-at-right-edge
  (let [loc (struct spatial-vector 11.0 6.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector -1.0 0.0)]
    (is (= (bounds-adjustment the-boid initial-bounds 0.0) expected-adjustment))))

(deftest test-should-turn-boid-up-at-bottom-edge
  (let [loc (struct spatial-vector 0.0 -1.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector 0.0 1.0)]
    (is (= (bounds-adjustment the-boid initial-bounds 0.0) expected-adjustment))))

(deftest test-should-turn-boid-up-and-right-at-lower-left-corner
  (let [loc (struct spatial-vector -11.0 -1.0)
	the-boid (struct-map boid :location loc)
	expected-adjustment (struct spatial-vector 1.0 1.0)]
    (is (= (bounds-adjustment the-boid initial-bounds 0.0) expected-adjustment))))
