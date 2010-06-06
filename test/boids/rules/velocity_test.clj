(ns boids.rules.velocity-test
  (:use clojure.test 
	boids.boid-test
	boids.rules.velocity
	boids.spatial-vector)
  (:require [clojure.contrib.mock :as mock]))



(deftest test-velocity-adjustment
  (testing "arguments passed to sum"
    (let [sv-sum-args-ex (mock/has-args [(:velocity b1)
					 (:velocity b3)
					 (:velocity b4)] (mock/returns {:x 1, :y 1}))]
      (is (mock/expect
	   [sv-sum sv-sum-args-ex]
	   (velocity-adjustment b2 [b1 b2 b3 b4]))))))

;; send with a tolerance of 0.0 so all boids are updated
(deftest test-should-match-velocities-of-other-boids
  (is (= (velocity-adjustment b2 initial-boid-list)
	 {:x 0.3333333333333335 :y -3.0} )))
