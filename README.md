Boids
=====
This is a Clojure implementation of Craig Reynolds' original Boids
flocking algorithm (http://www.red3d.com/cwr/boids/).  The Boids
algorithm simulates coordinated animal motion such as you'd see in
bird flocks and fish schools.

I was at the Siggraph convention the year he presented this work.  It
was really cool and left a big impression on me.  See his famous CGI
simulation "Stanley and Stella in Breaking the Ice"
(http://www.youtube.com/watch?v=3bTqWsVqyzE&NR=1).

The rendition implemented in this program is considerably lower-fi
than Craig's.

Usage
-----

1. In the top-level project directory, run

> lein repl

This gives you a Clojure REPL with all the classpaths set for the
project.

2. You can execute the following sequence in the REPL

user=> (use 'boids.main)

user=> (def s (initialise-boid-space-agent))

user=> (start-animation s)

<<watch the animation for awhile>>

user=> (stop-animation s)

user=> (kill-animation s)

Installation
------------
1. You must first download and install leiningen (http://github.com/technomancy/leiningen)

License
-------
What??  Like anybody cares.
