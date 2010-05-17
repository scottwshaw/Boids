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
	 (struct spatial-vector 7.805  4.525))))

(deftest should-compute-correct-adjustment-with-velocity-weight
  (binding [*velocity-weight* 0.1]
    (is (= (total-adjustment b2 [b1 b2 b3 b4] initial-bounds)
	   (struct spatial-vector 4.805 2.725)))))

(deftest should-compute-correct-adjustment-with-com-weight
  (binding [*center-of-mass-weight* 0.1]
    (is (= (total-adjustment b2 [b1 b2 b3 b4] initial-bounds)
	   (struct spatial-vector 7.550000000000001 4.75)))))

(deftest should-compute-correct-adjustment-with-avoidance-weight
  (binding [*avoidance-weight* 0.1]
    (is (= (total-adjustment b2 [b1 b2 b3 b4] initial-bounds)
	   (struct spatial-vector 6.455 6.775)))))

(deftest should-compute-correct-adjustment-out-of-bounds
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-boid (struct-map boid :location loc :velocity vel)
	all-boids [b1 b2 b3 b4]
	the-bounds (struct bounds -6 10 0 20)]
    (is (= (total-adjustment the-boid all-boids the-bounds) 
	   (struct spatial-vector 1.8137500000000002 4.71875)))))

(deftest should-compute-correct-adjustment-out-of-bounds-weighted
  (binding [*bounds-weight* 0.1]
    (let [loc (struct spatial-vector -6.5 6.0)
	  vel (struct spatial-vector -1.0 2.0)
	  the-boid (struct-map boid :location loc :velocity vel)
	  all-boids [b1 b2 b3 b4]
	  the-bounds (struct bounds -6 10 0 20)]
      (is (= (total-adjustment the-boid all-boids the-bounds) 
	     (struct spatial-vector 0.9137500000000003 4.71875))))))
