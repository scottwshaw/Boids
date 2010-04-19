(ns boids.bounds-test
  (:use clojure.test
	boids.bounds
	boids.spatial-vector))

(defn initial-bounds [] (struct bounds -10 10 0 20))

(deftest should-correctly-identify-point-inside
  (let [s (initial-bounds)
	p (struct spatial-vector 1 2)]
    (is (true? (bounds-contain-point? s p)))))

(deftest should-correctly-identify-point-outside
  (let [s (initial-bounds)
	p (struct-map spatial-vector :x -20 :y 2)]
    (is (false? (bounds-contain-point? s p)))))

(deftest should-correctly-identify-point-on-y-border
  (let [s (initial-bounds)
	p (struct-map spatial-vector :x -9 :y 20)]
    (is (true? (bounds-contain-point? s p)))))

(deftest should-correctly-identify-point-on-corner
  (let [s (initial-bounds)
	p (struct-map spatial-vector :x -10 :y 20)]
    (is (true? (bounds-contain-point? s p)))))




