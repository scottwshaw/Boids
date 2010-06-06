(ns boids.rules.velocity-test
  (:use clojure.test 
	boids.boid-test
	boids.rules.velocity
	boids.spatial-vector)
  (:require [clojure.contrib.mock :as mock]))


;; There appears to be a bug in mock/has-args that prevents you from passing anything
;; except the literal vector of arguments.  A function that returns this vector, such as
;; (vec (map :velocity [b1 b3 b4])), or even a local binding to a var, doesn't work.
(deftest test-velocity-adjustment
  (testing "arguments passed to sum"
    (let [sv-sum-args-ex (mock/has-args [(:velocity b1)
					 (:velocity b3)
					 (:velocity b4)] (mock/returns {:x 1, :y 1}))]
      (is (mock/expect
	   [sv-sum sv-sum-args-ex]
	   (velocity-adjustment b2 [b1 b2 b3 b4])))))
  (testing "arguments passed to sv-div"
    (let [the-boids [b1 b2 b3 b4]
	  len-minus-1 (- (.length the-boids) 1)
	  sv-sum-return {:x len-minus-1, :y len-minus-1}
	  sv-sum-ex (mock/returns sv-sum-return)
	  sv-div-ex (mock/has-args [sv-sum-return len-minus-1]
				   (mock/returns {:x 0, :y 0}))]
      (mock/expect [sv-sum sv-sum-ex
		    sv-div sv-div-ex]
		   (velocity-adjustment b2 [b1 b2 b3 b4])))))

;; send with a tolerance of 0.0 so all boids are updated
(deftest test-should-match-velocities-of-other-boids
  (is (= (velocity-adjustment b2 initial-boid-list)
	 {:x 0.3333333333333335 :y -3.0} )))
