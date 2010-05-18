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
	   {:location {:x -3.6862500000000002, :y 8.71875}, 
	    :velocity {:x 2.8137499999999998, :y 2.71875}}))))

(deftest should-move-boids
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-bounds (struct-map bounds :xmin -6 :xmax 10 :ymin 0 :ymax 20)
	the-boid (struct-map boid :location loc :velocity vel)
	new-boids (move-all-boids-one-step [b1 b2 b3 b4 the-boid] initial-bounds)
	expected-boid {:location {:x -4.68625, :y 8.71875}, 
		       :velocity {:x 1.8137499999999998, :y 2.71875}}]
    (is (some #(= expected-boid %) new-boids))))

(deftest should-move-boids-as-atom
  (let [loc (struct spatial-vector -6.5 6.0)
	vel (struct spatial-vector -1.0 2.0)
	the-bounds (struct-map bounds :xmin -6 :xmax 10 :ymin 0 :ymax 20)
	the-boid (struct-map boid :location loc :velocity vel)
	the-boids (atom [b1 b2 b3 b4 the-boid])
	new-boids (move-all-boids-one-step @the-boids initial-bounds)
	expected-boid {:location {:x -4.68625, :y 8.71875}, 
		       :velocity {:x 1.8137499999999998, :y 2.71875}}]
    (is (some #(= expected-boid %) new-boids))))

(deftest should-move-other-boids
  (let [the-bounds (struct-map bounds :xmin -6 :xmax 10 :ymin 0 :ymax 20)
	new-boids (move-all-boids-one-step [b1 b2 b3 b4] the-bounds)
	expected-boid {:location {:x 6.805, :y 0.5250000000000004},
		       :velocity {:x 4.805, :y -0.47499999999999964}}]
    (is (= expected-boid (second new-boids)))))

