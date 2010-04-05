(ns boids.rules.total-test
  (:use clojure.test 
	boids.boid
	boids.boid-space
	boids.boid-space-test
	boids.rules.total
	boids.spatial-vector))

(deftest should-compute-correct-adjustment-in-bounds
  (is (= (total-adjustment b2 (initial-boid-space)) 
	 (struct spatial-vector 7.825 4.535))))

(deftest should-compute-correct-adjustment-out-of-bounds
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-boid (struct-map boid :location loc :velocity vel)
	bspace (struct-map boid-space :xmin -6 :xmax 10 :ymin 0 :ymax 20 :boids [b1 b2 b3 b4])]
    (is (= (total-adjustment the-boid bspace) (struct spatial-vector 1.7487499999999998 4.7787500000000005)))))
