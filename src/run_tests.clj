(use 'clojure.contrib.find-namespaces 'clojure.test 'boids.boid-test 'boids.boid-space-test
     'boids.rules.center-of-mass-test)

(run-tests 'boids.boid-test 'boids.boid-space-test 'boids.rules.center-of-mass-test)
