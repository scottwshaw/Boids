(ns boids.rules.avoidance-test
  (:use clojure.test 
	boids.boid-test
	boids.rules.avoidance
	boids.spatial-vector))

(deftest should-return-all-boids-in-radius-of-b2
  (let [point (struct-map spatial-vector :x 1.5 :y 1.82)
	nearby-boids (boids-in-radius initial-boid-list (:location b2) 2.0)]
    (is (not (nil? (some #(identical? b1 %) nearby-boids))))
    (is (not (nil? (some #(identical? b3 %) nearby-boids))))))

(deftest should-not-return-boids-outside-specified-radius
  (let [point (struct-map spatial-vector :x 1.5 :y 1.82)
	nearby-boids (boids-in-radius initial-boid-list point 2.0)]
    (is (nil? (some #(identical? b4 %) nearby-boids)))))

(deftest should-return-all-boids-in-specified-radius
  (let [point (struct-map spatial-vector :x 1.5 :y 1.82)
	nearby-boids (boids-in-radius initial-boid-list point 2.0)]
    (is (not (nil? (some #(identical? b1 %) nearby-boids))))
    (is (not (nil? (some #(identical? b2 %) nearby-boids))))
    (is (not (nil? (some #(identical? b3 %) nearby-boids))))))

(deftest test-should-move-away-from-other-boid
  (is (= (struct-map spatial-vector :x 1.5 :y -2.5) (avoidance-adjustment b2 initial-boid-list))))
    