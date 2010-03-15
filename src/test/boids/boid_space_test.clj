(ns boids.boid-space-test
  (:use clojure.test boids.boid boids.boid-space))

(def b1 (struct-map boid
	  :location (struct-map spatial-vector :x 1 :y 2)
	  :velocity (struct-map spatial-vector :x 0 :y 0)))
(def b2 (struct-map boid
	  :location (struct-map spatial-vector :x 2 :y 1)
	  :velocity (struct-map spatial-vector :x 0 :y 0)))
(def b3 (struct-map boid
	  :location (struct-map spatial-vector :x 1.5 :y 2.5)
	  :velocity (struct-map spatial-vector :x 0 :y 0)))
(def b4 (struct-map boid
	  :location (struct-map spatial-vector :x -5 :y 6)
	  :velocity (struct-map spatial-vector :x 0 :y 0)))

(defn initial-boid-space []
  (struct-map boid-space 
    :xmin -10 :xmax 10 :ymin 0 :ymax 20 :boids [b1 b2 b3 b4]))

(deftest test-should-correctly-identify-point-inside
  (let [s (initial-boid-space)
	p (struct-map spatial-vector :x 1 :y 2)]
    (is (true? (space-contains-point? s p)))))

(deftest test-should-correctly-identify-point-outside
  (let [s (initial-boid-space)
	p (struct-map spatial-vector :x -20 :y 2)]
    (is (false? (space-contains-point? s p)))))

(deftest test-should-correctly-identify-point-on-x-border
  (let [s (initial-boid-space)
	p (struct-map spatial-vector :x -10 :y 2)]
    (is (true? (space-contains-point? s p)))))

(deftest test-should-correctly-identify-point-on-y-border
  (let [s (initial-boid-space)
	p (struct-map spatial-vector :x -9 :y 20)]
    (is (true? (space-contains-point? s p)))))

(deftest test-should-correctly-identify-point-on-corner
  (let [s (initial-boid-space)
	p (struct-map spatial-vector :x -10 :y 20)]
    (is (true? (space-contains-point? s p)))))

(deftest test-should-return-list-of-all-boids
  (let [s (initial-boid-space)]
    (is (not (nil? (some #(identical? b1 %) (boids s)))))))

(deftest test-should-return-all-boids-in-specified-radius
  (let [space (initial-boid-space)
	point (struct-map spatial-vector :x 1.5 :y 1.82)
	nearby-boids (boids-in-radius space point 2.0)]
    (is (not (nil? (some #(identical? b1 %) nearby-boids))))
    (is (not (nil? (some #(identical? b2 %) nearby-boids))))
    (is (not (nil? (some #(identical? b3 %) nearby-boids))))))

	
