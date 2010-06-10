(ns boids.boid-test
  (:use clojure.test
	boids.boid 
	boids.bounds
	boids.bounds-test
	boids.spatial-vector)
  (:require [clojure.contrib.mock :as mock]))

(def b1 (struct-map boid
	  :location (struct-map spatial-vector :x 1 :y 2)
	  :velocity (struct-map spatial-vector :x 4.0 :y 2.0)))
(def b2 (struct-map boid
	  :location (struct-map spatial-vector :x 2 :y 1)
	  :velocity (struct-map spatial-vector :x 3.0 :y 5.0)))
(def b3 (struct-map boid
	  :location (struct-map spatial-vector :x 1.5 :y 2.5)
	  :velocity (struct-map spatial-vector :x 5.0 :y 4.0)))
(def b4 (struct-map boid
	  :location (struct-map spatial-vector :x -5 :y 6)
	  :velocity (struct-map spatial-vector :x 1.0 :y 0.0)))

(def initial-boid 
     (let [pos (struct-map spatial-vector :x 1 :y 2)
	   vel (struct-map spatial-vector :x 0.1 :y 0.2)]
       (struct-map boid :location pos :velocity vel)))

(def initial-boid-list [b1 b2 b3 b4])

(deftest test-should-access-boid-location-after-initialisation
  (is (= 1 (:x (:location initial-boid))))
  (is (= 2 (:y (:location initial-boid)))))

(deftest should-access-boid-velocity-after-initialisation
    (is (= 0.1 (:x (:velocity initial-boid))))
    (is (= 0.2 (:y (:velocity initial-boid)))))

(deftest test-absolute-distance-between-boids
  (let [distance 1.0]
    (mock/expect [distance-between (mock/has-args [(:location b1) (:location b2)] (mock/returns distance))]
		 (is (= (absolute-distance-between-boids b1 b2) distance)))))

(deftest test-distance-between-boids
  (let [distance 1.0
	sv-diff-ex (mock/has-args [(:location b1) (:location b2)] (mock/returns distance))]
    (mock/expect
     [sv-diff sv-diff-ex]
     (is (= (distance-between-boids b1 b2) distance)))))

(deftest test-sum-of-distances
  (let [the-boid b2
	boid-list [b1 b3 b4]
	vvalue {:x 1.0 :y 1.0}]
    (mock/expect [distance-between-boids (mock/has-args [the-boid] (mock/returns vvalue (mock/times 3)))
		  sv-sum (mock/has-args [vvalue vvalue vvalue] 
					(mock/returns vvalue))]
		  (is (= (sum-of-distances-between-boids the-boid boid-list) vvalue)))))

(deftest test-average-velocity
  (let [boid-list [b1 b2 b3 b4]
	vvalue {:x 4.0, :y 4.0}
	len 4]
    (mock/expect [sv-sum (mock/returns vvalue)
		  sv-div (mock/has-args [vvalue len] (mock/returns vvalue))]
		 (is (= (average-velocity boid-list) vvalue)))))

(deftest test-take-velocity-from
  (let [vvalue {:x 1.0, :y 1.0}
	the-boid b1]
    (is (= (take-velocity-from vvalue the-boid) (sv-diff vvalue (:velocity the-boid))))))
	