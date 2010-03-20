(ns boids.boid-test
  (:use clojure.test boids.boid))

(defn initial-boid []
  (let [pos (struct-map spatial-vector :x 1 :y 2)
	vel (struct-map spatial-vector :x 0.1 :y 0.2)]
    (struct-map boid :location pos :velocity vel)))

(deftest test-should-access-boid-location-after-initialisation
  (let [b (initial-boid)]
    (is (= 1 (x (location b))))
    (is (= 2 (y (location b))))))

(deftest test-should-access-boid-velocity-after-initialisation
  (let [b (initial-boid)]
    (is (= 0.1 (x (velocity b))))
    (is (= 0.2 (y (velocity b))))))

(deftest test-should-accurately-compute-distance-between-points-in-one-direction
  (let [p1 (struct-map spatial-vector :x 1.0 :y 2.0)
	p2 (struct-map spatial-vector :x 1.0 :y 3.0)]
    (is (= 1.0 (distance-between p1 p2)))))

(deftest test-should-accurately-compute-distance-between-45-degree-points
  (let [p1 (struct-map spatial-vector :x 1.0 :y 1.0)
	p2 (struct-map spatial-vector :x 2.0 :y 2.0)]
    (is (<= 1.414210 (distance-between p1 p2) 1.414214))))

(deftest test-should-accurately-compute-distance-between-negative-points
  (let [p1 (struct-map spatial-vector :x 0.0 :y 0.0)
	p2 (struct-map spatial-vector :x -1.0 :y -3.0)]
    (is (<= 3.16227 (distance-between p1 p2) 3.16228))))


