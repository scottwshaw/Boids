(ns boids.rules.center-of-mass-test
  (:use clojure.test 
	boids.boid
	boids.boid-test
	boids.rules.center-of-mass 
	boids.spatial-vector))

; x = -.5, y = 3.1666666
(deftest should-correctly-calculate-center-of-mass-on-list-of-boids
  (let [the-boids [b2 b3 b4]]
    (is (<= 3.16665 (:y (center-of-mass the-boids)) 3.16667))
    (is (>= -0.49999 (:x (center-of-mass the-boids)) -0.50001))))

(deftest test-should-correctly-calculate-center-of-mass-exclusive-of
  (let [the-boids [b2 b3 b1 b4]]
    (is (<= 3.16665 (:y (center-of-mass-exclusive-of the-boids b1)) 3.16667))
    (is (>= -0.49999 (:x (center-of-mass-exclusive-of the-boids b1)) -0.50001))))

;; x = 2.833333333/1000, y = -2.5/1000
(deftest test-should-correctly-calculate-center-of-mass-adjustment
  (is (= (center-of-mass-adjustment b2 initial-boid-list)
	 (struct-map spatial-vector :x -2.8333333333333334 :y 2.5))))
