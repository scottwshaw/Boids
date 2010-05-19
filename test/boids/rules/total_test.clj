(ns boids.rules.total-test
  (:use clojure.test 
	boids.boid
	boids.boid-test
	boids.bounds
	boids.bounds-test
	boids.rules.total

	boids.spatial-vector))

(deftest should-compute-correct-adjustment-in-bounds
  (is (= (total-adjustment b2 [b1 b2 b3 b4] initial-bounds)
	 (struct spatial-vector 4.805  -0.47499999999999964))))

(deftest should-compute-correct-adjustment-with-weights
  (binding [*velocity-weight* 0.1
	    *center-of-mass-weight* 0.2
	    *avoidance-weight* 0.3
	    *goal-weight* 0.2]
    (is (= (total-adjustment b2 [b1 b2 b3 b4] initial-bounds {:x -3 :y 5})
	   {:x 1.9166666666666665, :y 5.25}))))

(deftest should-compute-correct-adjustment-out-of-bounds
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-boid (struct-map boid :location loc :velocity vel)
	all-boids [b1 b2 b3 b4]
	the-bounds (struct bounds -6 10 0 20)]
    (binding [*goal-weight* 1.0]
      (is (= (total-adjustment the-boid all-boids the-bounds {:x -3 :y 5}) 
	     (struct spatial-vector 6.31375 1.71875))))))

(deftest should-compute-correct-adjustment-out-of-bounds-weighted
  (binding [*bounds-weight* 0.1
	    *goal-weight* 1.0]
    (let [loc (struct spatial-vector -6.5 6.0)
	  vel (struct spatial-vector -1.0 2.0)
	  the-boid (struct-map boid :location loc :velocity vel)
	  all-boids [b1 b2 b3 b4]
	  the-bounds (struct bounds -6 10 0 20)]
	(is (= (total-adjustment the-boid all-boids the-bounds {:x -3 :y 5}) 
	       (struct spatial-vector 5.41375 1.71875))))))
