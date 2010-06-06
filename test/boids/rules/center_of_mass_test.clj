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

(deftest test-center-of-mass-adjustment
  (testing "arguments passed to center-of-mass function"
    (let [the-boids [b1 b3 b2 b4]
	  ex (mock/has-args [the-boids b2]
			    (mock/returns {:x 3, :y 3}))]
      (is (mock/expect [center-of-mass-exclusive-of ex] 
		       (center-of-mass-adjustment b2 the-boids)))))
  (testing "arguments passed to sv-diff function"
    (let [the-boids [b1 b3 b2 b4]
	  c-o-m {:x 1, :y 1}
	  com-return-ex (mock/returns c-o-m)
	  diff-args-ex (mock/has-args [c-o-m (:location b2)])]
      (is (mock/expect 
	   [center-of-mass-exclusive-of com-return-ex
	    sv-diff diff-args-ex]
	   (center-of-mass-adjustment b2 the-boids)))))
  (testing "center-of-mass-adjustment return value"
    (let [ret-val {:x 1, :y 1}
	  sv-diff-return-ex (mock/returns ret-val)]
      (mock/expect [sv-diff sv-diff-return-ex]
	   (is (= (center-of-mass-adjustment b2 [b1 b2 b3 b4]) ret-val))))))