(ns boids.rules.center-of-mass-test
  (:use clojure.test 
	boids.boid
	boids.boid-test
	boids.rules.center-of-mass 
	boids.spatial-vector)
  (:require [clojure.contrib.mock :as mock]))

(deftest test-center-of-mass-exclusive-of
  (testing "does not pass self to sum"
    (let [the-boids [b1 b3 b2 b4]
	  ex (mock/has-args [(:location b1) 
			     (:location b3)
			     (:location b4)] 
			    (mock/returns {:x 3, :y 3}))]
      (is (mock/expect [sv-sum ex] (center-of-mass-exclusive-of the-boids b2)))))
  (testing "correct com value exclusive self"
    (let [the-boids [b1 b3 b2 b4]
	  ret-val (- (.length the-boids) 1)]
      (mock/expect [sv-sum (mock/returns {:x ret-val, :y ret-val})] 
		   (is (= (center-of-mass-exclusive-of the-boids b2) {:x 1, :y 1}))))))

(deftest test-should-correctly-calculate-center-of-mass-exclusive-of
  (let [the-boids [b2 b3 b1 b4]]
    (is (<= 3.16665 (:y (center-of-mass-exclusive-of the-boids b1)) 3.16667))
    (is (>= -0.49999 (:x (center-of-mass-exclusive-of the-boids b1)) -0.50001))))

;; x = 2.833333333/1000, y = -2.5/1000
(deftest test-should-correctly-calculate-center-of-mass-adjustment
  (is (= (center-of-mass-adjustment b2 initial-boid-list)
	 (struct-map spatial-vector :x -2.8333333333333334 :y 2.5))))
