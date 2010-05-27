(ns boids.rules.goal-test
  (:use boids.boid-test
	boids.rules.goal
	clojure.test))

(deftest should-calculate-goal-adjustment
  (let [the-boid b2
	goal {:x -3.0 :y 2.0}]
    (is (= (goal-adjustment the-boid goal) {:x -5.0 :y 1.0}))))
    

