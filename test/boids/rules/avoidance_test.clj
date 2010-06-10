(ns boids.rules.avoidance-test
  (:use clojure.test 
	boids.boid
	boids.boid-test
	boids.rules.avoidance
	boids.spatial-vector)
  (:require [clojure.contrib.mock :as mock]))

(def test-radius 2.0)

(defn d-b-mock-call [_ test-boid]
  (cond (= test-boid b1) (- test-radius 1.0) ; in
	(= test-boid b2) 0.0 ; in
	(= test-boid b3) (+ test-radius 1.0) ; out
	(= test-boid b4) (- test-radius 1.0))) ; in

(deftest test-boids-in-radius
  (testing "returns only boids that are in"
    (mock/expect [absolute-distance-between-boids (mock/times 4 (mock/calls d-b-mock-call))]
		 (is (= (boids-in-radius [b1 b2 b3 b4] b2 test-radius)
			(list b1 b2 b4))))))

(deftest test-avoidance-adjustment
  (testing "args to distance calcuation"
    (let [boids [b1 b2 b3 b4]]
      (is (mock/expect [boids-in-radius (mock/returns [b1 b3 b4])
			distance-between-boids (mock/times 3 (mock/has-args [b2] (mock/returns {:x 1.0, :y 1.0})))]
		       (avoidance-adjustment b2 boids 2.0))))))
		 

(deftest test-should-move-away-from-other-boid
  (is (= (struct-map spatial-vector :x 1.5 :y -2.5) (avoidance-adjustment b2 initial-boid-list 2.0))))
    