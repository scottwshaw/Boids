(ns boids.boid-test
  (:use clojure.test boids.boid boids.spatial-vector))

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
