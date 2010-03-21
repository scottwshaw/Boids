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
    (is (= -5.5 (:x (apply sv-sum svecs))))
    (is (= 7.0 (:y (apply sv-sum svecs))))))

(deftest test-should-correctly-divide
  (let [sv1 (struct-map spatial-vector :x 1.5 :y 2.0)]
    (is (= 0.75 (:x (sv-div sv1 2.0))))
    (is (= 1.0 (:y (sv-div sv1 2.0))))))
  