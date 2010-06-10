(ns boids.rules.velocity-test
  (:use clojure.test 
	boids.boid
	boids.boid-test
	boids.rules.velocity)
  (:require [clojure.contrib.mock :as mock]))

(deftest test-velocity-adjustment
  (let [the-boid b1
	blist [b2 b3 b4]
	vval {:x 1.0, :y 1.0}]
    (mock/expect [average-velocity (mock/has-args [blist] (mock/returns vval))
		  take-velocity-from (mock/has-args [vval the-boid] (mock/returns vval))]
		 (is (= (velocity-adjustment the-boid blist) vval)))))
    

;; send with a tolerance of 0.0 so all boids are updated
(deftest test-should-match-velocities-of-other-boids
  (is (= (velocity-adjustment b2 initial-boid-list)
	 {:x 0.3333333333333335 :y -3.0} )))
