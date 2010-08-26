(ns boids.bounds-test
  (:use boids.bounds
	boids.spatial-vector
	clojure.test
	midje.semi-sweet))

(def initial-bounds (struct bounds -10 10 0 20))

(deftest should-correctly-identify-point-inside
  (let [p (struct spatial-vector 1 2)]
    (expect (bounds-contain-point? initial-bounds p) => true?)))

(deftest should-correctly-identify-point-outside
  (let [p (struct-map spatial-vector :x -20 :y 2)]
    (is (false? (bounds-contain-point? initial-bounds p)))))

(deftest should-correctly-identify-point-on-y-border
  (let [p (struct-map spatial-vector :x -9 :y 20)]
    (is (true? (bounds-contain-point? initial-bounds p)))))

(deftest should-correctly-identify-point-on-corner
  (let [p (struct-map spatial-vector :x -10 :y 20)]
    (is (true? (bounds-contain-point? initial-bounds p)))))




