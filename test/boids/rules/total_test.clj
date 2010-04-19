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
	 (struct spatial-vector 7.825 4.535))))

(deftest should-compute-correct-adjustment-out-of-bounds
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-boid (struct-map boid :location loc :velocity vel)
	all-boids [b1 b2 b3 b4]
	the-bounds (struct bounds -6 10 0 20)]
    (is (= (total-adjustment the-boid all-boids the-bounds) (struct spatial-vector 1.7487499999999998 4.7787500000000005)))))
