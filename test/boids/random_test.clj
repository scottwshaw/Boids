(ns boids.random-test
  (:use clojure.test
	boids.bounds
	boids.random
	midje.semi-sweet))

(deftest should-create-a-boid-within-bounds
  (let [xmin 0
	xmax 100
	ymin 0
	ymax 100
	max-v 10.0
	rand-boid (random-boid (struct bounds xmin xmax ymin ymax) max-v)]
    (expect (:x (:location rand-boid)) => #(< xmin % xmax))
    (expect (:y (:location rand-boid)) => #(< ymin % ymax))
    (expect (:x (:velocity rand-boid)) => #(< (- max-v) % max-v))
    (expect (:y (:velocity rand-boid)) => #(< (- max-v) % max-v))))
  

(deftest should-create-boids-within-bounds
  (let [nboids 10
	xmin 0
	xmax 100
	ymin 0
	ymax 100
	max-v 10.0
	rand-boids (random-boids nboids (struct bounds xmin xmax ymin ymax) max-v)]
    (is (= (.length (vec rand-boids)) nboids))
    (is (every? #(< xmin (:x (:location %)) xmax) rand-boids))
    (is (every? #(< ymin (:y (:location %)) ymax) rand-boids))
    (is (every? #(< (- max-v) (:x (:velocity %)) max-v) rand-boids))
    (is (every? #(< (- max-v) (:y (:velocity %)) max-v) rand-boids))))
    
	