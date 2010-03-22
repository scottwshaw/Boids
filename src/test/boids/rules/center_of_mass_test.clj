(ns boids.rules.center-of-mass-test
  (:use clojure.test 
	boids.boid 
	boids.boid-space-test
	boids.rules.center-of-mass 
	boids.spatial-vector))

; x = -.5, y = 3.1666666
(deftest test-should-correctly-calculate-center-of-mass-on-list-of-boids
  (let [the-boids [b2 b3 b4]]
    (is (<= 3.16665 (:y (center-of-mass the-boids)) 3.16667))
    (is (>= -0.49999 (:x (center-of-mass the-boids)) -0.50001))))

(deftest test-should-correctly-calculate-center-of-mass-exclusive-of
  (let [the-boids [b2 b3 b1 b4]]
    (is (<= 3.16665 (:y (center-of-mass-exclusive-of the-boids b1)) 3.16667))
    (is (>= -0.49999 (:x (center-of-mass-exclusive-of the-boids b1)) -0.50001))))

;; x = -0.833333333/1000, y = 3.5/1000
;; This test shadows the center-of-mass-adjustment-factor to make sure it is possible to 
;; do so.  I'm expecting that these parameters will be injected outside the main function
;; definition.
;;
;; One thing I'm not really understanding right now is why the variable name doesn't have
;; to be namespace qualified since I'm currently in a different namespace than 
;; boids.rules.center-of-mass.  I guess the :use just imports everything into the current namespace?
(deftest test-should-correctly-calculate-center-of-mass-adjustment
  (binding [center-of-mass-adjustment-factor 1000.0]
    (is (= (center-of-mass-adjustment b2 (initial-boid-space))
	   (struct-map spatial-vector :x -8.333333333333334E-4 :y 0.0035)))))
