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

Installation
------------
1. This is Clojure, so you will need to have Java (preferably 1.6)
installed on your system
2. Download and install
[leiningen](http://github.com/technomancy/leiningen).  The
installation instructions on the leiningen github page are pretty
clear, but basically, you download the script (_not_ git clone), put
it somewhere in your path and make it executable, then run  `lein
self-install` at the command line.
3. Fork or `git clone` this repository
4. That's it.  Follow the usage instructions from there on out.
Beware that leiningen is maven-like and will try to download the entire
universe when you run it the first time.

Alternatively, you can execute the shell script in the top level
directory called `repl` but you will have to make sure the jline jar
and clojure jars are in place and edit the script to reflect the
location of your project and repository.  Once you run lein, they should be
there though.  The `repl` script gives you more control over the
Java classpaths and stuff.

Usage
-----
In the top-level project directory, run

    $ lein repl

This gives you a Clojure REPL with all the classpaths set for the
project. Now, you can execute the following commands (in this sequence) in the
REPL

Import the functions in src/boids/main.clj

    user=> (use 'boids.main)

Generate a Var s that is a "double agent" (my term) for controlling
the flock display

    user=> (def f (init-flock))
	       
Start the flock moving.  Watch them fly (or swim). Whoo-hoo!

    user=> (start-flock f) 

Freeze the flock in place. You can always restart with `start-flock` if
you wish.

    user=> (stop-flock f) 

Kills the flock and its window

    user=> (kill-flock f) 

License
-------
What??  Like anybody cares.
