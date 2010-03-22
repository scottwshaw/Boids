(def test-libs
     ['boids.boid-test 
      'boids.boid-space-test 
      'boids.spatial-vector-test
      'boids.rules.avoidance-test
      'boids.rules.center-of-mass-test])
     
(apply use 'clojure.test test-libs)

(apply run-tests test-libs)
