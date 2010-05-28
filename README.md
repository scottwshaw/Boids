Boids
=====
This is a Clojure implementation of Craig Reynolds' original [Boids
flocking algorithm](http://www.red3d.com/cwr/boids/).  The Boids
algorithm simulates coordinated animal motion such as you'd see in
bird flocks and fish schools.

I was at the 1987 Siggraph convention the year he presented this
work. It was really revolutionary for its time and left a big
impression on me.  See his famous CGI simulation ["Stanley and Stella
in Breaking the
Ice"](http://www.youtube.com/watch?v=3bTqWsVqyzE&NR=1).



The rendition implemented in this program is considerably lower-fi
than Craig's.

Usage
-----

In the top-level project directory, run

    $ lein repl

This gives you a Clojure REPL with all the classpaths set for the
project.

Now, you can execute the following commands (in this sequence) in the REPL

    user=> (use 'boids.main)

    user=> (def s (initialise-boid-space-agent))

    user=> (start-animation s)

    user=> (stop-animation s)

    user=> (kill-animation s)

Installation
------------
1. You should have Java 1.6 installed on your system already
2. Download and install [leiningen](http://github.com/technomancy/leiningen).  The
   installation instructions on the github page are pretty clear, but
   basically, you download the script (_not_ git clone), put it
   somewhere in your path and make it executable, then run <tt>lein
   self-install</tt> at the command line.
3. Now fork or <tt>git clone</tt> this repository
4. That's it.  Follow the usage instructions from there on out



License
-------
What??  Like anybody cares.
