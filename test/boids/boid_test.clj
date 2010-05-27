(ns boids.boid-test
  (:use clojure.test 
	boids.boid 
	boids.bounds
	boids.bounds-test
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

(deftest should-move-boid-one-step
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-goal (struct spatial-vector -3 5)
	the-boid (struct-map boid :location loc :velocity vel)
	the-bounds (struct-map bounds :xmin -6 :xmax 10 :ymin 0 :ymax 20)
	all-boids [b1 b2 b3 b4]]
    (is (= (move-boid-one-step the-boid all-boids the-bounds the-goal)
	   {:location {:x -0.18625000000000025, :y 7.71875},
	    :velocity {:x 6.31375, :y 1.71875}}))))

;; regression
(deftest should-move-boids
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-bounds (struct-map bounds :xmin -6 :xmax 10 :ymin 0 :ymax 20)
	the-boid (struct-map boid :location loc :velocity vel)
	the-goal (struct spatial-vector -3 5)
	new-boids (move-all-boids-one-step [b1 b2 b3 b4 the-boid] initial-bounds the-goal)
	expected-boid {:location {:x -1.1862500000000002, :y 7.71875}, 
		       :velocity {:x 5.31375, :y 1.71875}}]
    (is (some #(= expected-boid %) new-boids))))

(deftest should-move-boids-as-atom
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-bounds (struct-map bounds :xmin -6 :xmax 10 :ymin 0 :ymax 20)
	the-boid (struct-map boid :location loc :velocity vel)
	the-boids (atom [b1 b2 b3 b4 the-boid])
	the-goal (struct spatial-vector -3 5)
	new-boids (move-all-boids-one-step @the-boids initial-bounds the-goal)
	expected-boid {:location {:x -1.1862500000000002, :y 7.71875}, 
		       :velocity {:x 5.31375, :y 1.71875}}]
    (is (some #(= expected-boid %) new-boids))))

;; Just checking another set of values 
(deftest should-move-other-boids
  (let [the-bounds (struct-map bounds :xmin -6 :xmax 10 :ymin 0 :ymax 20)
	the-goal (struct spatial-vector -3 5)
	new-boids (move-all-boids-one-step [b1 b2 b3 b4] the-bounds the-goal)
	expected-boid {:location {:x 1.8050000000000002, :y 4.525}, 
		       :velocity {:x -0.19499999999999984, :y 3.5250000000000004}}]
    (is (= expected-boid (second new-boids)))))

