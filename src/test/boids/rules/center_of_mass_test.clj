(ns boids.rules.center-of-mass-test
  (:use clojure.test boids.boid boids.boid-space-test boids.rules.center-of-mass))

; x = -.5, y = 3.1666666
(deftest test-should-correctly-calculate-center-of-mass-without-self
  (let [the-boids [b2 b3 b4]]
    (is (<= 3.16665 (:y (center-of-mass the-boids)) 3.16667))
    (is (<= -0.49999 (:x (center-of-mass the-boids)) -0.50001))))
    


