(ns boids.spatial-vector-test
  (:use clojure.test boids.spatial-vector))

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

(deftest test-should-accurately-sum-two-spatial-vectors
  (let [sv1 (struct-map spatial-vector :x 1.5 :y 2.0)
	sv2 (struct-map spatial-vector :x -1.75 :y 1.25)]
    (is (= -0.25 (:x (sv-sum sv1 sv2))))
    (is (= 3.25 (:y (sv-sum sv1 sv2))))))

(deftest test-should-accurately-sum-arbitrary-numbers-of-spatial-vectors
  (let [sv1 (struct-map spatial-vector :x 1.5 :y 2.0)
	sv2 (struct-map spatial-vector :x -1.75 :y 1.25)
	sv3 (struct-map spatial-vector :x -1.75 :y 1.25)
	sv4 (struct-map spatial-vector :x -1.75 :y 1.25)
	sv5 (struct-map spatial-vector :x -1.75 :y 1.25)
	svecs [sv1 sv2 sv3 sv4 sv5]]
    (is (= (apply sv-sum svecs) (struct-map spatial-vector :x -5.5 :y 7.0)))))

(deftest test-should-correctly-divide
  (let [sv1 (struct-map spatial-vector :x 1.5 :y 2.0)]
    (is (= (sv-div sv1 2.0) (struct-map spatial-vector :x 0.75 :y 1.0)))))

(deftest test-should-correctly-multiply
  (let [sv1 (struct-map spatial-vector :x 1.5 :y 2.0)]
    (is (=  (sv-mul sv1 2.0) (struct-map spatial-vector :x 3.0 :y 4.0)))))

(deftest test-should-correctly-subtract-two-points
  (let [sv1 (struct-map spatial-vector :x 1.5 :y 2.0)
	sv2 (struct-map spatial-vector :x -0.2 :y 1.5)]
    (is (= (sv-diff sv2 sv1) (struct-map spatial-vector :x -1.7 :y -0.5)))))

(deftest test-should-correctly-subtract-more-than-two-points
  (let [sv1 (struct-map spatial-vector :x 1.5 :y 2.0)
	sv2 (struct-map spatial-vector :x -0.2 :y 1.5)
	sv3 (struct-map spatial-vector :x -0.2 :y 1.5)
	sv4 (struct-map spatial-vector :x -0.2 :y 1.5)]
    (is (= (sv-diff sv1 sv2 sv3 sv4) (struct-map spatial-vector :x 2.1 :y -2.5)))))
