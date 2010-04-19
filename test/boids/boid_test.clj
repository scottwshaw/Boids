(ns boids.boid-test
  (:use clojure.test 
	boids.boid 
	boids.spatial-vector))

(def b1 (struct-map boid
	  :location (struct-map spatial-vector :x 1 :y 2)
	  :velocity (struct-map spatial-vector :x 4.0 :y 2.0)))
(def b2 (struct-map boid
	  :location (struct-map spatial-vector :x 2 :y 1)
	  :velocity (struct-map spatial-vector :x 3.0 :y 5.0)))
(def b3 (struct-map boid
	  :location (struct-map spatial-vector :x 1.5 :y 2.5)
	  :velocity (struct-map spatial-vector :x 5.0 :y 4.0)))
(def b4 (struct-map boid
	  :location (struct-map spatial-vector :x -5 :y 6)
	  :velocity (struct-map spatial-vector :x 1.0 :y 0.0)))

(def initial-boid 
     (let [pos (struct-map spatial-vector :x 1 :y 2)
	   vel (struct-map spatial-vector :x 0.1 :y 0.2)]
       (struct-map boid :location pos :velocity vel)))

(deftest test-should-access-boid-location-after-initialisation
  (is (= 1 (:x (:location initial-boid))))
  (is (= 2 (:y (:location initial-boid)))))

(deftest should-access-boid-velocity-after-initialisation
    (is (= 0.1 (:x (:velocity initial-boid))))
    (is (= 0.2 (:y (:velocity initial-boid)))))

(def initial-boid-list [b1 b2 b3 b4])

(deftest should-return-all-boids-in-specified-radius
  (let [point (struct-map spatial-vector :x 1.5 :y 1.82)
	nearby-boids (boids-in-radius initial-boid-list point 2.0)]
    (is (not (nil? (some #(identical? b1 %) nearby-boids))))
    (is (not (nil? (some #(identical? b2 %) nearby-boids))))
    (is (not (nil? (some #(identical? b3 %) nearby-boids))))))

(deftest should-return-all-boids-in-radius-of-b2
  (let [point (struct-map spatial-vector :x 1.5 :y 1.82)
	nearby-boids (boids-in-radius initial-boid-list (:location b2) 2.0)]
    (is (not (nil? (some #(identical? b1 %) nearby-boids))))
    (is (not (nil? (some #(identical? b3 %) nearby-boids))))))

(deftest should-not-return-boids-outside-specified-radius
  (let [point (struct-map spatial-vector :x 1.5 :y 1.82)
	nearby-boids (boids-in-radius initial-boid-list point 2.0)]
    (is (nil? (some #(identical? b4 %) nearby-boids)))))

(deftest should-move-boid-one-step
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-boid (struct-map boid :location loc :velocity vel)
	bspace (struct-map boid-space :xmin -6 :xmax 10 :ymin 0 :ymax 20 :boids [b1 b2 b3 b4])]
    (is (= (move-boid-one-step the-boid bspace) 
	   (struct boid 
		   (struct spatial-vector -4.751250000000001 10.77875)
		   (struct spatial-vector 1.7487499999999998 4.7787500000000005))))))
