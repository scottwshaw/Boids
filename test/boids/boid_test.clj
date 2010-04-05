(ns boids.boid-test
  (:use clojure.test 
	boids.boid 
	boids.boid-space
	boids.boid-space-test
	boids.spatial-vector))

(defn initial-boid []
  (let [pos (struct-map spatial-vector :x 1 :y 2)
	vel (struct-map spatial-vector :x 0.1 :y 0.2)]
    (struct-map boid :location pos :velocity vel)))

(deftest test-should-access-boid-location-after-initialisation
  (let [b (initial-boid)]
    (is (= 1 (:x (:location b))))
    (is (= 2 (:y (:location b))))))

(deftest test-should-access-boid-velocity-after-initialisation
  (let [b (initial-boid)]
    (is (= 0.1 (:x (:velocity b))))
    (is (= 0.2 (:y (:velocity b))))))

(deftest should-move-boid-one-step
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-boid (struct-map boid :location loc :velocity vel)
	bspace (struct-map boid-space :xmin -6 :xmax 10 :ymin 0 :ymax 20 :boids [b1 b2 b3 b4])]
    (is (= (move-boid-one-step the-boid bspace) 
	   (struct boid 
		   (struct spatial-vector -4.751250000000001 10.77875)
		   (struct spatial-vector 1.7487499999999998 4.7787500000000005))))))
